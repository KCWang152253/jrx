package com.anytech.anytxn.biz.common.enums;

/**
 * 借贷记属性
 *
 * @author dengguoqing
 * @date 2018-08-27
 */
public enum DebitCreditIndicator {
    /**
     * 借记交易标志
     */
    DEBIT_INDICATOR("D"),
    /**
     * 贷记交易标志
     */
    CREDIT_INDICATOR("C"),

    /**
     * 非借贷类交易
     */
    NOT_DEBIT_OR_CREDIT("N"),
    /**
     * 非金融交易
     */
    NOT_FINANCE("M"),

    /**
     * 异常标志
     */
    OTHER("ERROR");
    /**
     * 属性值
     */
    private final String code;

    /**
     * @param value 属性值
     */
    DebitCreditIndicator(String value) {
        this.code = value;
    }

    public static DebitCreditIndicator getInstance(String value) {
        for (DebitCreditIndicator debitCreditIndicator : DebitCreditIndicator.values()) {
            if (debitCreditIndicator.getCode().equals(value)){
                return debitCreditIndicator;
            }
        }
        return OTHER;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "DebitCreditIndicator{" +
                "code='" + code + '\'' +
                '}';
    }
}
