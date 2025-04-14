package com.anytech.anytxn.anyapi.server;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.actuate.health.DefaultHealthContributorRegistry;
import org.springframework.boot.actuate.health.HealthContributorRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


/**
 * @description:
 * @author: wangdaojian
 * @create: 2020-03-04 15:23
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
//@EnableDiscoveryClient
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.anytech.anytxn.anyapi.service"})
@ComponentScan(basePackages = {"com.anytech.anytxn.anyapi.controller"})
//@EnableFeignClients(basePackages = {"com.anytech.anytxn.transaction.feign","com.anytech.anytxn.cardholder.feign"})
@EnableMethodCache(basePackages = "com.anytech.anytxn")//项目主路径
@EnableCreateCacheAnnotation
public class AnyapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnyapiApplication.class, args);
    }

    /**
     * 监控配置bean
     */
    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}")String applicatonName,
                                                      @Value("${spring.cloud.client.ip-address}") String serverAddr,
                                                      @Value("${server.port}") String port) {
        return (registry) -> registry.config().commonTags("application", applicatonName.concat("-")
                .concat(serverAddr).concat(":").concat(port));
    }


//    @Bean
//    HealthContributorRegistry healthContributorRegistry(){
//        return new DefaultHealthContributorRegistry();
//    }
}
