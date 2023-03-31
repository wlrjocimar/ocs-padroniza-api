package com.cenop4011.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public Docket api() {
    	return new Docket(DocumentationType.SWAGGER_2)
    			.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cenop4011"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(apiKey()));
    }
    
   
    private ApiKey apiKey() {
        return new ApiKey("Token Access", "X-Access-Token", "header");
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Padroniza do checklist ")
                .description("Modulo para Gestão")
                .build();
      }
   
    
}

