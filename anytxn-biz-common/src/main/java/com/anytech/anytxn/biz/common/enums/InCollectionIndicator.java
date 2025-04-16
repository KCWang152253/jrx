package com.anytech.anytxn.biz.common.enums;

/**
 * 催收标志
 *
 * @author ZXL
 * @date 2018-9-22
 */
public enum InCollectionIndicator {

    /**
     * 0 - 未催收
     * 1 - 催收
     */
    NOT_COLLECTION("0", "未催收"),

    COLLECTION("1", "催收");

    private String code;
    private String reason;


    InCollectionIndicator(String code, String reason) {
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
