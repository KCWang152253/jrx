package com.anytech.anytxn.biz.common.cache.config;

import com.anytech.anytxn.biz.common.constant.AnyTxnCommonRespCode;
import com.anytech.anytxn.biz.common.constant.CommonRepDetail;
import com.anytech.anytxn.biz.common.exception.AnyTxnCommonException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author peidong.meng
 * @date 2021/1/28
 */
@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "anytxn.cache.root")
public class CacheNacosProperties {
    /**
     * 配置文件前缀
     */
    private String configContext = "application";

    /**
     * 配置文件前缀
     */
    private String configKey = "data";

    /**
     * 缓存监听配置文件
     */
    private String dataId = "cache.yaml";

    /**
     * 缓存配置组
     */
    private String groupId = "DEFAULT_GROUP";

    /**
     * 是否开启缓存
     */
    private Boolean ableCache = false;

    /**
     * 是否开启发布
     */
    private Boolean ablePublish = false;

    /**
     * 是否开启监听
     */
    private Boolean ableListen = false;

    /**
     * 缓存模块
     */
    private Map<String, ItemProperties> items;

    /**
     * 注册模块配置，并返回已注册模块数量
     * @param itemProperties
     * @return
     */
    public int registerItem(ItemProperties itemProperties){
        if (items == null) {
            items = new HashMap<>();
        }

        if (StringUtils.isEmpty(itemProperties.getName())) {
            throw new AnyTxnCommonException(AnyTxnCommonRespCode.P_ERR, CommonRepDetail.CACHE_KEY_NULL);
        }

        if (items.containsKey(itemProperties.getName())) {
            log.info("缓存模块【{}】已注册，已注册模块配置已替换成最新", itemProperties.getName());
        } else {
            log.info("注册缓存模块，名称：{}，前缀：{}， 是否开启发布：{}, 是否开始监听{}", itemProperties.getName(), itemProperties.getPrefixKey(), itemProperties.getAblePublish(), itemProperties.getAbleListen());
        }
        items.put(itemProperties.getName(), itemProperties);

        return items.size();
    }

    /**
     * 是否开启监听缓存
     */
    public boolean ableListenCache(){
        return getAbleCache() && getAbleListen() && !CollectionUtils.isEmpty(getActiveProperties());
    }

    /**
     * 是否开启监听缓存
     */
    public boolean ablePublishCache(){
        return getAbleCache() && getAblePublish() && !CollectionUtils.isEmpty(getWaitProperties());
    }

    /**
     * 获取有监听能力的模块集合
     */
    public List<ItemProperties> getActiveProperties(){
        if (getAbleListen() && !CollectionUtils.isEmpty(items)) {
            return items.values().stream().filter(ItemProperties::ableListen).collect(Collectors.toList());
        }

        return null;
    }

    /**
     * 获取有发布能力的模块集合
     */
    public List<ItemProperties> getWaitProperties(){
        if (getAblePublish() && !CollectionUtils.isEmpty(items)) {
            return items.values().stream().filter(ItemProperties::ablePublish).collect(Collectors.toList());
        }

        return null;
    }

    /**
     * 根据名称获取子模块
     */
    public ItemProperties getItemByName(String name){
        if (!CollectionUtils.isEmpty(items) && !StringUtils.isEmpty(name)) {
            return items.get(name);
        }

        return null;
    }
}
