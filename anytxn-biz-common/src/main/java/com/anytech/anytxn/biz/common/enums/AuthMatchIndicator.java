package com.anytech.anytxn.biz.common.enums;

/**
 * 授权匹配标志
 *
 * @author ping
 * @date 2019-08-28
 */
public enum AuthMatchIndicator {
    /**
     * 授权匹配标志
     * 0=未匹配授权；1=匹配授权
     */
    NOT_MATCH_AUTH("0", "未匹配授权"),
    MATCH_AUTH("1", "匹配授权")
    ;
    private String code;
    private String desc;


    AuthMatchIndicator(String code, String desc) {
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
