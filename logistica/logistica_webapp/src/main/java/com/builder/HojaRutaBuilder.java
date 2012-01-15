package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.HojaRuta;

import com.view.HojaRutaView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class HojaRutaBuilder extends BaseBuilder<HojaRutaView, HojaRuta> {

	@ManagedProperty("#{detalleHojaRutaBuilder}")
	private DetalleHojaRutaBuilder detalleHojaRutaBuilder;

	@Override
	public HojaRuta toDomain(HojaRutaView view) {
		return new HojaRuta(view.getId(), view.getSucursal(),
				view.getFechaEmision(), view.getPrefijo(), view.getNumero(),
				view.getCliente(), view.getChofer(), view.getMovil(),
				view.getNumeroRemito(), view.getDireccion(),
				view.getLocalidad(), view.getObservaciones(),
				detalleHojaRutaBuilder.toDomain(view
						.getDetalleHojaRutaViewList()));
	}

	@Override
	public HojaRutaView toView(HojaRuta model) {
		return new HojaRutaView(model.getID(), model.getSucursal(),
				model.getFechaEmision(), model.getPrefijo(), model.getNumero(),
				model.getCliente(), model.getChofer(), model.getMovil(),
				model.getNumeroRemito(), model.getDireccion(),
				model.getLocalidad(), model.getObservaciones(),
				detalleHojaRutaBuilder.toView(model.getDetalleHojaRutaList()));
	}

	public DetalleHojaRutaBuilder getDetalleHojaRutaBuilder() {
		return detalleHojaRutaBuilder;
	}

	public void setDetalleHojaRutaBuilder(
			DetalleHojaRutaBuilder detalleHojaRutaBuilder) {
		this.detalleHojaRutaBuilder = detalleHojaRutaBuilder;
	}
}
