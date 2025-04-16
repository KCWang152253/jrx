package com.anytech.anytxn.biz.common.enums;

/**
 * 是否恢复授权占用额度标志
 *
 * @author ping
 * @date 2019-08-28
 *
 */
public enum ReleaseAuthAmount {
    /**
     * 是否恢复授权占用额度标志
     * Y=需要；N=不需要
     */
    RECOVER("Y", "需要恢复授权占用额度标志"),
    NOT_RECOVER("N", "不需要恢复授权占用额度标志");


    private String code;
    private String reason;


    ReleaseAuthAmount(String code, String reason) {
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
