package com.anytech.anytxn.biz.common.enums;

/**
 * 交易来源枚举类
 * @author sukang
 */
public enum TransactionSourceEnum {

    /**
     * 0=本地输入；1=本行外部输入；2=内生交易；
     * C=ChinaUnionPay；V=Visa；M=MasterCard；J=JCB；A=Amex
     */

    LOCAL("0", "发卡交易录入, GIRO、CASHBACK等不需收单侧结算的交易, 交易转分期，DCASH,RCASH， (所有分期抛账)"),

    LOCAL_OUT("1", "ACQON-US交易，包括POS分期(此渠道只包括ACQ清算文件交易，" +
            "不包含FILE ACQUIRER FEP TO ANY TXN YYMMDD的交易"),



    INNER_FEE("2", "内生，衍生(利息、交易手续费)"),

    FRMSUS("3", "SQ,IATA等同时入持卡人账和商户账的交易"),

    LOCAL_OTHER("4", "SQ,IATA等同时入持卡人账和商户账的交易"),

    LOCAL_ACMS("5", "ACMS收单，非ACQ"),

    /**
     * 由APP触发的还款（持卡人CA/VA账户间内部转入转出）
     */
    TRIGGER_BY_APP("6", " APP渠道的交易(VA002/VA003 客户发起)"),


    TRIGGER_BY_SYS("7", "内部还款转入转出"),


    /**
     * PayNow ( VA充值交易的一种渠道，由App上送）
     */
    PAYNOW_BY_APP("8", "PAYNOW Trigger By App"),

    /**
     * DBS VA （VA充值交易的一种渠道，即现有的VA充值渠道，由APP上送）
     */
    DBS_VA_BY_APP("9", "DBS VA Trigger By App"),




    /**
     * FOMO Triple-A SGD
     */
    TRIPLE_A("Y", "Triple-A SGD"),


    AMEX("A", "AMEX"),

    BANCNET("B", "BANCNET"),

    CUP("C", "ChinaUnionPay"),


    DCI("D", "DCI"),

    EPCC("E", "网联"),


    /**
     * FOMO-SGD(VA充值交易的一种渠道,由业务手工录入）
     */
    FOMO_VA_BY_CMS("F", "FOMO VA Trigger By CMS"),

    /**
     * G - GIRO
     */
    GIRO("G", "GIRO"),

    JCB("J", "JCB"),

    MASTERCARD("M", "MasterCard"),

    ONUS("N","ONUS"),


    UPI("UPI", "银联国际"),


    /**
     * O - Remittance /TT -> shown in the App as Transaction Channel - Overseas
     */
    OVERSEAS_TT ("O", "Remittance /TT -> shown in the App as Transaction Channel - Overseas"),

    /**
     * P - MEPS M and E already used
     */
    MEPS("P", "MEPS M and E already used"),


    /**
     * 客户VA对VA转账，用于PAY from DVAULT和RECEIVE From DVAULT
     */
    DVAULT_TRANSFER_TO_DVAULT("R", "DVault Transfer To DVault"),

    /**
     * dcs-token（VA充值交易的一种渠道，由App上送）
     */
    TOKEN_VA_BY_APP("T", "DCS-TOKEN VA Trigger By App"),

    @Deprecated
    LOCAL_OUT_L("L", "弃用"),

    OUT("U", "out source"),


    VISA("V", "Visa"),

    /**
     * DVault Withdrawal 以及DVault Withdrawal Reversal使用
     */
    DVAULT_WITHDRAWAL("W", "DVault Withdrawal"),

    TXN_SOURCE("X", "txn source")




    ;


    private String code;

    private String desc;


    TransactionSourceEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
