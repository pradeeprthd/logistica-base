package com.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import logistica.model.Localidad;
import logistica.type.UnidadMedidaEnum;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DetalleHojaRutaView implements Serializable {

	private Long id;

	@NotNull(message = "Valor requerido")
	@Size(min = 1, max = 200, message = "La dirección debe tener entre 1 y 200 caracteres.")
	private String direccion;

	@NotNull(message = "Valor requerido")
	private Localidad localidad;

	@NotNull(message = "Valor requerido")
	private UnidadMedidaEnum unidadMedida;

	@NotNull(message = "Valor requerido")
	private Integer cantidad;

	@NotNull(message = "Valor requerido")
	private Date fechaDesde;

	@NotNull(message = "Valor requerido")
	private Date fechaHasta;

	public DetalleHojaRutaView(Long id, String direccion, Localidad localidad,
			UnidadMedidaEnum unidadMedida, Integer cantidad, Date fechaDesde,
			Date fechaHasta) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.localidad = localidad;
		this.unidadMedida = unidadMedida;
		this.cantidad = cantidad;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
	}

	public DetalleHojaRutaView() {
		this(null, null, null, null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public UnidadMedidaEnum getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedidaEnum unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	@Override
	public boolean equals(Object detalleHojaRuta) {
		boolean ret = true;
		DetalleHojaRutaView detalle2 = (DetalleHojaRutaView) detalleHojaRuta;
		if (direccion != null && detalle2.getDireccion() != null) {
			ret = direccion.equalsIgnoreCase(detalle2.getDireccion());
		} else if (direccion == null && detalle2.getDireccion() == null) {
			ret = true;
		} else {
			ret = false;
		}
		if (localidad != null && detalle2.getLocalidad() != null) {
			ret = ret
					& localidad.getID().intValue() == detalle2.getLocalidad()
							.getID().intValue();
		} else if (localidad == null && detalle2.getLocalidad() == null) {
			ret = true;
		} else {
			ret = false;
		}
		ret = ret && unidadMedida == detalle2.getUnidadMedida();
		ret = ret && cantidad == detalle2.getCantidad();
		ret = ret && fechaDesde == detalle2.getFechaDesde();
		ret = ret && fechaHasta == detalle2.getFechaHasta();
		return ret;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		DetalleHojaRutaView detalle = new DetalleHojaRutaView(id, direccion,
				localidad, unidadMedida, cantidad, fechaDesde, fechaHasta);
		return detalle;
	}

}
