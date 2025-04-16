package com.anytech.anytxn.biz.common.enums;

/**
 * 公司类型
 * @Author liyh
 * @Date 2020/10/4 22:34
 */
public enum CorporateType {

    BUSINESS("B","公务卡"),
    CORPORATE("C","公司卡");

    private String code;

    private String desc;

    CorporateType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static CorporateType getEnum(String code){
        for(CorporateType corporateType:CorporateType.values()){
            if(corporateType.code.equals(code)){
                return corporateType;
            }
        }
        return null;
    }
}
