package com.anytech.anytxn.biz.common.enums;

/**
 * 状态
 *
 * @author dengguoqing
 * @date 2018-08-27
 */
public enum Status {
    /**
     * 关闭状态
     */
    CLOSE("8"),
    /**
     * 将要删除状态
     */
    WILL_DELETE("9"),
    /**
     * 正常状态
     */
    OTHER("0");

    private final String code;

    Status(String code) {
        this.code = code;
    }

    public static Status getStatus(String code){
        for (Status status : Status.values()) {
            if (status.getCode().equals(code)){
                return status;
            }
        }
        return OTHER;
    }

    public String getCode() {
        return code;
    }
}
