package com.formacionbdi.springboot.app.usuarios.commons.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String username;
	private String password;
	private Boolean enabled;
	private String nombre;
	private String apellido;

	@Column(unique = true, length = 100)
	private String email;

	// esta LAZY and EAGER, LAZY solo trae los usuarios y EAGER trae usuarios y
	// roles pero LAZY es lo recomendado porque si necesitamos roles invocamos
	// getRoles

	/*
	 * sirve para personalizar el nombre de la tabla intermedia, y el nombre de las
	 * llaves foraneas de la relacion, osea los id de las dos tablas y el contraint
	 * es para definir que los id sean unicos en la tabla relacionada, que un
	 * usuario no pueda tener el rol repetido
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "usuario_id", "role_id" }) })
	private List<Role> roles;

	private Integer intentos;

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static final long serialVersionUID = 4002221912401133094L;

}
