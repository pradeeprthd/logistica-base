package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.OtrosServicios;

import com.view.OtrosServiciosView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class OtrosServiciosBuilder extends
		BaseBuilder<OtrosServiciosView, OtrosServicios> {

	@Override
	public OtrosServicios toDomain(OtrosServiciosView view) {
		return new OtrosServicios(view.getId(), view.getMovil(),
				view.getChofer(), view.getFecha(), view.getHorarioEntrada(),
				view.getAsignadoA());
	}

	@Override
	public OtrosServiciosView toView(OtrosServicios model) {
		return new OtrosServiciosView(model.getID(), model.getMovil(),
				model.getChofer(), model.getFecha(), model.getHorarioEntrada(),
				model.getAsignadoA());
	}
}
