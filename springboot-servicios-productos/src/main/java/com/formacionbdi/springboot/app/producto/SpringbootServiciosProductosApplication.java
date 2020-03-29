package com.formacionbdi.springboot.app.producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
//@EntityScan({"com.formacionbdi.springboot.app.commons.entity"})  //esto es para decirle a la aplicacion que tambien me escanee otros packages de common que tambien esta en maven
public class SpringbootServiciosProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosProductosApplication.class, args);
	}
}
