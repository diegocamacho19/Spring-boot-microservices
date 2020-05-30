package com.formacionbdi.springboot.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formacionbdi.springboot.app.usuarios.commons.models.entity.Usuario;

@FeignClient(name ="servicio-usuarios")
public interface UsuarioFeignClient {

	//esto llama al DAO de usuario y pone la ruta de busqueda como se consume en postman y no es @param sino @RequestParam
	//con el RequestParam enviamos el usuario para poder consumir el servicio
		@GetMapping("/usuarios/search/buscar-username")
		public Usuario findByUsername(@RequestParam String username);
}
