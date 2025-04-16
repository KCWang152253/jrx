package com.anytech.anytxn.biz.common.annotation;

import com.anytech.anytxn.biz.common.enums.SensitiveFieldEnum;

import java.lang.annotation.*;

/**
 * @Author wangshuguan
 * @Description 脱敏域注解
 * @Date 2020-09-12
 * @Version
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desensitized {

    /*脱敏类型(规则)*/
    SensitiveFieldEnum type();

    /*判断注解是否生效的方法*/
    String isEffictiveMethod() default "";

}
