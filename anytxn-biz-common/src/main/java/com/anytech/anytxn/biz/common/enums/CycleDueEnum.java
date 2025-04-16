package com.anytech.anytxn.biz.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 延滞状态   对应数据库字典CYCLE_DUE,追加queuePriority属性是优先级
 * @Author gaoc
 * @Date 2021/11/9
 */
@Getter
public enum CycleDueEnum {

    NO_DEBT("0","没有欠款","99"),
    NO_DELAY("1","未延滞","99"),
    DELAY_XDAYS("2","X天延滞","99"),
    DELAY_30DAYS("3","30天延滞","60"),
    DELAY_60DAYS("4","60天延滞","55"),
    DELAY_90DAYS("5","90天延滞","50"),
    DELAY_120DAYS("6","120天延滞","45"),
    DELAY_150DAYS("7","150天延滞","40"),
    DELAY_180DAYS("8","180天延滞","35"),
    DELAY_210DAYS("9","210天延滞","30"),
    DELAY_240DAYS("10","240天延滞","25"),
    DELAY_270DAYS("11","270天延滞","20"),
    DELAY_300DAYS("12","300天延滞","15"),
    DELAY_330DAYS("13","330天延滞","10"),
    DELAY_360DAYS("14","360天延滞","05"),
    DELAY_390DAYS("15","390天延滞","01");

    private String code;
    private String reason;
    private String queuePriority;
    CycleDueEnum(String code, String reason, String queuePriority) {
        this.code=code;
        this.reason=reason;
        this.queuePriority=queuePriority;
    }
    public static CycleDueEnum getEnum(String Code) {
        return Arrays.stream(CycleDueEnum.values())
                .filter(t -> Objects.equals(Code, t.getCode()))
                .findFirst()
                .orElse(null);
    }

}
