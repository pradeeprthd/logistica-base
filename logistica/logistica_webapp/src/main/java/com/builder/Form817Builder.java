package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Form817;

import com.view.Form817View;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class Form817Builder extends BaseBuilder<Form817View, Form817> {

	@Override
	public Form817 toDomain(Form817View view) {
		return new Form817(view.getId(), view.getNumero(), view.getFecha());
	}

	@Override
	public Form817View toView(Form817 model) {
		return new Form817View(model.getID(), model.getNumero(),
				model.getFecha());
	}
}
