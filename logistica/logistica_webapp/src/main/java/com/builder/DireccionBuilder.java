package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.composite.Direccion;

import com.view.DireccionView;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DireccionBuilder extends BaseBuilder<DireccionView, Direccion> {

	@Override
	Direccion toDomain(DireccionView view) {
		return new Direccion(view.getLocalidad(), view.getCalle(),
				view.getAltura(), view.getPiso(), view.getDepartamento(),
				view.getCodigoPostal());
	}

	@Override
	DireccionView toView(Direccion model) {
		return new DireccionView(model.getLocalidad(), model.getCalle(),
				model.getAltura(), model.getPiso(), model.getDepartamento(),
				model.getCodigoPostal());
	}
}
