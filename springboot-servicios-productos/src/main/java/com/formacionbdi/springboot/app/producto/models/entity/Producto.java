

//ESTA CLASE SE DEBE ELLIMINAR POR QUE YA SE TIENE ESTO EN COMMONS CON TODO Y PAQUETE PORQUE ESTO YA NO SE VA A UTLIZAR


/*package com.formacionbdi.springboot.app.producto.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//Spring JPA

@Entity
@Table(name = "productos")
public class Producto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	private Double precio;
	
	@Column(name ="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	Transient indica que no esta en ningun campo o indicado en la base de datos 
	@Transient
	private Integer port;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
		
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	private static final long serialVersionUID = 1035371288287386882L;

}*/
