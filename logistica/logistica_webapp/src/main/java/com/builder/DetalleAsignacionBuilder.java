package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.DetalleAsignacion;

import com.view.DetalleAsignacionView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DetalleAsignacionBuilder extends
		BaseBuilder<DetalleAsignacionView, DetalleAsignacion> {

	@Override
	public DetalleAsignacion toDomain(DetalleAsignacionView view) {
		return new DetalleAsignacion(view.getId(), view.getMovil(),
				view.getDescripcionFlete(), view.getHorarioEntrada(),
				view.getHorarioSalida(), view.getHorarioPedidoFlete(),
				view.getNombreAgenciaFlete(), view.getCodigoCoto());
	}

	@Override
	public DetalleAsignacionView toView(DetalleAsignacion model) {
		return new DetalleAsignacionView(model.getID(), model.getMovil(),
				model.getDescripcionFlete(), model.getHorarioEntrada(),
				model.getHorarioSalida(), model.getHorarioPedidoFlete(),
				model.getNombreAgenciaFlete(), model.getCodigoCoto());
	}
}
