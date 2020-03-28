package com.formacionbdi.springboot.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringbootServiciosConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosConfigServerApplication.class, args);
	}

}
