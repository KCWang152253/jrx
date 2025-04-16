package com.anytech.anytxn.biz.common.config.db;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;


/**
 * @author sukang
 *
 * sharding jdbc配置
 */
@Configuration
public class ShardingDbConfiguration {
    /**
     * sharding-jdbc 广播表数据源配置
    */
    public static final String SQLSESSIONTEMPLATE = "sqlSessionTemplateShardingSphere";
    public static final String DATASOURCE = "shardingSphereDataSource";
    public static final String JDBCTEMPLATE = "shardingSphereJdbcTemplate";

    @Bean
    public SqlSessionFactory shardingSphereSqlSessionFactory(@Qualifier(ShardingDbConfiguration.DATASOURCE) DataSource shardingSphereDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(shardingSphereDataSource);
        factoryBean.setDatabaseIdProvider(databaseIdProvider());
        return factoryBean.getObject();
    }

    @Bean(name = ShardingDbConfiguration.JDBCTEMPLATE)
    public NamedParameterJdbcTemplate shardingSphereJdbcTemplate(
            @Qualifier(ShardingDbConfiguration.DATASOURCE) DataSource shardingSphereDataSource) {
        return new NamedParameterJdbcTemplate(shardingSphereDataSource);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateShardingSphere(@Qualifier(ShardingDbConfiguration.DATASOURCE) DataSource shardingSphereDataSource) throws Exception {
        return new SqlSessionTemplate(shardingSphereSqlSessionFactory(shardingSphereDataSource));
    }


    private DatabaseIdProvider databaseIdProvider() {
        return VendorDatabaseIdProviderBuilder.build();
    }



}