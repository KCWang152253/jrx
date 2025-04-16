package com.anytech.anytxn.biz.common.enums;

/**
 * 清偿责任
 * 
 * @Author liyh
 * @Date 2020/10/4 22:33
 */
public enum Liability {
    /**
     * 清偿责任 - 个人
     */
    PERSONAL("P", "个人"),
    /**
     * 清偿责任 - 公司
     */
    CORPORATE("C", "公司");

    private String code;

    private String desc;

    Liability(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static Liability getEnum(String code) {
        for (Liability liability : Liability.values()) {
            if (liability.code.equals(code)) {
                return liability;
            }
        }
        return null;
    }
}
