package com.anytech.anytxn.biz.common.annotation.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: sukang
 * @Date: 2021/4/16 14:46
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeleteParameter {

    /**
     * 表名
     */
    String tableName() default "";

    /**
     * 表名的中文描述
     */
    String tableDesc() default "";

    boolean isJoinTable() default false;
}
