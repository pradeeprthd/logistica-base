package com.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Sucursal;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DetalleSucursalView implements Serializable {

	private Long id;
	private Sucursal sucursal;
	private Integer cantidadMoviles;
	private List<DetalleAsignacionView> detalleAsignacionViewList;

	public DetalleSucursalView(Long id, Sucursal sucursal,
			Integer cantidadMoviles,
			List<DetalleAsignacionView> detalleAsignacionViewList) {
		super();
		this.id = id;
		this.sucursal = sucursal;
		this.cantidadMoviles = cantidadMoviles;
		this.detalleAsignacionViewList = detalleAsignacionViewList;
	}

	public DetalleSucursalView() {
		this(null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Integer getCantidadMoviles() {
		return cantidadMoviles;
	}

	public void setCantidadMoviles(Integer cantidadMoviles) {
		this.cantidadMoviles = cantidadMoviles;
	}

	public List<DetalleAsignacionView> getDetalleAsignacionViewList() {
		return detalleAsignacionViewList;
	}

	public void setDetalleAsignacionViewList(
			List<DetalleAsignacionView> detalleAsignacionViewList) {
		this.detalleAsignacionViewList = detalleAsignacionViewList;
	}
}
