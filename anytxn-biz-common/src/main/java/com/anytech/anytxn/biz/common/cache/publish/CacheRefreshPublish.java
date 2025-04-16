package com.anytech.anytxn.biz.common.cache.publish;

import com.anytech.anytxn.biz.common.cache.api.PublishHandle;
import com.anytech.anytxn.biz.common.cache.config.CacheNacosProperties;
import com.anytech.anytxn.biz.common.cache.config.ItemProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author peidong.meng
 * @date 2021/1/28
 */
@Slf4j
public class CacheRefreshPublish implements PublishHandle {

    /**
     * 缓存配置
     */
    private CacheNacosProperties cacheNacosProperties;

    public CacheRefreshPublish(CacheNacosProperties cacheNacosProperties) {
        this.cacheNacosProperties = cacheNacosProperties;
    }

    @Override
    public Boolean publish(String itemName, String... args) {
        if (!cacheNacosProperties.ablePublishCache()) {
            log.info("未开启缓存刷新发布功能～");
        } else {
            ItemProperties item = cacheNacosProperties.getItemByName(itemName);
            if (item == null) {
                log.error("缓存发布模块未注册，模块名称：{}", itemName);
            } else {
                log.info("|------- cache key 【{}: {}】publish handle start -------|", itemName, args);
                item.getPublishHandle().publish(itemName, args);
                log.info("|------- cache key 【{}: {}】publish handle end -------|", itemName, args);
            }
        }

        return true;
    }

    /**
     * 未开启发布功能时使用发布接口
     * @return
     */
    public static Boolean simplePublish(){
        log.info("未开启缓存刷新发布功能～");
        return true;
    }
}
