package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.Sucursal;

import com.view.SucursalView;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SucursalBuilder extends BaseBuilder<SucursalView, Sucursal> {
	@ManagedProperty("#{direccionBuilder}")
	private DireccionBuilder direccionBuilder;

	@Override
	public Sucursal toDomain(SucursalView view) {
		return new Sucursal(view.getId(), view.getNombre(),
				view.getNumeroSucursal(), view.getNumeroHojaRuta(),
				direccionBuilder.toDomain(view.getDireccionView()));
	}

	@Override
	public SucursalView toView(Sucursal model) {
		return new SucursalView(model.getID(), model.getNombre(),
				model.getNumeroSucursal(), model.getNumeroHojaRuta(),
				direccionBuilder.toView(model.getDireccion()));
	}

	public DireccionBuilder getDireccionBuilder() {
		return direccionBuilder;
	}

	public void setDireccionBuilder(DireccionBuilder direccionBuilder) {
		this.direccionBuilder = direccionBuilder;
	}
}
