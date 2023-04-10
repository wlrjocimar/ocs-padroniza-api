package com.cenop4011.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = "com.cenop4011.security.repositories", transactionManagerRef = "transactionManager")
public class DbAthenticationConfig {
	
	
	
		@Bean(name = "authDataSource")
		@Primary
		@ConfigurationProperties(prefix="auth.datasource")
		public DataSource slipDataSource() {
			return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver").build();
		}
		
		

		
		
		@Bean(name="entityManagerFactory")
		@Primary
		public LocalContainerEntityManagerFactoryBean authEntityManager(EntityManagerFactoryBuilder builder, @Qualifier("authDataSource") DataSource dataSource) {
			Map<String , Object> properties = new HashMap<>();
			properties.put("hibernate.hbm2ddl.auto", "update");
			properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
			return builder.dataSource(dataSource).properties(properties).packages("com.cenop4011.security.models").persistenceUnit("AuthPU").build();
			
		}
		
		@Bean(name="transactionManager")
		public PlatformTransactionManager transactionManagerAuth(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory ) {
			
			
			
			
			return new  JpaTransactionManager(entityManagerFactory);
			
		}
		
		
}

