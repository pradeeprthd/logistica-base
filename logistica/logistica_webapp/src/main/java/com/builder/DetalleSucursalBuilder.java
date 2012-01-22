package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.DetalleSucursal;

import com.view.DetalleSucursalView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DetalleSucursalBuilder extends
		BaseBuilder<DetalleSucursalView, DetalleSucursal> {

	@ManagedProperty("#{detalleAsignacionBuilder}")
	private DetalleAsignacionBuilder detalleAsignacionBuilder;

	@Override
	public DetalleSucursal toDomain(DetalleSucursalView view) {
		return new DetalleSucursal(view.getId(), view.getSucursal(),
				view.getCantidadMoviles(),
				detalleAsignacionBuilder.toDomain(view
						.getDetalleAsignacionViewList()));
	}

	@Override
	public DetalleSucursalView toView(DetalleSucursal model) {
		return new DetalleSucursalView(model.getID(), model.getSucursal(),
				model.getCantidadMoviles(),
				detalleAsignacionBuilder.toView(model
						.getDetalleAsignacionList()));
	}

	public DetalleAsignacionBuilder getDetalleAsignacionBuilder() {
		return detalleAsignacionBuilder;
	}

	public void setDetalleAsignacionBuilder(
			DetalleAsignacionBuilder detalleAsignacionBuilder) {
		this.detalleAsignacionBuilder = detalleAsignacionBuilder;
	}
}
