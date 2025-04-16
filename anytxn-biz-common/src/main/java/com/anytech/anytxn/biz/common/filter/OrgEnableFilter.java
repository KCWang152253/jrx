package com.anytech.anytxn.biz.common.filter;

import com.anytech.anytxn.biz.common.util.OrgNumberUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.servlet.Filter;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({OrgEnableFilter.OrgEnableConfigurer.class, OrgNumberUtil.class})
public @interface OrgEnableFilter {

    @Configuration
//    @ConditionalOnProperty(prefix = "org", name = "enable", havingValue = "true")
    class OrgEnableConfigurer{
        @Bean
        public FilterRegistrationBean orgFilterRegistrationBean(){
            FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
            filterRegistrationBean.setFilter(orgFilter());
            filterRegistrationBean.addUrlPatterns("/anytxn/v2/api/*");
            filterRegistrationBean.setOrder(1);
            filterRegistrationBean.setAsyncSupported(true);
            return filterRegistrationBean;
        }

        @Bean
        public Filter orgFilter(){
            return new OrgFilter();
        }
    }
}
