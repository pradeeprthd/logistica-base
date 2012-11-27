package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.Chofer;

import com.view.ChoferView;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ChoferBuilder extends BaseBuilder<ChoferView, Chofer> {

	@ManagedProperty("#{direccionBuilder}")
	private DireccionBuilder direccionBuilder;

	@Override
	public Chofer toDomain(ChoferView view) {
		return new Chofer(view.getId(), view.getNombre(),
				direccionBuilder.toDomain(view.getDireccionView()),
				view.getDni(), view.getFechaNacimiento(),
				view.getTipoInscripcion(), view.getCuil(), view.getCuit(),
				view.getTelefono(), view.getObservacionTelefono(),
				view.getTelefono2(), view.getObservacionTelefono2(),
				view.getTelefono3(), view.getObservacionTelefono3(),
				view.getFechaLibretaSanitaria(),
				view.getFechaVencimientoRegistro(),
				view.getAutorizacionManejo(), view.getPartidaNacimiento(),
				view.getSeguroAccidente(), view.getAntecedentesBuenaConducta(),
				view.getConstanciaRegistro(),
				view.getLicenciaNacionalHabilitante(), view.getFechaCurso(),
				view.getFechaRegistro(), view.getObservaciones(),
				view.getImagen() != null ? view.getImagen().getContents()
						: null, view.getRutaArchivo(), view.getNombreArchivo());
	}

	@Override
	public ChoferView toView(Chofer model) {
		return new ChoferView(model.getID(), model.getNombre(),
				direccionBuilder.toView(model.getDireccion()), model.getDni(),
				model.getFechaNacimiento(), model.getTipoInscripcion(),
				model.getCuil(), model.getCuit(), model.getTelefono(),
				model.getObservacionTelefono(), model.getTelefono2(),
				model.getObservacionTelefono2(), model.getTelefono3(),
				model.getObservacionTelefono3(),
				model.getFechaLibreataSanitaria(),
				model.getFechaVencimientoRegistro(),
				model.getAurizacionManejo(), model.getPartidaNacimiento(),
				model.getSeguroAccidente(),
				model.getAntecedentesBuenaConducta(),
				model.getConstanciaRegistro(),
				model.getLicenciaNacionalHabilitante(), model.getFechaCurso(),
				model.getFechaRegistro(), model.getObservaciones(),
				model.getImagen(), model.getRutaArchivo(),
				model.getNombreArchivo());
	}

	public DireccionBuilder getDireccionBuilder() {
		return direccionBuilder;
	}

	public void setDireccionBuilder(DireccionBuilder direccionBuilder) {
		this.direccionBuilder = direccionBuilder;
	}
}
