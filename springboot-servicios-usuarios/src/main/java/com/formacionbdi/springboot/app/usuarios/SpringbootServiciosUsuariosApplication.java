package com.formacionbdi.springboot.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.formacionbdi.springboot.app.usuarios.commons.models.entity"})
public class SpringbootServiciosUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosUsuariosApplication.class, args);
	}

}
