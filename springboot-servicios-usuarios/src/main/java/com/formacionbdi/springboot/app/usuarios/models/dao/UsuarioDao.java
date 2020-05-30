package com.formacionbdi.springboot.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.formacionbdi.springboot.app.usuarios.commons.models.entity.Usuario;


//esto es para importar todos los metodos y crear un crud sin tanto codigo como en el servicio de productos
@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{
	
	//se pueden realizar consultas a travez del nombre metodo donde By define el Where en la consulta y el despues de By es el nombre del campo de la tabla
	
	/*
	 * RestResource se usa para ponerle un nombre diferente al metodo para ser llamado y el param se define el nombre del parametro
	 * */
	@RestResource(path="buscar-username")
	public Usuario findByUsername(@Param("username") String username);
	
	/*
	 * Asi se usa normalito para llamar el metodo con el nombre del metodo
	 * public Usuario findByUsername(String username);*/
	
	@Query("select u from Usuario  u where u.username=?1")
	public Usuario obtenerPorUsername(String username);
}
