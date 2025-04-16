package com.anytech.anytxn.biz.common.param.poi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: sukang
 * @Date: 2021/9/8 14:52
 */
@Setter
@Getter
@ToString
public class ParamExportPageDTO {
    private String id;

    private String tableId;

    private String status;

    private String desc;

    private String organizationNumber;

}
