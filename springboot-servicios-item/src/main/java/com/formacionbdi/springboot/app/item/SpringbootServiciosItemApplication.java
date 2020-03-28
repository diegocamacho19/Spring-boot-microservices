package com.formacionbdi.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/*Histrix */
@EnableCircuitBreaker

@EnableEurekaClient
/*feign es otra forma de comunicarse entre aplicaciones spring boot*/
/* ribbon es client singular porque solo hay un llamado a un microservicio si fueran varios seria clients
 * y se le agrega el nombre del microservicio de ProductoClienteRest*/
/*
 * Se elimina ribbon client porque ya se encuentra en el servidor de eureka
 * @RibbonClient(name="servicio-productos")*/
@EnableFeignClients
@SpringBootApplication
public class SpringbootServiciosItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosItemApplication.class, args);
	}

}
