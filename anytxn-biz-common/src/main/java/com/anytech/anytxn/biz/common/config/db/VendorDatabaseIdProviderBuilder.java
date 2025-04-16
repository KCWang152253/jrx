package com.anytech.anytxn.biz.common.config.db;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;

import java.util.Properties;

/**
 * 静态idProviderBuilder
 * @author yxy
 * @version 2.0
 * @date 2020/2/18
 */
public class VendorDatabaseIdProviderBuilder {

    private VendorDatabaseIdProviderBuilder() {

    }

    public static DatabaseIdProvider build() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.setProperty("Oracle", "oracle");
        properties.setProperty("MySQL", "mysql");
        properties.setProperty("DB2", "db2");
        properties.setProperty("Derby", "derby");
        properties.setProperty("H2", "h2");
        properties.setProperty("HSQL", "hsql");
        properties.setProperty("Informix", "informix");
        properties.setProperty("MS-SQL", "ms-sql");
        properties.setProperty("PostgreSQL", "postgresql");
        properties.setProperty("Sybase", "sybase");
        properties.setProperty("Hana", "hana");
        databaseIdProvider.setProperties(properties);
        return databaseIdProvider;
    }
}
