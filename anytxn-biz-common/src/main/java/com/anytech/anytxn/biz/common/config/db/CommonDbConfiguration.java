package com.anytech.anytxn.biz.common.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.alibaba.druid.util.Utils;
import com.anytech.anytxn.biz.common.config.monitor.DruidSqlMonitorWriteCsv;
import com.anytech.anytxn.biz.common.constant.PropsConstants;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.io.IOException;


/**
 * 公共库数据库配置
 * @author yxy
 */
@Configuration
//@EnableConfigurationProperties({CommonDbConfig.class})
public class CommonDbConfiguration {
    /** 公共库sqlSession配置 */
    public static final String SQLSESSIONTEMPLATE = "sqlSessionTemplateCommon";
    public static final String DATASOURCE = "commonDataSource";
    public static final String JDBCTEMPLATE = "commonJdbcTemplate";
//TODO   DruidDataSource
    @Bean(name = CommonDbConfiguration.DATASOURCE)
    @ConfigurationProperties(prefix = PropsConstants.PROPS_PREFIX+".datasource.common")
    public DataSource commonDataSource() {
        return  new DruidDataSource();

    }
    //TODO HikariDataSource
//  @Bean(name = CommonDbConfiguration.DATASOURCE)
//    public DataSource commonDataSource(CommonDbConfig commonDbConfig) {
//        return new HikariDataSource(commonDbConfig);
//    }
    @Bean
    public SqlSessionFactory sqlSessionFactoryCommon(@Qualifier(CommonDbConfiguration.DATASOURCE) DataSource commonDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(commonDataSource);
        factoryBean.setDatabaseIdProvider(databaseIdProvider());
        return factoryBean.getObject();
    }

    @Bean(name = CommonDbConfiguration.JDBCTEMPLATE)
    public NamedParameterJdbcTemplate commonJdbcTemplate(
            @Qualifier(CommonDbConfiguration.DATASOURCE) DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateCommon(@Qualifier(CommonDbConfiguration.DATASOURCE) DataSource commonDataSource) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryCommon(commonDataSource));
    }


    private DatabaseIdProvider databaseIdProvider() {
        return VendorDatabaseIdProviderBuilder.build();
    }

 /**
      * @Description druid监控spring
      * @Method 
      * @Params 
      * @Return 
      * @Date 2020/8/19
      **/
  
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        DruidStatInterceptor dsInterceptor = new DruidStatInterceptor();
        return dsInterceptor;
    }

    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPattern("jrx.anytxn.*.mapper.*");
        return pointcut;
    }

    @Bean
    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
        DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
        defaultPointAdvisor.setPointcut(druidStatPointcut);
        defaultPointAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointAdvisor;
    }

    @Bean
    public FilterRegistrationBean removeDruidAdFilter() throws IOException {
        String text= Utils.readFromResource("support/http/resources/js/common.js");
        final String newJs= text.replace("this.buildFooter();", "");
        // 新建一个过滤器注册器对象
        FilterRegistrationBean<Filter> registration= new FilterRegistrationBean<>();
        registration.addUrlPatterns("/druid/js/common.js");
        registration.setFilter((servletRequest, servletResponse, filterChain) ->{
            servletResponse.resetBuffer();
            servletResponse.getWriter().write(newJs);
        });

        return registration;

    }

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        //先配置管理后台的servLet，访问的入口为/druid/
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        return servletRegistrationBean;
    }

    @Bean
    @Qualifier("commonDbMonitorWriteCsv")
    public DruidSqlMonitorWriteCsv commonDbMonitorWriteCsv(@Qualifier(CommonDbConfiguration.DATASOURCE)DataSource commonDb){
        return new DruidSqlMonitorWriteCsv(commonDb, null, null);
    }
}