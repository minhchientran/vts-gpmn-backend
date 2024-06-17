package viettel.gpmn.platform.core.configs.tenant;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class TenantManager {

    private final DataSourceProperties dataSourceProperties;

    private final ThreadLocal<String> currentTenant = new ThreadLocal<>();
    private final Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();

    private AbstractRoutingDataSource multiTenantDataSource;

    public TenantManager(
            DataSourceProperties dataSourceProperties
    ) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    public DataSource dataSource() {
        multiTenantDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return currentTenant.get();
            }
        };
        multiTenantDataSource.setTargetDataSources(tenantDataSources);
        multiTenantDataSource.setDefaultTargetDataSource(defaultDataSource());
        multiTenantDataSource.afterPropertiesSet();
        return multiTenantDataSource;
    }

    public void addTenant(String tenantId, String url, String username, String password) {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(url)
                .username(username)
                .password(password)
                .build();
        tenantDataSources.put(tenantId, dataSource);
        multiTenantDataSource.afterPropertiesSet();
    }

    public void setCurrentTenant(String tenantId) {
        currentTenant.set(tenantId);
    }

    private DriverManagerDataSource defaultDataSource() {
        DriverManagerDataSource defaultDataSource = new DriverManagerDataSource();
        defaultDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        defaultDataSource.setUrl(dataSourceProperties.getUrl());
        defaultDataSource.setUsername(dataSourceProperties.getUsername());
        defaultDataSource.setPassword(dataSourceProperties.getPassword());
        return defaultDataSource;
    }

}
