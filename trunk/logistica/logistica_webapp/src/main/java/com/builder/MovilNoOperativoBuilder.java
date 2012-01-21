package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.MovilNoOperativo;

import com.view.MovilNoOperativoView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MovilNoOperativoBuilder extends
		BaseBuilder<MovilNoOperativoView, MovilNoOperativo> {

	@Override
	public MovilNoOperativo toDomain(MovilNoOperativoView view) {
		return new MovilNoOperativo(view.getId(), view.getMovil(),
				view.getFechaDesde(), view.getFechaHasta(),
				view.getEstadoMovilEnum());
	}

	@Override
	public MovilNoOperativoView toView(MovilNoOperativo model) {
		return new MovilNoOperativoView(model.getID(), model.getMovil(),
				model.getFechaDesde(), model.getFechaHasta(),
				model.getEstadoMovilEnum());
	}
}
