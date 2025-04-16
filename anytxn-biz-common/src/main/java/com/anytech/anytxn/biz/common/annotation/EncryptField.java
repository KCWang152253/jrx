package com.anytech.anytxn.biz.common.annotation;

import java.lang.annotation.*;

/**
 * @Author wangshuguan
 * @Description 字段加解密
 * @Date 2020-09-13
 * @Version
 */
@Documented
@Inherited
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptField {
}
