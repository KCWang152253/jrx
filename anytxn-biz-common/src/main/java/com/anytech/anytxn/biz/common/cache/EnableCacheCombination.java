package com.anytech.anytxn.biz.common.cache;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * 缓存开启组合注解
 * 删除注解，由配置控制
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Deprecated
public @interface EnableCacheCombination {

    @Configuration
    @EnableCache
    @EnableCacheListener
    @EnableCachePublish
    class CacheCombinationConfigurer{}
}
