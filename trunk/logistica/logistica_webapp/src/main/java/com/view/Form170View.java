package com.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class Form170View implements Serializable {

	private Long id;

	private String numero;

	private Date fecha;

	public Form170View(Long id, String numero, Date fecha) {
		super();
		this.id = id;
		this.numero = numero;
		this.fecha = fecha;
	}

	public Form170View() {
		this(null, null, null);
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public boolean equals(Object detalleHojaRuta) {
		boolean ret = true;
		Form170View detalle2 = (Form170View) detalleHojaRuta;
		if (numero != null && detalle2.getNumero() != null) {
			ret = numero.equalsIgnoreCase(detalle2.getNumero());
		} else if (numero == null && detalle2.getNumero() == null) {
			ret = true;
		} else {
			ret = false;
		}

		ret = ret && fecha == detalle2.getFecha();
		return ret;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Form170View detalle = new Form170View(id, numero, fecha);
		return detalle;
	}
}
