package com.formacionbdi.springboot.app.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	
	//busca un componente que este inyectado con UserDetailsService y lo inyecta aca, esto es la clase ya creada UsuarioService
	@Autowired
	private UserDetailsService usuarioService;
	
	//esto es parael manejo de errores de la clase AuthenticationSuccessErrorHandler del package event
	@Autowired
	private AuthenticationEventPublisher eventPublisher;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	//este Autowired es para que el auth pueda pasarse por parametro
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//con esto automaticamente encripta el password y lo compara con el password de la base de datos
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder())
		.and().authenticationEventPublisher(eventPublisher);
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
			
}
