package com.anytech.anytxn.biz.common.base.audit;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: sukang
 * @Date: 2021/4/19 18:26
 */
@Setter
@Getter
public class ParmModificationRecord{

    /**
     * json类型的string
     * 如果是新增 存放 新增后数据
     * 如果是 删除，存放需要删除的数据
     * 如果是更新，存放更新后的数据
     */
    private String parmBody;

    /**
     * I-插入；U-修改；D-删除
     */
    private String operateType;

    /**
     * 申请人
     */
    private String applicationBy;


    private String organizationNumber;


    private String mainParameterId;


    private String tableId;
}
