package com.anytech.anytxn.biz.common.annotation.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: sukang
 * @Date: 2021/4/18 20:36
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnName {

    /**
     * 实体类中字段的中文描述
     * @return string
     */
    String desc() default "";


    /**
     *  是否时tableId字段,  比如卡产品号 P0001 账产品号 A00001
     * @return boolean
     */
    boolean isTableId() default false;
}
