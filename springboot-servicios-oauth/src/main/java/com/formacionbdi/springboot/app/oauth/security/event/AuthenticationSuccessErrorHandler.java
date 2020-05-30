package com.formacionbdi.springboot.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher{

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	//se obtienen datos del usuario autenticado
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails)authentication.getPrincipal();
		String mensaje = "Success Login: "+ user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);
	}

	//se obtienen datos del usuario que se quiere autenticar
	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error en el Login: "+ exception.getMessage();
		System.out.println(mensaje);
		log.error(mensaje);
		
	}

}
