package com.anytech.anytxn.biz.common.cache.api;

/**
 * 缓存刷新api
 * @author peidong.meng
 * @date 2021/1/28
 */
public interface RefreshHandle {

    /**
     * 缓存刷新处理
     */
    void refresh(String key);
}
