package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Autonomo;

import com.view.AutonomoView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class AutonomoBuilder extends BaseBuilder<AutonomoView, Autonomo> {

	@Override
	public Autonomo toDomain(AutonomoView view) {
		return new Autonomo(view.getId(), view.getNumeroAutonomo(),
				view.getFechaAutonomo());
	}

	@Override
	public AutonomoView toView(Autonomo model) {
		return new AutonomoView(model.getID(), model.getNumeroAutonomo(),
				model.getFechaAutonomo());
	}
}
