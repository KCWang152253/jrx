package com.anytech.anytxn.biz.common.enums;

/**
 * 账户状态
 *
 * @author ZXL
 * @date 2018-9-22
 */
public enum AccountStatus {


    /**
     * 0 - 新户
     * 1 - 静止
     * 2 - 活跃
     * 5 - 核销
     * 8 - 关闭
     * 9 - 删除
     */

    NEW("0", "新户"),

    SLEEP("1", "静止"),

    ACTIVE("2", "活跃"),

    VERIFICATION("5", "核销"),

    CLOSED("8", "关闭"),

    DELETED("9", "删除");

    private String code;

    private String reason;


    AccountStatus(String code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
}
