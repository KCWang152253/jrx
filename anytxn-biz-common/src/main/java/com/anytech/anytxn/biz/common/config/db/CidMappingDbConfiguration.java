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

import javax.sql.DataSource;


/**
 * 客户号映射数据库配置
 * @author yxy
 */
@Configuration
//@EnableConfigurationProperties({CidMappingDbConfig.class})
public class CidMappingDbConfiguration {

    /** 客户号映射数据库sqlSession配置 */
    public static final String SQLSESSIONTEMPLATE = "sqlSessionTemplateCidMapping";
    public static final String DATASOURCE = "cidMappingDataSource";

    @Bean(name = CidMappingDbConfiguration.DATASOURCE)
    @ConfigurationProperties(prefix = PropsConstants.PROPS_PREFIX+".datasource.cidmapping")
    public DataSource cidMappingDataSource( ) {
        return new DruidDataSource();
    }





    @Bean
    public SqlSessionFactory sqlSessionFactoryCidMapping(@Qualifier(CidMappingDbConfiguration.DATASOURCE) DataSource cidMappingDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(cidMappingDataSource);
        factoryBean.setDatabaseIdProvider(databaseIdProvider());
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateCidMapping(@Qualifier(CidMappingDbConfiguration.DATASOURCE) DataSource cidMappingDataSource) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryCidMapping(cidMappingDataSource));
    }


    private DatabaseIdProvider databaseIdProvider() {
        return VendorDatabaseIdProviderBuilder.build();
    }


    @Bean
    @Qualifier("cidMappingMonitorWriteCsv")
    public DruidSqlMonitorWriteCsv cidMappingMonitorWriteCsv(@Qualifier(CidMappingDbConfiguration.DATASOURCE)DataSource cidMappingDb){
        return new DruidSqlMonitorWriteCsv(null, null, cidMappingDb);
    }
}