package com.anytech.anytxn.biz.common.enums;

/**
 * 公司卡标志
 * 
 * @Author liyh
 * @Date 2020/10/4 22:28
 */
public enum CorporateIndicator {
    /**
     * 公司卡标志 - 个人
     */
    PERSONAL("P", "个人"),
    /**
     * 公司卡标志 - 公司
     */
    CORPORATE("C", "公司");

    private String code;

    private String desc;

    CorporateIndicator(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static CorporateIndicator getEnum(String code) {
        for (CorporateIndicator corporateIndicator : CorporateIndicator.values()) {
            if (corporateIndicator.code.equals(code)) {
                return corporateIndicator;
            }
        }
        return null;
    }
}
