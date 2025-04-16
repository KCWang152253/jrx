package com.anytech.anytxn.biz.common.cache.listener;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.anytech.anytxn.biz.common.cache.config.CacheNacosProperties;
import com.anytech.anytxn.biz.common.cache.config.ItemProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * 缓存清理监听
 * @author peidong.meng
 * @date 2021/1/28
 */
@Slf4j
public class CacheRefreshListener implements ApplicationListener<RefreshEvent>, ApplicationContextAware, Ordered {

    /**
     * 上下文刷新器
     */
    private ContextRefresher refresh;
    /**
     * spring上下文，用于获取配置
     * 通过ApplicationContextAware获取
     */
    private ApplicationContext context;

    /**
     * 缓存配置
     */
    private CacheNacosProperties cacheNacosProperties;

    public CacheRefreshListener(ContextRefresher refresh, CacheNacosProperties cacheNacosProperties) {
        this.refresh = refresh;
        this.cacheNacosProperties = cacheNacosProperties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void onApplicationEvent(RefreshEvent refreshEvent) {
        // 未开启缓存或无模块时不处理配置
        if (!cacheNacosProperties.ableListenCache()) {
            this.refresh.refresh();
        } else {
            // 解析更新前配置
            Map<String, Object> beforeProperty = sourceToMap(((ConfigurableEnvironment) context.getEnvironment()).getPropertySources());

            // 确保该监听器是第一个执行刷新，否则获取不到旧的配置文件
            this.refresh.refresh();

            // 解析更新后配置
            Map<String, Object> afterProperty = sourceToMap(((ConfigurableEnvironment) context.getEnvironment()).getPropertySources());

            // 获取更新模块, 此步骤必存在有效模块
            List<ItemProperties> items = cacheNacosProperties.getActiveProperties();
            // 遍历配置判断是否更新
            afterProperty.forEach((x, y) -> {
                // 遍历所有模块
                for (ItemProperties item : items) {
                    // 是此模块的前缀且配置发生了变更
                    if (x.startsWith(item.getPrefixKey()) && isChangeKeys(beforeProperty, afterProperty, x)) {
                        try {
                            // 剔除前缀
                            String key = x.replace(item.getPrefixKey() + ".", "");
                            if (item.getRefreshHandle() != null) {
                                // 刷新处理
                                log.info("|------- cache【{}】refresh handle start -------|", key);
                                item.getRefreshHandle().refresh(key);
                                log.info("|------- cache【{}】refresh handle end -------|", key);
                            }
                        } catch (Exception e){
                            log.error("处理更新缓存异常！模块: {}, 异常内容: {}", item.getName(), e.getMessage(), e);
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getOrder() {
        // 最高优先级
        return 0;
    }

    /**
     * 判断key是否需要更新
     */
    public boolean isChangeKeys(Map<String, Object> before, Map<String, Object> after, String key){
        //过期
        boolean timeout = before.containsKey(key) && !after.containsKey(key);
        //新增
        boolean cred = !before.containsKey(key) && after.containsKey(key);
        //刷新
        boolean refresh = before.containsKey(key) && after.containsKey(key) && !before.get(key).toString().equals(after.get(key).toString());

        return timeout || cred || refresh;
    }

    /**
     * 解析配置成map
     * @param source
     * @return
     */
    private Map<String, Object> sourceToMap(MutablePropertySources source){
        Map<String, Object> result = Maps.newHashMap();
        List<PropertySource> propertySources = Lists.newArrayList();
        for (PropertySource<?> propertySource : source) {
            propertySources.add(propertySource);
        }

        for (PropertySource propertySource : propertySources) {
            if (propertySource instanceof EnumerablePropertySource) {
                for (String key : ((EnumerablePropertySource<?>) propertySource).getPropertyNames()) {
                    result.put(key, propertySource.getProperty(key));
                }
            }
        }

        return result;
    }
}
