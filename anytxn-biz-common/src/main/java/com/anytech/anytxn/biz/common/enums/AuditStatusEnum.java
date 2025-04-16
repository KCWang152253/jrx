package com.anytech.anytxn.biz.common.enums;

import lombok.Getter;

/**
 * @author :   fengjun
 * @date :  2022/1/3
 **/
@Getter
public enum AuditStatusEnum {

    EFFECTIVE("1", "已审核"),

    INVALID("0", "未审核");

    AuditStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    private String status;
    private String desc;
}
