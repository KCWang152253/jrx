package com.anytech.anytxn.biz.common.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 取值范围校验器, 被注解的字段的值, 需是{values}中的值
 * @author zhangyingxuan
 * @date 2018-08-20
 **/
public class ValuesValidator implements ConstraintValidator<Values,String> {

    private String[] values;

    @Override
    public void initialize(Values constraintAnnotation) {
        values = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        for (String v : values) {
            if(v.equals(value)) {
                return true;
            }
        }

        return false;
    }
}
