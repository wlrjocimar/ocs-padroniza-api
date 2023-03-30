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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "padronizaEntityManager", basePackages = "com.cenop4011.padroniza.repositories",transactionManagerRef = "padronizaTransactionManager")
public class DbPadronizaConfig {
	
	
	

		
	
	@Bean(name= "padronizaDataSource")
	@ConfigurationProperties(prefix="padroniza.datasource")
	public DataSource padronizaDataSource() {
		
		return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver").build();
	}

	
	
	
	
	@Bean(name="padronizaEntityManager")
	public LocalContainerEntityManagerFactoryBean padronizaAcpEntityManager(EntityManagerFactoryBuilder builder, @Qualifier("padronizaDataSource") DataSource dataSource) {
		Map<String , Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		return builder.dataSource(dataSource).properties(properties).packages("com.cenop4011.padroniza.models").persistenceUnit("PadronizaPU").build();
		
	}
	
	
	@Bean(name="padronizaTransactionManager")
	public PlatformTransactionManager transactionManagerCalculo(@Qualifier("padronizaEntityManager") EntityManagerFactory entityManagerFactory ) {
		
		
		
		
		return new  JpaTransactionManager(entityManagerFactory);
		
	}
	
	

}
