package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Proveedor;

import com.view.ProveedorView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ProveedorBuilder extends BaseBuilder<ProveedorView, Proveedor> {

	@Override
	public Proveedor toDomain(ProveedorView view) {
		return new Proveedor(view.getId(), view.getNombre());
	}

	@Override
	public ProveedorView toView(Proveedor model) {
		return new ProveedorView(model.getID(), model.getNombre());
	}
}
