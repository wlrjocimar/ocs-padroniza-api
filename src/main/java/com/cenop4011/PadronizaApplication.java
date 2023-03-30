package com.cenop4011;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
public class PadronizaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadronizaApplication.class, args);
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		System.out.println("TimeZone configurado :" + TimeZone.getDefault());
	}

}
