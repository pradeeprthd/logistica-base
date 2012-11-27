package com.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import logistica.type.AsignacionMovilEnum;
import logistica.type.EstadoEnum;

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

	@NotNull(message = "Valor requerido")
	private AsignacionMovilEnum asignacionMovil;

	@NotNull(message = "Valor requerido")
	private EstadoEnum estado;

	@NotNull(message = "Valor requerido")
	private Date fechaIngreso;

	private Date fechaEgreso;

	private Boolean controlado;

	private Boolean comodato;

	public MovilView(Long id, Long numeroMovil, String patente,
			String descripcion, AsignacionMovilEnum asignacionMovil,
			EstadoEnum estado, Date fechaIngreso, Date fechaEgreso,
			Boolean controlado, Boolean comodato) {
		super();
		this.id = id;
		this.patente = patente;
		this.numeroMovil = numeroMovil;
		this.descripcion = descripcion;
		this.asignacionMovil = asignacionMovil;
		this.estado = estado;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.controlado = controlado;
		this.comodato = comodato;
	}

	public MovilView() {
		this(null, null, null, null, null, null, null, null, null, null);
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

	public AsignacionMovilEnum getAsignacionMovil() {
		return asignacionMovil;
	}

	public void setAsignacionMovil(AsignacionMovilEnum asignacionMovil) {
		this.asignacionMovil = asignacionMovil;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public Boolean getControlado() {
		return controlado;
	}

	public void setControlado(Boolean controlado) {
		this.controlado = controlado;
	}

	public Boolean getComodato() {
		return comodato;
	}

	public void setComodato(Boolean comodato) {
		this.comodato = comodato;
	}
}
