package com.anytech.anytxn.biz.common.enums;

/**
 *
 * @discription 卡信息-状态枚举类
 * @author gaosuwen
 * @create 2018--22--16:58
 */
public enum CardStatus {

    /**
     * 1   = 活跃
     * 2 = 静止
     * 4 = 挂失换卡或升降级旧卡
     * 8 = 关闭
     * 9 = 待删除
     * 0 = 新建(未面签确认)
     */
    ACTIVE_STATUS("1", "活跃"),

    STATIC_STATUS("2", "静止"),

    REPORT_LOSS_OR_LIFT_OLD_CARD("4", "挂失换卡或升降级旧卡"),

    SHUT_DOWN("8", "关闭"),

    TO_DELETE("9", "待删除"),

    NEW_STATUS("0", "新建(未面签确认)");

    private String code;
    private String desc;


    CardStatus(String code, String desc) {
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
