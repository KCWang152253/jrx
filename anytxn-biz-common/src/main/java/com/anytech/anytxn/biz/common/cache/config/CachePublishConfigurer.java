package com.anytech.anytxn.biz.common.cache.config;

import com.anytech.anytxn.biz.common.cache.publish.CacheRefreshPublish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
public class CachePublishConfigurer {

    @Bean
    @ConditionalOnProperty(prefix = "anytxn.cache.root", name = {"able-cache","able-publish"}, havingValue = "true")
    public CacheRefreshPublish cacheRefreshPublish(CacheNacosProperties cacheNacosProperties){
        log.info("|------------- cache refresh -------------|");
        log.info("    ~ cache refresh publish open ~");
        log.info("|------------- cache refresh -------------|");
        return new CacheRefreshPublish(cacheNacosProperties);
    }
}
