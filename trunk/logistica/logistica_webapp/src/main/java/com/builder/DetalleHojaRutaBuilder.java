package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.DetalleHojaRuta;

import com.view.DetalleHojaRutaView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DetalleHojaRutaBuilder extends
		BaseBuilder<DetalleHojaRutaView, DetalleHojaRuta> {

	@Override
	public DetalleHojaRuta toDomain(DetalleHojaRutaView view) {
		return new DetalleHojaRuta(view.getId(), view.getDireccion(),
				view.getLocalidad(), view.getUnidadMedida(),
				view.getCantidad(), view.getFechaDesde(), view.getFechaHasta());
	}

	@Override
	public DetalleHojaRutaView toView(DetalleHojaRuta model) {
		return new DetalleHojaRutaView(model.getID(), model.getDireccion(),
				model.getLocalidad(), model.getUnidadMedida(),
				model.getCantidad(), model.getFechaDesde(),
				model.getFechaHasta());
	}
}
