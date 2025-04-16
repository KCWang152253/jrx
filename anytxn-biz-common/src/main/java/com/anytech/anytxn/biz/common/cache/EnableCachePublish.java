package com.anytech.anytxn.biz.common.cache;

import java.lang.annotation.*;

/**
 * 开启缓存发布
 * 删除注解，由配置控制
 * @author peidong.meng
 * @date 2021/1/28
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Deprecated
public @interface EnableCachePublish {

}
