package com.anytech.anytxn.biz.common.enums;

/**
 * @Author: sukang
 * @Date: 2021/4/21 19:28
 */
public enum  ParmAuditOperateTypeEnum {
    /**
     * 更新动作
     */
    UPDATE("U","更新"),

    /**
     *删除动作
     */
    DELETE("D","删除"),

    /**
     * 插入动作
     */
    INSERT("I","插入");

    String operateType;

    String desc;


    ParmAuditOperateTypeEnum(String operateType, String desc) {
        this.operateType = operateType;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getOperateType() {
        return operateType;
    }
}
