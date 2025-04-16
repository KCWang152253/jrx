package com.anytech.anytxn.biz.common.annotation;

import java.lang.annotation.*;

/**
 * @Author wangshuguan
 * @Description 需要加解密的对象
 * @Date 2020-09-13
 * @Version
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptModel {
}
