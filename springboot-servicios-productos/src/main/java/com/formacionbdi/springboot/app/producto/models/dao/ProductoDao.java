package com.formacionbdi.springboot.app.producto.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.producto.models.entity.Producto;

// CrudRepository<Producto, Long> la clase para llamar el crud
public interface ProductoDao extends CrudRepository<Producto, Long>{
	
}
