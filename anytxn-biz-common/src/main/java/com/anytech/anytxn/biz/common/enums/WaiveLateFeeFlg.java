package com.anytech.anytxn.biz.common.enums;

/**
 * 滞纳金免除标识
 *
 * @author ZXL
 * @date 2018-9-22
 */
public enum WaiveLateFeeFlg {


    /**
     * 0=不免除，1=延滞免除，2=其他原因免除
     */
    NOT_EXEMPT("0", "不免除"),

    LATE_EXEMPT("1", "延滞免除"),

    OTHER_EXEMPT("2", "其他原因免除");

    private String code;
    private String reason;


    WaiveLateFeeFlg(String code, String reason) {
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
