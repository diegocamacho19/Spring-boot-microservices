package com.formacionbdi.springboot.app.producto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.producto.models.entity.Producto;
import com.formacionbdi.springboot.app.producto.models.service.IProductoService;

@RestController
public class ProductoController {

	// esto es para llamar el application.properties de la aplicacion, hay dos
	// formas con Environment o con Value
	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;

	@Autowired
	private IProductoService productoService;

	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoService.findAll().stream().map(producto -> {
			/*
			 * esta es con Enviroment
			 * /producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			 * producto.setPort(Integer.parseInt(env.getProperty("port")));
			 */

			// Esto es con Value
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws Exception {
		Producto producto = productoService.findById(id);
		/*
		 * esto es con Envioment
		 * producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		 */
		producto.setPort(port);

		// ejemplo cuando hay una exepcion con hidrix y la tolerancia de fallos
		/*
		 * boolean ok = false; if(ok == false) { throw new Exception
		 * ("No se pudo cargar el producto"); }
		 */

		/*try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	//@RequestBody pobla los datos de json a el metodo producto, Created es 201
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDb = productoService.findById(id);
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		return productoService.save(productoDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}
	
}
