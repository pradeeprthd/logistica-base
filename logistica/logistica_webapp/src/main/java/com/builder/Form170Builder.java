package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Form170;

import com.view.Form170View;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class Form170Builder extends BaseBuilder<Form170View, Form170> {

	@Override
	public Form170 toDomain(Form170View view) {
		return new Form170(view.getId(), view.getNumero(), view.getFecha());
	}

	@Override
	public Form170View toView(Form170 model) {
		return new Form170View(model.getID(), model.getNumero(),
				model.getFecha());
	}
}
