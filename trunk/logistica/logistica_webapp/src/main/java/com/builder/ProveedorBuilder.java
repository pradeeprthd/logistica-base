package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.Proveedor;

import com.view.ProveedorView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ProveedorBuilder extends BaseBuilder<ProveedorView, Proveedor> {
	@ManagedProperty("#{direccionBuilder}")
	private DireccionBuilder direccionBuilder;

	@Override
	public Proveedor toDomain(ProveedorView view) {
		return new Proveedor(view.getId(), view.getNombre(),
				direccionBuilder.toDomain(view.getDireccionView()));
	}

	@Override
	public ProveedorView toView(Proveedor model) {
		return new ProveedorView(model.getID(), model.getNombre(),
				direccionBuilder.toView(model.getDireccion()));
	}
	
	public DireccionBuilder getDireccionBuilder() {
		return direccionBuilder;
	}

	public void setDireccionBuilder(DireccionBuilder direccionBuilder) {
		this.direccionBuilder = direccionBuilder;
	}

}
