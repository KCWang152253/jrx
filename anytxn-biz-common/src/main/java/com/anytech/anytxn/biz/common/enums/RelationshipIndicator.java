package com.anytech.anytxn.biz.common.enums;

/**
 *
 * @discription 卡信息主附卡标志枚举类
 * @author gaosuwen
 * @create 2018--22--17:08
 */
public enum RelationshipIndicator {
    /**
     * P = 主卡
     * S = 附卡
     */
    MIAN_CARD("P", "主卡"),

    ATTACHED_CARD("S", "附卡");


    private String code;
    private String desc;


    RelationshipIndicator(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
