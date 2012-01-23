package com.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.SucursalCoto;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DetalleSucursalView implements Serializable,
		Comparable<DetalleSucursalView> {

	private Long id;
	private SucursalCoto sucursalCoto;
	private Integer cantidadMoviles;
	private List<DetalleAsignacionView> detalleAsignacionViewList;

	public DetalleSucursalView(Long id, SucursalCoto sucursalCoto,
			Integer cantidadMoviles,
			List<DetalleAsignacionView> detalleAsignacionViewList) {
		super();
		this.id = id;
		this.sucursalCoto = sucursalCoto;
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

	public SucursalCoto getSucursalCoto() {
		return sucursalCoto;
	}

	public void setSucursalCoto(SucursalCoto sucursalCoto) {
		this.sucursalCoto = sucursalCoto;
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

	public int compareTo(DetalleSucursalView o) {
		int ret = 0;
		if (sucursalCoto.getNumeroSucursal().intValue() == o.getSucursalCoto()
				.getNumeroSucursal().intValue()) {
			ret = 0;
		} else if (sucursalCoto.getNumeroSucursal().intValue() > o
				.getSucursalCoto().getNumeroSucursal().intValue()) {
			ret = 1;
		} else {
			ret = -1;
		}

		return ret;
	}
}
