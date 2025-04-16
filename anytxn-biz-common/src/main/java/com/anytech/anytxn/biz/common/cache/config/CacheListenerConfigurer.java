package com.anytech.anytxn.biz.common.cache.config;

import com.anytech.anytxn.biz.common.cache.listener.CacheRefreshListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author peidong.meng
 * @date 2021/2/3
 */
@Slf4j
@Configuration
@Import(CacheNacosProperties.class)
public class CacheListenerConfigurer {

    @Bean
    @ConditionalOnClass
    @ConditionalOnProperty(prefix = "anytxn.cache.root", name = {"able-cache","able-listen"}, havingValue = "true")
    public CacheRefreshListener cacheRefreshListener(ContextRefresher contextRefresher,
                                                     CacheNacosProperties cacheNacosProperties){
        log.info("|------------- cache refresh -------------|");
        log.info("    ~ cache refresh listener open ~");
        log.info("|------------- cache refresh -------------|");
        return new CacheRefreshListener(contextRefresher, cacheNacosProperties);
    }
}
