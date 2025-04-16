package com.anytech.anytxn.biz.common.enums;

import lombok.Getter;

/**
 * @author :   fengjun
 * @date :  2022/1/3
 **/
@Getter
public enum AuditResultEnum {

    PASS("Y", "通过"),

    REJECT("N", "拒绝");

    AuditResultEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;
}
