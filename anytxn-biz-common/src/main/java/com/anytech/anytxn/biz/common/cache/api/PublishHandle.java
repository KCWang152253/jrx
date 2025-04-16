package com.anytech.anytxn.biz.common.cache.api;

/**
 * 缓存发布api
 * @author peidong.meng
 * @date 2021/1/28
 */
public interface PublishHandle {

    /**
     * 发布
     * @param args
     * @return
     */
    Boolean publish(String itemName, String... args);
}
