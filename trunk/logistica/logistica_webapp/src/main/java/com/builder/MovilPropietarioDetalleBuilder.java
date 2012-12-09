package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.MovilPropietarioDetalle;

import com.view.MovilPropietarioDetalleView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MovilPropietarioDetalleBuilder extends
		BaseBuilder<MovilPropietarioDetalleView, MovilPropietarioDetalle> {

	@Override
	public MovilPropietarioDetalle toDomain(MovilPropietarioDetalleView view) {
		return new MovilPropietarioDetalle(view.getId(),
				view.getFechaTitularDesde(), view.getFechaCedulaVerde(),
				view.getNumeroCedulaVerde(), view.getNumeroTitulo(),
				view.getMovil());
	}

	@Override
	public MovilPropietarioDetalleView toView(MovilPropietarioDetalle model) {
		return new MovilPropietarioDetalleView(model.getID(),
				model.getFechaTitularDesde(), model.getFechaCedulaVerde(),
				model.getNumeroCedulaVerde(), model.getNumeroTitulo(),
				model.getMovil());
	}
}
