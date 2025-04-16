package com.anytech.anytxn.biz.common.base.audit;

import com.anytech.anytxn.biz.common.base.ParameterBaseEntity;
import com.anytech.anytxn.biz.common.enums.RuleTypeEnum;
import com.anytech.anytxn.biz.common.util.JacksonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.ApiModelProperty;
import com.anytech.anytxn.biz.common.annotation.db.ColumnName;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * parameter 不依赖 core,因此改基础类存放在biz_common中
 * @Author: sukang
 * @Date: 2021/4/18 20:01
 *
 * [
 *        {
 * 		"fieldCode": "baseInfo",
 * 		"fieldDesc": "基本信息",
 * 		"context": [
 *            {
 * 				"fieldDesc": "产品描述",
 * 				"oldValueValue": "001",
 * 				"newValueValue": "002"
 *            },
 *            {
 * 				"fieldDesc": "产品描述",
 * 				"oldValue": "001",
 * 				"newValue": "002"
 *            },
 *            {
 * 				"fieldDesc": "产品描述",
 * 				"oldValue": "001",
 * 				"newValue": "002"
 *            }
 * 		]
 *    },
 *    {
 * 		"fieldCode": "ruleResult",
 * 		"fieldDesc": "规则结果信息",
 * 		"context": [
 *            {
 * 				"fieldDesc": "码值1",
 * 				"oldValue": "1",
 * 				"newValue": "2"
 *            },
 *            {
 * 				"fieldDesc": "码值2",
 * 				"oldValue": "a",
 * 				"newValue": "b"
 *            },
 *            {
 * 				"fieldDesc": "码值3",
 * 				"oldValue": "c",
 * 				"newValue": "d"
 *            }
 * 		]
 *    },
 *    {
 * 		"fieldCode": "ruleTree",
 * 		"fieldDesc": "规则信息",
 * 		"type":"json"
 * 		"context": [
 *            {
 * 				"fieldDesc": "规则信息",
 * 				"oldValue": {},
 * 				"newValue": {}
 *            }
 * 		]
 *    },
 *
 *    {
 * 		"fieldCode": "",
 * 		"fieldDesc": "卡前缀定义信息",
 * 		"type": "表格",
 * 		"keys": [
 *            {
 * 				"title": "姓名",
 * 				"dataIndex": "name",
 * 				"key": "name"
 *            }
 * 		],
 * 		"context": [
 *            {
 * 				"fieldDesc": "卡前缀定义信息",
 * 				"oldValue": [
 *                    {
 * 						"name": "1",
 * 						"sex": "0",
 * 						"changed": [
 * 							"name",
 * 							"sex"
 * 						]
 *                    }
 * 				],
 * 				"newValue": [
 *                    {
 * 						"name": "su",
 * 						"sex": "1",
 * 						"changed": [
 * 							"name",
 * 							"sex"
 * 						]
 *                    },
 *                    {
 *
 *                    }
 * 				]
 *            }
 * 		]
 *    }
 * ]
 *
 */
@Setter
@Getter
@Slf4j
public class ParameterCompare {

    /**
     * 操作前数据 ，   更新数据时 before和 after都需要赋值
     *                删除操作时 before 需要赋值 ，after不需要
     *                添加操作时  after 需要赋值，before不需要
     */
    Object before;

    /**
     * 操作后数据
     */
    Object after;

    /**
     * 泛型 class
     */
    Class<?> clazz;

    /**
     * 1.主表参数的 tableId,如果该事务的操作 isJoinTable为true，需要赋值
     * 2.该字段会在提交审核时使用，用于校验是否存在审核中的记录
     */
    Object mainParmId;

    /**
     * 如果该事务操作 isJoinTable为true, 则需要自己做比对结果，
     * 如果是单表单记录操作， 则会统一做对比
     */
    List<ParameterCompareResult> parameterCompareResults;

    /**
     * 是否自动审核，aop使用
     */
    Boolean automatedAudit;


    public void compareFieldValue() throws Exception{

        if (before == null || after == null){
            return;
        }

        if (before.getClass() != after.getClass()){
            return;
        }


        try {
            compareClassField(clazz, this.before, this.after);
        } catch (Exception e) {
            log.error("对象{}比对异常",this.after.getClass().getName(), e);
        }

    }



