package com.anytech.anytxn.biz.common.enums;

import lombok.Getter;

/**
 * @author :   fengjun
 * @date :  2021/12/31
 **/
@Getter
public enum DataTypeEnum {

    TRANSACTION_DATA("1", "交易");

    DataTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;


}
