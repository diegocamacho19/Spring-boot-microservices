package com.formacionbdi.springboot.app.producto.models.service;

import java.util.List;

//import com.formacionbdi.springboot.app.producto.models.entity.Producto; anterior a que usaramos la clase commons
import com.formacionbdi.springboot.app.commons.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public void deleteById(Long id);
	
}
