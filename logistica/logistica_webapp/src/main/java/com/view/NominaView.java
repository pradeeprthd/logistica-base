package com.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class NominaView implements Serializable {

	private Long id;

	private String numero;

	private String observacion;

	private Date fecha;

	public NominaView(Long id, String numero, String observacion, Date fecha) {
		super();
		this.id = id;
		this.numero = numero;
		this.fecha = fecha;
		this.observacion = observacion;
	}

	public NominaView() {
		this(null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public boolean equals(Object detalleHojaRuta) {
		boolean ret = true;
		NominaView detalle2 = (NominaView) detalleHojaRuta;
		if (numero != null && detalle2.getNumero() != null) {
			ret = numero.equalsIgnoreCase(detalle2.getNumero());
		} else if (numero == null && detalle2.getNumero() == null) {
			ret = true;
		} else {
			ret = false;
		}

		if (observacion != null && detalle2.getObservacion() != null) {
			ret = observacion.equalsIgnoreCase(detalle2.getObservacion());
		} else if (observacion == null && detalle2.getObservacion() == null) {
			ret = true;
		} else {
			ret = false;
		}

		ret = ret && fecha == detalle2.getFecha();
		return ret;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		NominaView detalle = new NominaView(id, numero, observacion, fecha);
		return detalle;
	}
}
