package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Nomina;

import com.view.NominaView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class NominaBuilder extends BaseBuilder<NominaView, Nomina> {

	@Override
	public Nomina toDomain(NominaView view) {
		return new Nomina(view.getId(), view.getNumero(),
				view.getObservacion(), view.getFecha());
	}

	@Override
	public NominaView toView(Nomina model) {
		return new NominaView(model.getID(), model.getNumero(),
				model.getObservacion(), model.getFecha());
	}
}
