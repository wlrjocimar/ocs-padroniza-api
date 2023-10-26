package com.cenop4011.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UsoDadosJdbcConfig {

    @Bean(name = "usoDadosDataSource")
    @ConfigurationProperties(prefix = "usodados.datasource")
    public DataSource usoDadosDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "usoDadosJdbcTemplate")
    public JdbcTemplate usoDadosJdbcTemplate(@Qualifier("usoDadosDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
