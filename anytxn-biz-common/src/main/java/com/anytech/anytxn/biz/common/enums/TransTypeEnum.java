package com.anytech.anytxn.biz.common.enums;

/**
 *
 *<pre>
 * Description: 交易类型枚举值
 * Company:	    江融信
 * Version:	    1.0
 * Create at:   2023/8/24
 * <p>
 * 修改历史: 
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 * @author heq
 *</pre>
 */
public enum TransTypeEnum {


    /**
     * DCS01 - Retail Principal
     * DCS02 - Cash Principal
     * DCS03 - Retail Interest
     * DCS04 - Cash Interest
     * DCS05 - Annual Fee
     * DCS06 - Other retail fee
     * DCS07 - Cash Fee
     * DCS0G - Installment NIB
     * DCS0H - Installment Fee
     * DCS0J - Installment IB
     * DCS0O - Payment
     */

    DCS01("DCS01", "Retail Principal"),

    DCS02("DCS02", "Cash Principal"),

    DCS03("DCS03", "Retail Interest"),

    DCS04("DCS04", "Cash Interest"),

    DCS05("DCS05", "Annual Fee"),

    DCS06("DCS06", "Other retail fee"),

    DCS07("DCS07", "Cash Fee"),

    DCS0G("DCS0G", "Installment NIB"),

    DCS0H("DCS0H", "Installment Fee"),

    DCS0J("DCS0J", "Installment IB"),

    DCS0O("DCS0O", "Payment");

    private String code;

    private String reason;


    TransTypeEnum(String code, String reason) {
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
