package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.DiagramacionDiaria;

import com.view.DiagramacionDiariaView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DiagramacionDiariaBuilder extends
		BaseBuilder<DiagramacionDiariaView, DiagramacionDiaria> {

	@ManagedProperty("#{detalleSucursalBuilder}")
	private DetalleSucursalBuilder detalleSucursalBuilder;

	@Override
	public DiagramacionDiaria toDomain(DiagramacionDiariaView view) {
		return new DiagramacionDiaria(view.getId(), view.getFecha(),
				view.getNovedades(), detalleSucursalBuilder.toDomain(view
						.getDetalleSucursalViewList()));
	}

	@Override
	public DiagramacionDiariaView toView(DiagramacionDiaria model) {
		return new DiagramacionDiariaView(model.getID(), model.getFecha(),
				model.getNovedades(), detalleSucursalBuilder.toView(model
						.getDetalleSucursalList()));
	}

	public DetalleSucursalBuilder getDetalleSucursalBuilder() {
		return detalleSucursalBuilder;
	}

	public void setDetalleSucursalBuilder(
			DetalleSucursalBuilder detalleSucursalBuilder) {
		this.detalleSucursalBuilder = detalleSucursalBuilder;
	}
}
