package com.anytech.anytxn.biz.common.enums;

import org.springframework.util.StringUtils;

/**
 * 卡组织枚举类
 *
 * @author guanghao.ji
 * @date 2023/03/01
 */
public enum CardSchemeEnum {

    /**
     * CHINA UNION PAY
     */
    CUP("C", "CHINA UNION PAY"),

    /**
     * UNION PAY INTERNATIONAL
     */
    UPI("U", "UNION PAY INTERNATIONAL"),

    /**
     * VISA
     */
    VISA("V", "VISA"),

    /**
     * MASTER CARD
     */
    MC("M", "MASTER CARD"),

    /**
     * JAPAN CREDIT BUREAU
     */
    JCB("J", "JAPAN CREDIT BUREAU"),

    /**
     * AMERICAN EXPRESS
     */
    AMEX("A", "AMERICAN EXPRESS"),

    /**
     * DINERS
     */
    DINERS("D", "DINERS"),
    ;

    private String code;
    private String desc;


    CardSchemeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CardSchemeEnum load(String code) {
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        for(CardSchemeEnum ps : CardSchemeEnum.values()) {
            if(ps.getCode().equals(code)) {
                return ps;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
