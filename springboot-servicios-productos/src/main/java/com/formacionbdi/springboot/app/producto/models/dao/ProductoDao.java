package com.formacionbdi.springboot.app.producto.models.dao;

import org.springframework.data.repository.CrudRepository;

//import com.formacionbdi.springboot.app.producto.models.entity.Producto; antes de usar el proyecto commos
import com.formacionbdi.springboot.app.commons.models.entity.Producto;
// CrudRepository<Producto, Long> la clase para llamar el crud
public interface ProductoDao extends CrudRepository<Producto, Long>{
	
}
