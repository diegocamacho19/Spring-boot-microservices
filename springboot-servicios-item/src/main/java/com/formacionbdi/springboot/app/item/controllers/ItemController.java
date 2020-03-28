package com.formacionbdi.springboot.app.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;

//@RefreshScope es para actualizar los campos de las properties y se necesita para que esto funcione la dependencia actuator
@RefreshScope
@RestController
public class ItemController {
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	//El Enviroment sirve para traer todo el entorno de configuracion
	@Autowired
	private Environment env;

	@Autowired
	/* En el qualifier defino cual de las implementaciones se va a ejecutar si es feign o resttemplate, por el nombre del service*/
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@GetMapping("/listar")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable long id, @PathVariable Integer cantidad ) {
		return itemService.findById(id, cantidad);
	}
	
	//el metodo debe tener el mismo nombvre del Hystrix y se retorna un metodo alternativo	
	public Item metodoAlternativo(long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Camara Sony Alternativo");
		producto.setPrecio(500.0);
		item.setProducto(producto);		
		return item;
	}
	
	//obtenerConfig(@Value("${server.port}") String puerto) sel value se manda como atributo del puerto osea predeterminado
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
		Map<String,String> json = new HashMap<>();
		log.info(texto);
		json.put("texto", texto);
		json.put("puerto", puerto);
		
		//Compara si hay algun profile y si es de desarrollo, esta configuracion se encuentra en el bootstrap.propertiers
		if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		return new ResponseEntity<Map<String,String>>(json, HttpStatus.OK);
	}
	
	
	
}