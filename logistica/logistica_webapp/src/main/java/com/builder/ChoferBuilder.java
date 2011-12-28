package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Chofer;

import com.view.ChoferView;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ChoferBuilder extends BaseBuilder<ChoferView, Chofer> {

	@Override
	public Chofer toDomain(ChoferView view) {
		return new Chofer(view.getId(), view.getNombre());
	}

	@Override
	public ChoferView toView(Chofer model) {
		return new ChoferView(model.getID(), model.getNombre());
	}
}
