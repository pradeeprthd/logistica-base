package com.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class AutonomoView implements Serializable {

	private Long id;

	private String numeroAutonomo;

	private Date fechaAutonomo;

	public AutonomoView(Long id, String numeroAutonomo, Date fechaAutonomo) {
		super();
		this.id = id;
		this.numeroAutonomo = numeroAutonomo;
		this.fechaAutonomo = fechaAutonomo;
	}

	public AutonomoView() {
		this(null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroAutonomo() {
		return numeroAutonomo;
	}

	public void setNumeroAutonomo(String numeroAutonomo) {
		this.numeroAutonomo = numeroAutonomo;
	}

	public Date getFechaAutonomo() {
		return fechaAutonomo;
	}

	public void setFechaAutonomo(Date fechaAutonomo) {
		this.fechaAutonomo = fechaAutonomo;
	}

	@Override
	public boolean equals(Object detalleHojaRuta) {
		boolean ret = true;
		AutonomoView detalle2 = (AutonomoView) detalleHojaRuta;
		if (numeroAutonomo != null && detalle2.getNumeroAutonomo() != null) {
			ret = numeroAutonomo.equalsIgnoreCase(detalle2.getNumeroAutonomo());
		} else if (numeroAutonomo == null
				&& detalle2.getNumeroAutonomo() == null) {
			ret = true;
		} else {
			ret = false;
		}

		ret = ret && fechaAutonomo == detalle2.getFechaAutonomo();
		return ret;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		AutonomoView detalle = new AutonomoView(id, numeroAutonomo,
				fechaAutonomo);
		return detalle;
	}
}
