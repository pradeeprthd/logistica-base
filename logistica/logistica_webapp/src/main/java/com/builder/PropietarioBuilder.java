package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.Propietario;

import com.view.PropietarioView;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PropietarioBuilder extends
		BaseBuilder<PropietarioView, Propietario> {

	@ManagedProperty("#{direccionBuilder}")
	private DireccionBuilder direccionBuilder;

	@Override
	public Propietario toDomain(PropietarioView view) {
		return new Propietario(view.getId(), view.getNombre(),
				direccionBuilder.toDomain(view.getDireccionView()),
				view.getDni(), view.getFechaNacimiento(),
				view.getTipoInscripcion(), view.getCuil(), view.getCuit(),
				view.getTelefono(), view.getObservacionTelefono(),
				view.getTelefono2(), view.getObservacionTelefono2(),
				view.getTelefono3(), view.getObservacionTelefono3(),
				view.getPartidaNacimiento(), view.getObservaciones(),
				view.getImagen() != null ? view.getImagen().getContents()
						: null, view.getRutaArchivo(),
				view.getNombreArchivo(), view.getMovilList(),
				view.getAutonomoList());
	}

	@Override
	public PropietarioView toView(Propietario model) {
		return new PropietarioView(model.getID(), model.getNombre(),
				direccionBuilder.toView(model.getDireccion()), model.getDni(),
				model.getFechaNacimiento(), model.getTipoInscripcion(),
				model.getCuil(), model.getCuit(), model.getTelefono(),
				model.getObservacionTelefono(), model.getTelefono2(),
				model.getObservacionTelefono2(), model.getTelefono3(),
				model.getObservacionTelefono3(), model.getPartidaNacimiento(),
				model.getObservaciones(), model.getImagen(),
				model.getRutaArchivo(), model.getNombreArchivo(),
				model.getMovilList(), model.getAutonomoList());
	}

	public DireccionBuilder getDireccionBuilder() {
		return direccionBuilder;
	}

	public void setDireccionBuilder(DireccionBuilder direccionBuilder) {
		this.direccionBuilder = direccionBuilder;
	}
}
