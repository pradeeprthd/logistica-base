package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.SucursalCoto;

import com.view.SucursalCotoView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class SucursalCotoBuilder extends
		BaseBuilder<SucursalCotoView, SucursalCoto> {
	@ManagedProperty("#{direccionBuilder}")
	private DireccionBuilder direccionBuilder;

	@Override
	public SucursalCoto toDomain(SucursalCotoView view) {
		return new SucursalCoto(view.getId(), view.getNombre(),
				view.getNumeroSucursal(), view.getCantidadMoviles(),
				direccionBuilder.toDomain(view.getDireccionView()));
	}

	@Override
	public SucursalCotoView toView(SucursalCoto model) {
		return new SucursalCotoView(model.getID(), model.getNombre(),
				model.getNumeroSucursal(), model.getCantidadMoviles(),
				direccionBuilder.toView(model.getDireccion()));
	}

	public DireccionBuilder getDireccionBuilder() {
		return direccionBuilder;
	}

	public void setDireccionBuilder(DireccionBuilder direccionBuilder) {
		this.direccionBuilder = direccionBuilder;
	}
}
