package com.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Movil;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MovilPropietarioDetalleView implements Serializable {

	private Long id;

	private Date fechaTitularDesde;

	private Date fechaCedulaVerde;

	private Integer numeroCedulaVerde;

	private Integer numeroTitulo;

	private Movil movil;

	private String titularRegistral;

	public MovilPropietarioDetalleView(Long id, Date fechaTitularDesde,
			Date fechaCedulaVerde, Integer numeroCedulaVerde,
			Integer numeroTitulo, Movil movil, String titularRegistral) {
		super();
		this.id = id;
		this.fechaTitularDesde = fechaTitularDesde;
		this.fechaCedulaVerde = fechaCedulaVerde;
		this.numeroCedulaVerde = numeroCedulaVerde;
		this.numeroTitulo = numeroTitulo;
		this.movil = movil;
		this.titularRegistral = titularRegistral;
	}

	public MovilPropietarioDetalleView() {
		this(null, null, null, null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaTitularDesde() {
		return fechaTitularDesde;
	}

	public void setFechaTitularDesde(Date fechaTitularDesde) {
		this.fechaTitularDesde = fechaTitularDesde;
	}

	public Date getFechaCedulaVerde() {
		return fechaCedulaVerde;
	}

	public void setFechaCedulaVerde(Date fechaCedulaVerde) {
		this.fechaCedulaVerde = fechaCedulaVerde;
	}

	public Integer getNumeroCedulaVerde() {
		return numeroCedulaVerde;
	}

	public void setNumeroCedulaVerde(Integer numeroCedulaVerde) {
		this.numeroCedulaVerde = numeroCedulaVerde;
	}

	public Integer getNumeroTitulo() {
		return numeroTitulo;
	}

	public void setNumeroTitulo(Integer numeroTitulo) {
		this.numeroTitulo = numeroTitulo;
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}

	public String getTitularRegistral() {
		return titularRegistral;
	}

	public void setTitularRegistral(String titularRegistral) {
		this.titularRegistral = titularRegistral;
	}

	@Override
	public boolean equals(Object detalleHojaRuta) {
		boolean ret = true;
		MovilPropietarioDetalleView detalle2 = (MovilPropietarioDetalleView) detalleHojaRuta;

		if (movil != null && detalle2.getMovil() != null) {
			return movil.equals(detalle2.getMovil());
		} else if (movil == null && detalle2.getMovil() == null) {
			ret = true;
		} else {
			ret = false;
		}

		if (numeroCedulaVerde != null
				&& detalle2.getNumeroCedulaVerde() != null) {
			ret = numeroCedulaVerde.intValue() == detalle2
					.getNumeroCedulaVerde().intValue();
		} else if (numeroCedulaVerde == null
				&& detalle2.getNumeroCedulaVerde() == null) {
			ret = true;
		} else {
			ret = false;
		}

		if (numeroTitulo != null && detalle2.getNumeroTitulo() != null) {
			ret = numeroTitulo.intValue() == detalle2.getNumeroTitulo()
					.intValue();
		} else if (numeroTitulo == null && detalle2.getNumeroTitulo() == null) {
			ret = true;
		} else {
			ret = false;
		}

		ret = ret && fechaTitularDesde == detalle2.getFechaTitularDesde();
		ret = ret && fechaCedulaVerde == detalle2.getFechaCedulaVerde();

		if (titularRegistral != null && detalle2.getTitularRegistral() != null) {
			ret = titularRegistral.equals(detalle2.getTitularRegistral());
		} else if (titularRegistral == null
				&& detalle2.getTitularRegistral() == null) {
			ret = true;
		} else {
			ret = false;
		}

		return ret;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		MovilPropietarioDetalleView detalle = new MovilPropietarioDetalleView(
				id, fechaTitularDesde, fechaCedulaVerde, numeroCedulaVerde,
				numeroTitulo, movil, titularRegistral);
		return detalle;
	}
}
