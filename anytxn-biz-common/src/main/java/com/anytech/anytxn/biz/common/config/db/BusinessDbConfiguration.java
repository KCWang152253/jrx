package com.anytech.anytxn.biz.common.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.anytech.anytxn.biz.common.config.monitor.DruidSqlMonitorWriteCsv;
import com.anytech.anytxn.biz.common.constant.PropsConstants;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 业务数据库配置
 * @author yxy
 * @date 2020-02-17
 */
@Configuration
//@EnableConfigurationProperties({BusinessDbConfig.class})
public class BusinessDbConfiguration {

    /** 业务sqlSession的配置 */
    public static final String SQLSESSIONTEMPLATE = "sqlSessionTemplateBusiness";
    public static final String DATASOURCE = "businessDataSource";
    public static final String JDBCTEMPLATE = "bizJdbcTemplate";

    @Bean(name = BusinessDbConfiguration.DATASOURCE)
    @Primary
    @ConfigurationProperties(prefix = PropsConstants.PROPS_PREFIX+".datasource.business")
//        public DataSource businessDataSource(BusinessDbConfig businessDbConfig){
    public DataSource businessDataSource( ){
//        return new HikariDataSource(businessDbConfig);
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactoryBusiness(@Qualifier(BusinessDbConfiguration.DATASOURCE) DataSource businessDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(businessDataSource);
        factoryBean.setDatabaseIdProvider(databaseIdProvider());
        return factoryBean.getObject();
    }

    @Bean(name = BusinessDbConfiguration.JDBCTEMPLATE)
    @Primary
    public NamedParameterJdbcTemplate bizJdbcTemplate(
            @Qualifier(BusinessDbConfiguration.DATASOURCE) DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplateBusiness(@Qualifier(BusinessDbConfiguration.DATASOURCE) DataSource businessDataSource) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryBusiness(businessDataSource));
    }

    @Bean
    public DatabaseIdProvider databaseIdProvider() {
        return VendorDatabaseIdProviderBuilder.build();
    }

    @Bean
    @Qualifier("businessTransactionManager")
    @Primary
    public PlatformTransactionManager businessTransactionManager(@Qualifier("businessDataSource") DataSource primaryDataSource) {
        return new DataSourceTransactionManager(primaryDataSource);
    }

    @Bean
    @Qualifier("businessMonitorWriteCsv")
    public DruidSqlMonitorWriteCsv businessMonitorWriteCsv( @Qualifier(BusinessDbConfiguration.DATASOURCE)DataSource bussiessDb){
        return new DruidSqlMonitorWriteCsv(null, bussiessDb, null);
    }
}
