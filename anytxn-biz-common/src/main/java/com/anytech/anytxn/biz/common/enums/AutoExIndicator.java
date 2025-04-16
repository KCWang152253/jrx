package com.anytech.anytxn.biz.common.enums;

/**
 * 自动购汇标志
 *
 * @author mpd
 * @date 2020-06-18
 *
 */
public enum AutoExIndicator {


    /**
     * 关闭自动购汇
     */
    CLOSE("0", "关闭自动购汇"),

    OPEN("1", "开通自动购汇");


    private String code;
    private String reason;


    AutoExIndicator(String code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
    }
