package com.formacionbdi.springboot.app.producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServiciosProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosProductosApplication.class, args);
	}

}
