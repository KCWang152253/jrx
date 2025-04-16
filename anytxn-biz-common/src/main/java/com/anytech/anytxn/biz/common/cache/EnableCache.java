package com.anytech.anytxn.biz.common.cache;

import com.alicp.jetcache.CacheBuilder;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.alicp.jetcache.autoconfigure.JetCacheAutoConfiguration;
import com.alicp.jetcache.embedded.EmbeddedCacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
import java.util.Map;

/**
 * @author peidong.meng
 * @date 2021/1/28
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EnableCache.CacheConfigurer.class, JetCacheAutoConfiguration.class})
public @interface EnableCache {

    @Slf4j
    @Configuration
    @EnableMethodCache(basePackages = {"com.anytech.anytxn.parameter","com.anytech.anytxn.rule.limit"})
    @EnableCreateCacheAnnotation
    class CacheConfigurer{

        @Bean("jetCacheRunner")
        public ApplicationRunner cacheRunner(GlobalCacheConfig globalCacheConfig){
            return args -> {
                if (globalCacheConfig != null) {
                    Map<String, CacheBuilder> localCacheBuilders = globalCacheConfig.getLocalCacheBuilders();

                    if (localCacheBuilders != null && !localCacheBuilders.isEmpty()) {
                        log.info("|---------------- jetCache ----------------|");
                        log.info("  jetCache config start ... ");
                        log.info("  enableMethodCache：{}", globalCacheConfig.isEnableMethodCache());
                        localCacheBuilders.forEach((k,b) -> {
                            log.info("  {}-limit：{}", k, ((EmbeddedCacheBuilder)b).getConfig().getLimit());
                            log.info("  {}-expireTime：{}", k, ((EmbeddedCacheBuilder)b).getConfig().getExpireAfterWriteInMillis());
                        });

                        log.info("|---------------- jetCache ----------------|");
                    }
                }
            };
        }
    }
}
