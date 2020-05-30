package com.formacionbdi.springboot.app.zuul.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/*
 * @EnableResourceServer habilita la configuracion del servidor de recursos y se extiende de la clase ResourceServerConfigurerAdapter y se implementan dos metodos
 * @RefreshScope es para usar actuator
 * */

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Value("${config.secutiry.ouath.jwt.key}")
	private String jwtKey;
	
	//para el token
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}
	
	//para las rutas, para protegerlas en el servidor de recursos y antMatchers da permisos para todos por eso ponemos ruta de el token que siempre tenga el permiso
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//siempre las rutas expecificas van al principio para dejar las genericas al final con ** y el anyrequest es para que si alguna ruta no esta especificada requiera autenticacion
		
		http.authorizeRequests().antMatchers("/api/security/oauth/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/productos/listar","/api/items/listar", "/api/usuarios/usuarios").permitAll()
		.antMatchers(HttpMethod.GET, "/api/productos/ver/{id}", "/api/items/ver/{id}/cantidad/{cantidad}", "/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")
		.antMatchers("/api/productos/**", "/api/items/**", "/api/usuarios/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
		
		/*.antMatchers(HttpMethod.POST, "/api/productos/crear", "/api/items/crear", "/api/usuarios/usuarios").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/productos/editar/{id}", "/api/items/editar/{id}", "/api/usuarios/usuarios/{id}").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/productos/eliminar/{id}", "/api/items/eliminar/{id}", "/api/usuarios/usuarios/{id}").hasRole("ADMIN");*/
	}
	
	//el token tiene que ser identico que en el servidor donde se crea que es el microservicio oauth y con la misma firma
	//tiene que ser @Bean y esto se configura en spring security que es el metodo corsConfigurationSource()
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		//corsConfig.setAllowedOrigins(array); se configura una por una los origines permitidos
		corsConfig.addAllowedOrigin("*");
		corsConfig.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","OPTIONS"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		//el /* es para pasar el cors a todas las rutas
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}
	
	//esto es para que el cors quede global y no solo en spring security sino en toda la aplicacion, esto es como si fuera una configuracion no se llama en ninguna parte hablo del metodo FilterRegistrationBean
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // esto es para darle una prioridad alta
		return bean;
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
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}

	
}
