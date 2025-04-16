package com.anytech.anytxn.biz.common.base;

import com.anytech.anytxn.biz.common.util.LoginUserUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author: sukang
 * @Date: 2021/4/16 14:56
 */
@Setter
@Getter
public class ParameterBaseEntity {
    /**
     * 主键ID
     * 表字段:ID
     */
    private String id;



    private String updateBy;

    /**
     * 创建时间
     * 表字段:CREATE_TIME
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     * 表字段:UPDATE_TIME
     */
    private LocalDateTime updateTime;

    /**
     * 版本号
     * 表字段:VERSION_NUMBER
     */
    private Long versionNumber;


    private String organizationNumber;



    public void initUpdateDateTime(){
        this.updateTime = LocalDateTime.now();
    }

    public void initCreateDateTime(){
        this.createTime = LocalDateTime.now();
    }


    /**
     * 提交审核时调用该方法获取登录用户，也就是申请人
     * 审核通过操作db时不该调用此方法，因为此时获取的时审核人的用户名
     */
    public void initApplyBy(){
        this.updateBy = getLoginUserName();
    }


    /**
     * 提交新增申请时需要初始化, aop中反射调用
     */
    public void initCreateValue(){
        initCreateDateTime();
        initUpdateDateTime();
        this.setUpdateBy(getLoginUserName());
        this.setVersionNumber(1L);
    }

    /**
     * 提交更新申请时需要初始化， aop中反射调用
     */
    public void initUpdateValue(Long versionNumber){
        initUpdateDateTime();
        this.versionNumber = versionNumber;
        this.updateBy = getLoginUserName();
    }

    @JsonIgnore
    public String getLoginUserName() {
        return LoginUserUtil.getLoginUserName();
    }


}
