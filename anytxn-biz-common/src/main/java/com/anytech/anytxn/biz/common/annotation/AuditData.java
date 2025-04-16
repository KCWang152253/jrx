package com.anytech.anytxn.biz.common.annotation;

import com.anytech.anytxn.biz.common.enums.DataTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author :   fengjun
 * @date :  2021/12/31
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditData {

    DataTypeEnum dataType();

    Class targetClass();
}
