package com.anytech.anytxn.biz.common.param.poi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @Author: sukang
 * @Date: 2021/8/27 10:53
 */
@Setter
@Getter
@ToString
public class ParamExportDTO {

    private String transactionId;

    private String paramCodes;

    private Integer totalNum;

    private String fileType;

    private Integer pageNum;

    private Integer pageSize;

    private String organizationNumber;

    private Map<String,List<String>> paramCodeIds;




}
