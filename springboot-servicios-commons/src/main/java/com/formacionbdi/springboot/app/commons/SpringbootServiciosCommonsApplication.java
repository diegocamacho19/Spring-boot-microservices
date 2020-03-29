package com.formacionbdi.springboot.app.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//desabilita la autoconfiguracion de una base de datos en un proyecto spring
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}) 
public class SpringbootServiciosCommonsApplication {

	//se debe quitar el metodo main para la clase commons ya que es un proyecto de libreria no uno que se va a ejecutar
	/*public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosCommonsApplication.class, args);
	}*/

}
