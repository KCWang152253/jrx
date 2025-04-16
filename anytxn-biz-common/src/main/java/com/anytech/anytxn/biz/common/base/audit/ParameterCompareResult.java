package com.anytech.anytxn.biz.common.base.audit;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sukang
 * @Date: 2021/4/18 20:05
 */
@Setter
@Getter
public class ParameterCompareResult {

    String fieldCode;

    String fieldDesc;

    String type;

    List<FieldKey> keys;

    List<FieldChangeRecord> context;


    static class FieldChangeRecord {


        String fieldDesc;

        Object oldValue;

        Object newValue;

        public FieldChangeRecord() {
        }

        public FieldChangeRecord(String fieldDesc, Object oldValue, Object newValue) {
            this.fieldDesc = fieldDesc;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public String getFieldDesc() {
            return fieldDesc;
        }

        public void setFieldDesc(String fieldDesc) {
            this.fieldDesc = fieldDesc;
        }

        public Object getOldValue() {
            return oldValue;
        }

        public void setOldValue(Object oldValue) {
            this.oldValue = oldValue;
        }

        public Object getNewValue() {
            return newValue;
        }

        public void setNewValue(Object newValue) {
            this.newValue = newValue;
        }
    }


    public static FieldChangeRecord buildChangeRecord(String fieldDesc, Object oldValue, Object newValue){
        return new FieldChangeRecord(fieldDesc,oldValue,newValue);
    }


    public static final class FieldKey{

        String title;
        String dataIndex;
        String key;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDataIndex() {
            return dataIndex;
        }

        public void setDataIndex(String dataIndex) {
            this.dataIndex = dataIndex;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public static final class ParameterCompareResultBuilder {
        String fieldCode;
        String fieldDesc;
        String type;
        String keys;
        List<FieldChangeRecord> context;

        private ParameterCompareResultBuilder() {
        }

        public static ParameterCompareResultBuilder aParameterCompareResult() {
            return new ParameterCompareResultBuilder();
        }

        public ParameterCompareResultBuilder withFieldCode(String fieldCode) {
            this.fieldCode = fieldCode;
            return this;
        }

        public ParameterCompareResultBuilder withFieldDesc(String fieldDesc) {
            this.fieldDesc = fieldDesc;
            return this;
        }

        public ParameterCompareResultBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public ParameterCompareResultBuilder withKeys(String keys) {
            this.keys = keys;
            return this;
        }


        public ParameterCompareResult build() {
            ParameterCompareResult parameterCompareResult = new ParameterCompareResult();
            parameterCompareResult.setFieldCode(fieldCode);
            parameterCompareResult.setFieldDesc(fieldDesc);
            parameterCompareResult.setType(type);
            parameterCompareResult.setContext(new ArrayList<>(8));
            return parameterCompareResult;
        }
    }
}