package com.anytech.anytxn.biz.common.enums;

/**
 *交易属性
 * @author ZXL
 * @date 2018/8/29.
 */
public enum TransactionAttribute {

    /*==================================开始交易属性参数================================*/
    /**
     * 消费
     */
    CONSUME("1","消费"),
    /**
     *现金
     */
    CASH("2","现金"),
    /**
     * 消费分期
     */
    CONSUME_BY_STAGES("3","消费分期"),
    /**
     * 现金分期
     */
    CASH_BY_STAGES("4","现金分期"),
    /**
     * 消费费用
     */
    CONSUME_FEE("5","消费费用"),
    /**
     * 现金费用
     */
    CASH_FEE("6","现金费用"),
    /**
     * 还款
     */
    REPAYMENT("7","还款"),
    /**
     * 争议登记
     */
    DISPUTE_REGISTRATION("8","争议登记"),
    /**
     * 争议释放
     */
    DISPUTE_RELEASE("9","争议释放"),
    /**
     * 备忘交易
     */
    TRANSACTION_MEMO("M","备忘交易"),
    /**
     * 消费交易
     */
    CONSUME_INTEREST("A","消费交易"),
    /**
     * 现金交易
     */
    CASH_INTEREST("B","现金交易"),
    /**
     * 分期贷记
     */
    INSTALL_CREDIT("E","分期贷记"),
    /**
     * 分期整笔交易
     */
    INSTALL_TOTAL("F","分期整笔交易"),
    /**
     * 分期交易
     */
    INSTALL_PRINCIPAL("G","分期本金交易"),
    /**
     * 分期费用交易
     */
    INSTALL_FEE("H","分期费用交易"),
    /**
     * 分期违约金入账
     */
    INSTALL_PENATLY("I","分期违约金入账"),
    /**
     * 分期费用摊销
     */
    INSTALL_FEE_AMORTIZE("J","分期费用摊销")
    ;

    private String code;
    @SuppressWarnings("unchecked")
    private String desc;

    TransactionAttribute(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
}
