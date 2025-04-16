package com.anytech.anytxn.biz.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Author: sukang
 * @Date: 2021/8/27 11:35
 */

@Getter
public enum ParamFileType {

    /**
     * 文本文件类型
     */
    TXT("txt"),
    CSV("csv"),
    EXCEL("excel")
    ;


    private String type;


    ParamFileType(String type) {
        this.type = type;
    }


    public static ParamFileType getParamFileType(String paramFileType){
        return Arrays.stream(ParamFileType.values())
                .filter( t -> Objects.equals(t.getType(),paramFileType))
                .findFirst()
                .orElse(null);
    }

}
