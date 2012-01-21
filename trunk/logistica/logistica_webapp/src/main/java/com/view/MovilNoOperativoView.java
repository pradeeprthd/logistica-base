package com.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;

import logistica.model.Movil;
import logistica.type.EstadoMovilEnum;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MovilNoOperativoView implements Serializable {

	private Long id;

	@NotNull(message = "Valor requerido")
	private Movil movil;

	@NotNull(message = "Valor requerido")
	private Date fechaDesde;

	@NotNull(message = "Valor requerido")
	private Date fechaHasta;

	@NotNull(message = "Valor requerido")
	private EstadoMovilEnum estadoMovilEnum;

	public MovilNoOperativoView(Long id, Movil movil, Date fechaDesde,
			Date fechaHasta, EstadoMovilEnum estadoMovilEnum) {
		super();
		this.id = id;
		this.movil = movil;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.estadoMovilEnum = estadoMovilEnum;
	}

	public MovilNoOperativoView() {
		this(null, null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public EstadoMovilEnum getEstadoMovilEnum() {
		return estadoMovilEnum;
	}

	public void setEstadoMovilEnum(EstadoMovilEnum estadoMovilEnum) {
		this.estadoMovilEnum = estadoMovilEnum;
	}

}
