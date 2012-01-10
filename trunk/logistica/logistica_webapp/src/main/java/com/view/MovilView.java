package com.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MovilView implements Serializable {
	private Long id;

	@NotNull(message = "Valor requerido")
	private Long numeroMovil;

	@NotNull(message = "Valor requerido")
	@Size(min = 6, max = 20, message = "El número de patente debe tener entre 6 y 20 caracteres.")
	private String patente;

	private String descripcion;

	public MovilView(Long id, Long numeroMovil, String patente,
			String descripcion) {
		super();
		this.id = id;
		this.patente = patente;
		this.numeroMovil = numeroMovil;
		this.descripcion = descripcion;
	}

	public MovilView() {
		this(null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Long getNumeroMovil() {
		return numeroMovil;
	}

	public void setNumeroMovil(Long numeroMovil) {
		this.numeroMovil = numeroMovil;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
