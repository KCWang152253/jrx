package com.anytech.anytxn.biz.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

/**
 * 取值范围注解
 * @author zhangyingxuan
 * @date 2018-08-20
 **/
@Documented
@Constraint(validatedBy = ValuesValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Values {
    /**
     * 验证失败显示的信息
     */
    String message() default "";

    /**
     * 取值范围
     */
    String[] value() default {};

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
