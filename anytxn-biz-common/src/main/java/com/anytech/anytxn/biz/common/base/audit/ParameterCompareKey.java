package com.anytech.anytxn.biz.common.base.audit;

/**
 * @Author: sukang
 * @Date: 2021/6/9 10:57
 */
public enum ParameterCompareKey {

    /**
     * 基础信息
     */
    BASE_INFO("baseInfo","basicInformation"),

    RULE_TREE("ruleConditions", "ruleTree"),

    CHANGED("changed","changeFieldInformation"),

    RULE_RESULT("ruleResult", "ruleResults"),

    NODE_TABLE("nodeTable","collectionType")
    ;

    String fieldCode;

    String fieldDesc;

    ParameterCompareKey(String fieldCode, String fieldDesc) {
        this.fieldCode = fieldCode;
        this.fieldDesc = fieldDesc;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }
}
