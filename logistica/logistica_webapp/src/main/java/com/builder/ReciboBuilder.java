package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Recibo;

import com.view.ReciboView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ReciboBuilder extends BaseBuilder<ReciboView, Recibo> {

	@Override
	public Recibo toDomain(ReciboView view) {
		return new Recibo(view.getId(), view.getNumero(),
				view.getObservacion(), view.getFecha());
	}

	@Override
	public ReciboView toView(Recibo model) {
		return new ReciboView(model.getID(), model.getNumero(),
				model.getObservacion(), model.getFecha());
	}
}
