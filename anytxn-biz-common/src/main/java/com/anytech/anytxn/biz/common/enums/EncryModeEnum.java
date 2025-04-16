package com.anytech.anytxn.biz.common.enums;

/**
 * 加密方式
 *
 * @author ping
 * @date 2021-07-09
 */
public enum EncryModeEnum {
    DES("1", "3DES国际算法"),
    SM4("2", "SM4国际算法");

    private String code;
    private String desc;


    EncryModeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
