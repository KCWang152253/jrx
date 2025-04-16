package com.anytech.anytxn.biz.common.cache.annotation;

import java.lang.annotation.*;

/**
 * 缓存清除标记注解
 * @author peidong.meng
 * @date 2020/3/24
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheClear {

    /**
     * 清除缓存对name
     */
    String name();
}
