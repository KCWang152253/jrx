package com.anytech.anytxn.biz.common.cache.config;

import com.anytech.anytxn.biz.common.cache.api.PublishHandle;
import com.anytech.anytxn.biz.common.cache.api.RefreshHandle;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 基础配置，需要缓存多模块自定义引入
 * @author peidong.meng
 * @date 2021/1/28
 */
@Getter
@Setter
@ToString
public abstract class ItemProperties {

    /**
     * 模块名称，唯一健值
     * 可以通过重写get方法设置默认值/通过配置文件配置
     * 例子参考参数工程ParamCacheProperties
     */
    protected String name;

    /**
     * 缓存刷新key在配置文件中的前缀
     * 可以通过重写get方法设置默认值/通过配置文件配置
     * 例子参考参数工程ParamCacheProperties
     */
    protected String prefixKey;

    /**
     * 缓存key,拼接符
     * 默认@
     */
    protected String splitKey = "@";

    /**
     * 是否开启模块缓存
     * 默认开启
     */
    protected Boolean ableCache = true;

    /**
     * 是否开启模块缓存
     * 默认开启
     */
    protected Boolean ablePublish = true;

    /**
     * 是否开启模块缓存
     * 默认开启
     */
    protected Boolean ableListen = true;

    /**
     * 模块缓存刷新处理器
     * 通过子模块依赖注入
     */
    protected RefreshHandle refreshHandle;

    /**
     * 模块缓存发布处理器
     * 通过子模块依赖注入
     */
    protected PublishHandle publishHandle;

    /**
     * 根配置
     */
    protected CacheNacosProperties cacheNacosProperties;

    /**
     * 子模块是否开启发布功能
     */
    public boolean ablePublish(){
        return getAbleCache() && getAblePublish();
    }

    /**
     * 子模块是否开启监听功能
     */
    public boolean ableListen(){
        return getAbleCache() && getAbleListen();
    }

    public String[] getPrefixKeys(){
        return getPrefixKey().split("\\.");
    }
}
