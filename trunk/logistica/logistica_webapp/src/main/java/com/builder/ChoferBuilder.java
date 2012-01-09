package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.Chofer;

import com.view.ChoferView;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ChoferBuilder extends BaseBuilder<ChoferView, Chofer> {

	@ManagedProperty("#{direccionBuilder}")
	private DireccionBuilder direccionBuilder;

	@Override
	public Chofer toDomain(ChoferView view) {
		return new Chofer(view.getId(), view.getNombre(),
				direccionBuilder.toDomain(view.getDireccionView()));
	}

	@Override
	public ChoferView toView(Chofer model) {
		return new ChoferView(model.getID(), model.getNombre(),
				direccionBuilder.toView(model.getDireccion()));
	}
	
	public DireccionBuilder getDireccionBuilder() {
		return direccionBuilder;
	}

	public void setDireccionBuilder(DireccionBuilder direccionBuilder) {
		this.direccionBuilder = direccionBuilder;
	}
}
