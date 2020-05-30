package com.formacionbdi.springboot.app.oauth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	//con esto traigo los otros datos que van a ser agregados al token
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	
	
	//esto es para traer las propiedades de application.propeties del github
	@Autowired
	private Environment env;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()"); // permitAll es de spring security
	}
	
	// el refresh token es para obtener un token nuevo justo antes de que caduque 

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(env.getProperty("config.secutiry.ouath.client.id"))
		.secret(passwordEncoder.encode(env.getProperty("config.secutiry.ouath.client.secret")))
		.scopes("read","write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600);
		
		/*clients.inMemory().withClient("frontendapp")
		.secret(passwordEncoder.encode("12345"))
		.scopes("read","write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600).and().withClient("androidapp")
		.secret(passwordEncoder.encode("12345"))
		.scopes("read","write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600);*/
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {		
		// el accessTokenConverter() es la informacion por defecto
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		
		// el tokenEnhancerChain se le agrega al endpoint con todas las configuraciones
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.tokenEnhancer(tokenEnhancerChain);
		
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		// para poder almacenar el token necesitamos el componente de accessTokenConverter
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		//esta es la llave o la firma
		tokenConverter.setSigningKey(env.getProperty("config.secutiry.ouath.jwt.key"));
		return tokenConverter;
	}
	
	
}
