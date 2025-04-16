package com.anytech.anytxn.biz.common.enums;

/**
 * 停止计息（所有交易类型）
 *
 * @author ZXL
 * @date 2018-9-22
 */
public enum WaiveInterestFlg {

    /**
     * 0=正常计息
     * 1=停止计息（延滞原因，免除已累积利息），
     * 2=停止计息（其他原因，免除累积利息）
     * 3=停止计息（其他原因，正常收取累积利息）
     */
    ZERO("0", "正常计息"),

    ONE("1", "停止计息（延滞原因，免除已累积利息）"),

    TWO("2", "停止计息（其他原因，免除累积利息）"),

    THREE("3", "停止计息（其他原因，正常收取累积利息）");

    private String code;
    private String reason;


    WaiveInterestFlg(String code, String reason) {
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
