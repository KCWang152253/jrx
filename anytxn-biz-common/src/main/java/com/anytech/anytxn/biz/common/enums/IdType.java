package com.anytech.anytxn.biz.common.enums;

/**
 * 〈description〉
 * 〈证件类型〉
 *
 * @author zhixuan.xue
 * @create 2018/9/28 17:23
 *
 */
public enum IdType {

    /**
     * U	UNKNOWN
     * T	TEST CARD
     * I	NRIC
     * E	EMPLOYMENT PASS / WORK PERMIT
     * P	PASSPORT
     * Z	CORP CENTRAL BILL / TMP/LODGE
     */
    ID_CARD("I", "NRIC"),
    PASSPORT("P", "PASSPORT"),
    FOREIGNER_RESIDENTS_PERMIT("E", "EMPLOYMENT PASS / WORK PERMIT"),
    COMPANY_CARD("Z", "CORP CENTRAL BILL / TMP/LODGE"),
    UNKNOWN("U", "UNKNOWN"),
    TEST("T", "TEST CARD");

    private String code;
    private String desc;


    IdType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static IdType getIdType(String code){
        for (IdType idType : IdType.values()) {
            if (idType.getCode().equals(code)){
                return idType;
            }
        }
        return null;
    }


}
