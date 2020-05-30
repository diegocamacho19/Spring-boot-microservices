package com.formacionbdi.springboot.app.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.formacionbdi.springboot.app.usuarios.commons.models.entity.Role;
import com.formacionbdi.springboot.app.usuarios.commons.models.entity.Usuario;


/*
 * Con esta clase se expone el id de la clase Usuario y Rol de lo que esta agregado en el dao que es @RepositoryRestResource
 * */
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Role.class);
	}
	
}
