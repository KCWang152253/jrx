package com.anytech.anytxn.biz.common.enums;

/**
 * 参数表状态
 * @author mpd
 * @date 2020-06-18
 */
public enum  ParameterTableStatus {

    /**
     * 失效
     */
    INVALIDATE("0","失效"),
    /**
     * 有效
     */
    VALIDATE("1","有效");


    private String code;

    private String desc;

    ParameterTableStatus(String code, String desc) {
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