    public void compareClassField(Class<?> clazz,Object before, Object after) throws Exception{

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);

            compareField(declaredField, before, after);
        }

    }



    public void compareField(Field declaredField, Object before, Object after) throws Exception {

        if (ignoreField(declaredField)){
            return;
        }

        Object beforeValue = declaredField.get(before);
        Object afterValue = declaredField.get(after);

        if (beforeValue == null && afterValue == null){
            return;
        }

        // 获取不为null的字段 根据其类型值做比较并记录结果
        Object object = Optional.ofNullable(beforeValue).orElse(afterValue);

        if (object instanceof String){

            stringCompare(beforeValue, afterValue, declaredField);
        }else if (object instanceof  Number){

            numberCompare(beforeValue, afterValue, declaredField);
        }else if (object instanceof  Collection){

            collectionCompare(beforeValue, afterValue, declaredField);
        }else if (object instanceof Temporal){

            localDateCompare(beforeValue, afterValue, declaredField);
        }

        else {
            objectCompare(beforeValue, afterValue);
        }





    }

    private void localDateCompare(Object beforeValue, Object afterValue, Field declaredField) {
        if (Objects.equals(beforeValue,afterValue)){
            addCompareResultToBaseInfo(declaredField, beforeValue, afterValue);
        }
    }


    private void collectionCompare(Object beforeValue, Object afterValue, Field declaredField) throws Exception {

        beforeValue = Optional.ofNullable(beforeValue).orElse(Collections.emptyList());
        afterValue = Optional.ofNullable(afterValue).orElse(Collections.emptyList());

        if (beforeValue instanceof Collection && afterValue instanceof Collection) {
            Type genericType = declaredField.getGenericType();
            ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) genericType;
            Type[] genericTypes = parameterizedType.getActualTypeArguments();
            Type type = genericTypes[0];

            if (Objects.equals(type.getTypeName(), String.class.getTypeName())
                    || Objects.equals(type.getTypeName(), LocalDate.class.getTypeName())
                    || Objects.equals(type.getTypeName(), LocalDateTime.class.getTypeName())
                    || Objects.equals(type.getTypeName(), Integer.class.getTypeName())) {

                JsonNode beforeJsonNode = JacksonUtil.readTree(JacksonUtil.toJsonStr(beforeValue));
                JsonNode afterJsonNode = JacksonUtil.readTree(JacksonUtil.toJsonStr(afterValue));
                if (!Objects.equals(beforeJsonNode, afterJsonNode)){
                    addCompareResultToBaseInfo(declaredField, beforeValue, afterValue);
                }
            }else {
                compareCollectionField(beforeValue,afterValue,type, declaredField);
            }

        }

    }

    /**
     * 封装集合类型的比对结果
     *
     * [{"key":"oldValue"}]                      旧值
     *
     * [{"key":"newValue"},{"key":"newValue"}]   新值
     *
     */
    public void compareCollectionField(Object before, Object after, Type type, Field declaredField) throws Exception {
        Class<?> clazz = Class.forName(type.getTypeName());


        ArrayNode oldArrayNode = Optional.ofNullable(JacksonUtil.readValue(JacksonUtil.toJsonStr(before), ArrayNode.class))
                .orElse(JacksonUtil.arrayNode());

        ArrayNode newArrayNode = Optional.ofNullable(JacksonUtil.readValue(JacksonUtil.toJsonStr(after), ArrayNode.class))
                .orElse(JacksonUtil.arrayNode());


        if (!Objects.equals(oldArrayNode, newArrayNode)) {
            //获取比较对象的 所有参与比对的字段的name以及中文描述
            Map<String, String> namesMap = Arrays.stream(clazz.getDeclaredFields())
                    .filter(t -> !ignoreField(t))
                    .collect(Collectors.toMap(Field::getName, this::getFieldDesc,
                            (key1, key2) -> key2,
                            LinkedHashMap::new));


            ArrayNode oldArrayNodeResult = JacksonUtil.arrayNode();
            ArrayNode newArrayNodeResult = JacksonUtil.arrayNode();

            int max = Math.max(oldArrayNode.size(), newArrayNode.size());

            for (int i = 0; i < max; i++) {
                JsonNode oldJsonNode = oldArrayNode.get(i);
                JsonNode newJsonNode = newArrayNode.get(i);

                if (oldJsonNode == null && newJsonNode != null){
                    newArrayNodeResult.add(buildCollectionEmptyResultJsonNode(namesMap, newJsonNode));
                }

                if (oldJsonNode != null && newJsonNode == null){
                    oldArrayNodeResult.add(buildCollectionEmptyResultJsonNode(namesMap, oldJsonNode));
                }

                if (oldJsonNode != null && newJsonNode != null){
                    buildResultJsonNode(namesMap, newJsonNode, oldJsonNode,
                            newArrayNodeResult,oldArrayNodeResult);

                }

            }
            if (newArrayNodeResult.isEmpty() && oldArrayNodeResult.isEmpty()){
                return;
            }

            //将比对结果添加到结果集中
            String fieldDesc = getFieldDesc(declaredField);

            addCompareResultToNodeTable(fieldDesc,newArrayNodeResult, oldArrayNodeResult,namesMap);


        }

    }


    public void addCompareResultToNodeTable(String fieldDesc, ArrayNode newArrayNodeResult, ArrayNode oldArrayNodeResult, Map<String, String> namesMap){

        ParameterCompareResult.FieldChangeRecord fieldChangeRecord = ParameterCompareResult.buildChangeRecord(
                fieldDesc, oldArrayNodeResult, newArrayNodeResult);

        putParameterCompareResult(fieldChangeRecord, ParameterCompareKey.NODE_TABLE,namesMap);

    }





    private ObjectNode buildCollectionEmptyResultJsonNode(Map<String, String> namesMap, JsonNode newJsonNode){
        ObjectNode objectNode = JacksonUtil.objectNode();


        objectNode.putPOJO(ParameterCompareKey.CHANGED.getFieldCode(), namesMap.keySet());

        objectNode.setAll(Objects.requireNonNull(JacksonUtil.readValue(newJsonNode.toString(), ObjectNode.class)));

        return objectNode;
    }


    private void buildResultJsonNode(Map<String, String> namesMap,
                                           JsonNode newJsonNode,
                                           JsonNode oldJsonNode,
                                           ArrayNode newArrayNodeResult,
                                           ArrayNode oldArrayNodeResult){

        ObjectNode oldObjectNode = JacksonUtil.objectNode();

        ObjectNode newObjectNode = JacksonUtil.objectNode();

        List<String> changedNames = new ArrayList<>(4);

        namesMap.keySet().forEach(t -> {
            String newText = newJsonNode.get(t).asText();
            String oldText = oldJsonNode.get(t).asText();

            if (!textEquals(newText,oldText)){
                changedNames.add(t);
            }
        });



        if (!changedNames.isEmpty()){

            oldObjectNode.setAll(Objects.requireNonNull(JacksonUtil.readValue(oldJsonNode.toString(), ObjectNode.class)));
            oldObjectNode.putPOJO(ParameterCompareKey.CHANGED.getFieldCode(),changedNames);
            oldArrayNodeResult.add(oldObjectNode);

            newObjectNode.setAll(Objects.requireNonNull(JacksonUtil.readValue(newJsonNode.toString(), ObjectNode.class)));
            newObjectNode.putPOJO(ParameterCompareKey.CHANGED.getFieldCode(),changedNames);
            newArrayNodeResult.add(newObjectNode);
        }
    }


    /**
     * text比对, 一样返回true
     */
    private boolean textEquals(String newText, String oldText) {
        //一样直接返回
        if (Objects.equals(newText,oldText)){
            return true;
        }

        if (isBigDecimal(newText) && isBigDecimal(oldText)){
            return (new BigDecimal(newText).compareTo(new BigDecimal(oldText)) == 0);
        }

        return Objects.equals(newText,oldText);
    }


    private boolean isBigDecimal(String text){
        try {
            new BigDecimal(text);
            return true;
        }catch (Exception e){
            //ignore
        }
        return false;
    }





    private void objectCompare(Object beforeValue, Object afterValue) throws Exception {

        if (!JacksonUtil.isJson(beforeValue)){
            return;
        }

        //如果属性值是json类型 并且父类是BaseParam 则处理
        JsonNode jsonNode = JacksonUtil.readValue(JacksonUtil.toJsonStr(afterValue), JsonNode.class);

        if (jsonNode == null){
            return;
        }

        Iterator<String> stringIterator = jsonNode.fieldNames();
        boolean hashOrgNumber = false;

        while (stringIterator.hasNext()){
            if(Objects.equals(stringIterator.next(),"organizationNumber")){
                hashOrgNumber = true;
                break;
            }
        }

        //如果该属性是对象并且存在机构号字段 则 递归调用比较方法
        if (hashOrgNumber){
            compareClassField(beforeValue.getClass(), beforeValue, afterValue);
        }

    }




    private void numberCompare(Object oldValue, Object newValue, Field declaredField) {
        if (oldValue instanceof Number && newValue instanceof Number) {
            boolean result = false;

            if (oldValue instanceof BigDecimal && newValue instanceof BigDecimal) {
                result = ((BigDecimal) oldValue).compareTo((BigDecimal) newValue) == 0;
            }

            if (oldValue instanceof Long && newValue instanceof Long) {
                result = ((Long) oldValue).compareTo((Long) newValue) == 0;
            }

            if (oldValue instanceof Integer && newValue instanceof Integer) {
                result = ((Integer) oldValue).compareTo((Integer) newValue) == 0;
            }

            if (oldValue instanceof Short && newValue instanceof Short) {
                result = ((Short) oldValue).compareTo((Short) newValue) == 0;
            }
            
            if (!result){
                addCompareResultToBaseInfo(declaredField, String.valueOf(oldValue), String.valueOf(newValue));
            }
            
        }
    }


    /**
     * 规则的比对结果封装
     */
    private void stringCompare(Object oldValue, Object newValue,
                               Field field) {

        if (Objects.equals(this.clazz.getName(),"jrx.anytxn.rule.model.RuleInfo")){
            if (Objects.equals(field.getName(),ParameterCompareKey.RULE_RESULT.getFieldCode())){
                //jsonResult的结果值不能为null
                JsonNode newJsonNode = JacksonUtil.readValue(newValue.toString(), JsonNode.class);
                JsonNode oldJsonNode = JacksonUtil.readValue(oldValue.toString(), JsonNode.class);

                if (newJsonNode == null || oldJsonNode == null){return;}

                Iterator<String> fieldNames = newJsonNode.fieldNames();

                while (fieldNames.hasNext()){
                    String jsonKey = fieldNames.next();

                    String newJsonValue = newJsonNode.findValuesAsText(jsonKey).stream().findFirst().orElse(null);

                    String oldJsonValue = oldJsonNode.findValuesAsText(jsonKey).stream().findFirst().orElse(null);

                    if (!Objects.equals(newJsonValue, oldJsonValue)){
                        addCompareResultToRuleResult(jsonKey,oldJsonValue, newJsonValue);
                    }

                }
                return;
            }else if (Objects.equals(field.getName(),ParameterCompareKey.RULE_TREE.getFieldCode())){
                if (!Objects.equals(oldValue,newValue)){

                    oldValue = JacksonUtil.readTree((String) oldValue);
                    newValue = JacksonUtil.readTree((String) newValue);
                    addCompareResultToRuleTree(oldValue,newValue);
                    return;
                }
            }
        }



        if (!Objects.equals(oldValue, newValue)){
            addCompareResultToBaseInfo(field, oldValue, newValue);
        }


    }

    public void addCompareResultToRuleTree(Object oldValue, Object newValue){

        ParameterCompareResult.FieldChangeRecord fieldChangeRecord = ParameterCompareResult.buildChangeRecord(
                "规则树", oldValue, newValue);

        putParameterCompareResult(fieldChangeRecord, ParameterCompareKey.RULE_TREE, null);

    }


    public void addCompareResultToRuleResult(String resultKey, Object oldValue, Object newValue ){

        //获取到规则类型
        String ruleType = String.valueOf(getFieldValue(after));

        String fieldDesc = Optional.ofNullable(RuleTypeEnum.getRuleTypeEnum(ruleType)
                .getCodeMapping()).orElse(Collections.emptyMap()).get(resultKey);

        ParameterCompareResult.FieldChangeRecord fieldChangeRecord = ParameterCompareResult.buildChangeRecord(
                fieldDesc, oldValue, newValue);

        putParameterCompareResult(fieldChangeRecord, ParameterCompareKey.RULE_RESULT, null);

    }






    public void addCompareResultToBaseInfo(Field field, Object oldValue, Object newValue){

        String fieldDesc = getFieldDesc(field);


        ParameterCompareResult.FieldChangeRecord fieldChangeRecord = ParameterCompareResult.buildChangeRecord(
                fieldDesc, oldValue, newValue);

        putParameterCompareResult(fieldChangeRecord, ParameterCompareKey.BASE_INFO, null);

    }


    public void putParameterCompareResult(ParameterCompareResult.FieldChangeRecord fieldChangeRecord,
                                          ParameterCompareKey parameterCompareKey, Map<String, String> namesMap){

        ParameterCompareResult orElse = parameterCompareResults
                .stream()
                .filter(t -> Objects.equals(t.getFieldCode(), parameterCompareKey.getFieldCode()))
                .findFirst()
                .orElse(null);

        if (orElse == null){
            ParameterCompareResult.ParameterCompareResultBuilder builder = ParameterCompareResult.ParameterCompareResultBuilder
                    .aParameterCompareResult();
            orElse =  builder.withFieldDesc(parameterCompareKey.getFieldDesc())
                    .withFieldCode(parameterCompareKey.getFieldCode()).build();
            parameterCompareResults.add(orElse);
        }

        orElse.getContext().add(fieldChangeRecord);

        if (namesMap != null && namesMap.size() > 0){
            List<ParameterCompareResult.FieldKey> keyList = namesMap.entrySet().stream().map(t -> {
                ParameterCompareResult.FieldKey fieldKey = new ParameterCompareResult.FieldKey();
                fieldKey.setKey(t.getKey());
                fieldKey.setTitle(t.getValue());
                fieldKey.setDataIndex(t.getKey());
                return fieldKey;
            }).collect(Collectors.toList());
            orElse.setKeys(keyList);
            orElse.setType("List");
        }

    }







    public Boolean ignoreField(Field declaredField){
        ColumnName columnNameAnnotation = declaredField.getAnnotation(ColumnName.class);
        ApiModelProperty apiModelPropertyAnnotation = declaredField.getAnnotation(ApiModelProperty.class);

        List<String> stringStream = Arrays.stream(ParameterBaseEntity.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());



        return (Boolean) (stringStream.contains(declaredField.getName()) ||
                        (columnNameAnnotation == null && apiModelPropertyAnnotation == null));
    }


    public String getFieldDesc(Field declaredField){
        ColumnName columnNameAnnotation = declaredField.getAnnotation(ColumnName.class);
        ApiModelProperty apiModelPropertyAnnotation = declaredField.getAnnotation(ApiModelProperty.class);

        return apiModelPropertyAnnotation == null ?
                columnNameAnnotation == null ? "" : columnNameAnnotation.desc()
                : apiModelPropertyAnnotation.value();
    }


    private Object getFieldValue(Object proceed){

        try {
            Field field = proceed.getClass().getDeclaredField("ruleType");
            field.setAccessible(true);
            return field.get(proceed);
        } catch (Exception ex) {
           //ignore
        }
        return null;
    }




    public static  ParameterCompareBuilder getBuilder() {
        return new ParameterCompareBuilder();
    }


    public static final class ParameterCompareBuilder {
        Object before;
        Object after;

        Object mainParmId;

        private ParameterCompareBuilder() {
        }

        public ParameterCompareBuilder withBefore(Object before) {
            this.before = before;
            return this;
        }

        public ParameterCompareBuilder withAfter(Object after) {
            this.after = after;
            return this;
        }

        public ParameterCompareBuilder withMainParmId(Object mainParmId) {
            this.mainParmId = mainParmId;
            return this;
        }


        public  ParameterCompare build(Class<?> clazz) {
            ParameterCompare parameterCompare = new ParameterCompare();
            parameterCompare.after = this.after;
            parameterCompare.clazz = clazz;
            parameterCompare.parameterCompareResults = new ArrayList<>(8);
            parameterCompare.before = this.before;
            parameterCompare.mainParmId = this.mainParmId;
            return parameterCompare;
        }
    }
}
