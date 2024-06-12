package viettel.gpmn.platform.core.configs.tenant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MultiTenantManager {

    private final String defaultDriver;
    private final String defaultUrl;
    private final String defaultUsername;
    private final String defaultPassword;

    private final ThreadLocal<String> currentTenant = new ThreadLocal<>();
    private final Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();
    private final DataSourceProperties properties;

    private AbstractRoutingDataSource multiTenantDataSource;

    public MultiTenantManager(
            @Value("${spring.datasource.driver-class-name}") String defaultDriver,
            @Value("${spring.datasource.url}") String defaultUrl,
            @Value("${spring.datasource.username}") String defaultUsername,
            @Value("${spring.datasource.password}") String defaultPassword,
            DataSourceProperties properties) {
        this.defaultDriver = defaultDriver;
        this.defaultUrl = defaultUrl;
        this.defaultUsername = defaultUsername;
        this.defaultPassword = defaultPassword;
        this.properties = properties;
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
                .driverClassName(properties.getDriverClassName())
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
        defaultDataSource.setDriverClassName(defaultDriver);
        defaultDataSource.setUrl(defaultUrl);
        defaultDataSource.setUsername(defaultUsername);
        defaultDataSource.setPassword(defaultPassword);
        return defaultDataSource;
    }
}
