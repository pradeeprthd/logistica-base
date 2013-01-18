package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.jasper.MovilReport;
import logistica.model.Movil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MovilReportBuilder extends BaseBuilder<MovilReport, Movil> {
	
	public static String imagenAnonimo = "/fotos/anonimo.jpg";

	@Override
	public Movil toDomain(MovilReport view) {
		return null;
	}

	@Override
	public MovilReport toView(Movil model) {
		return new MovilReport(
				model.getNumeroMovil(),
				model.getPatente(),
				model.getFechaIngreso(),
				model.getFechaEgreso(),
				model.getMarca(),
				model.getModelo(),
				model.getAnio(),
				model.getTipo(),
				model.getAseguradoEn(),
				model.getComunicacion(),
				model.getPropietario() != null ? model.getPropietario()
						.getNombre() : "",
				model.getPropietario() != null ? model.getPropietario()
						.getDni() : "",
				model.getPropietario() != null ? model.getPropietario()
						.getCuit() : "",
				model.getPropietario() != null
						&& model.getPropietario().getTipoInscripcion() != null ? model
						.getPropietario().getTipoInscripcion().toString()
						: "",
				model.getPropietario() != null ? model.getPropietario()
						.getTelefono() : "",
				model.getPropietario() != null ? model.getPropietario()
						.getObservaciones() : "",
				model.getPropietario() != null
						&& model.getPropietario().getDireccion() != null
						&& model.getPropietario().getDireccion().getLocalidad() != null ? model
						.getPropietario().getDireccion().getLocalidad()
						.getDescripcion()
						: "",
				model.getPropietario() != null
						&& model.getPropietario().getDireccion() != null ? model
						.getPropietario().getDireccion().getCalle()
						+ " "
						+ model.getPropietario().getDireccion().getAltura()
						: "",
				model.getPropietario() != null && model.getPropietario()
				.getRutaArchivo() != null && !model.getPropietario()
				.getRutaArchivo().equals("") ? model.getPropietario()
						.getRutaArchivo()
						+ "/"
						+ model.getPropietario().getNombreArchivo() : imagenAnonimo,
				model.getChofer1() != null ? model.getChofer1().getNombre()
						: "",
				model.getChofer1() != null ? model.getChofer1().getDni() : "",
				model.getChofer1() != null ? model.getChofer1().getCuit() : "",
				model.getChofer1() != null ? model.getChofer1().getCuil() : "",
				model.getChofer1() != null ? model.getChofer1()
						.getTipoInscripcion() != null ? model.getChofer1()
						.getTipoInscripcion().toString() : "" : "",
				model.getChofer1() != null ? model.getChofer1().getTelefono()
						: "",
				model.getChofer1() != null ? model.getChofer1()
						.getObservacionTelefono() : "",
				model.getChofer1() != null
						&& model.getChofer1().getDireccion() != null
						&& model.getChofer1().getDireccion().getLocalidad() != null ? model
						.getChofer1().getDireccion().getLocalidad()
						.getDescripcion()
						: "",
				model.getChofer1() != null
						&& model.getChofer1().getDireccion() != null ? model
						.getChofer1().getDireccion().getCalle()
						+ " " + model.getChofer1().getDireccion().getAltura()
						: "",
				model.getChofer1() != null && model.getChofer1()
				.getRutaArchivo() != null && !model.getChofer1()
				.getRutaArchivo().equals("")? model.getChofer1()
						.getRutaArchivo()
						+ "/"
						+ model.getChofer1().getNombreArchivo() : imagenAnonimo,
				model.getChofer1() != null ? model.getParentezcoChofer1()
						.toString() : "",
				model.getChofer2() != null ? model.getChofer2().getNombre()
						: "",
				model.getChofer2() != null ? model.getChofer2().getDni() : "",
				model.getChofer2() != null ? model.getChofer2().getCuit() : "",
				model.getChofer2() != null ? model.getChofer2().getCuil() : "",
				model.getChofer2() != null ? model.getChofer2()
						.getTipoInscripcion() != null ? model.getChofer2()
						.getTipoInscripcion().toString() : "" : "",
				model.getChofer2() != null ? model.getChofer2().getTelefono()
						: "",
				model.getChofer2() != null ? model.getChofer2()
						.getObservacionTelefono() : "",
				model.getChofer2() != null
						&& model.getChofer2().getDireccion() != null
						&& model.getChofer2().getDireccion().getLocalidad() != null ? model
						.getChofer2().getDireccion().getLocalidad()
						.getDescripcion()
						: "",
				model.getChofer2() != null
						&& model.getChofer2().getDireccion() != null ? model
						.getChofer2().getDireccion().getCalle()
						+ " " + model.getChofer2().getDireccion().getAltura()
						: "",
						model.getChofer2() != null && model.getChofer2()
						.getRutaArchivo() != null && !model.getChofer2()
						.getRutaArchivo().equals("")? model.getChofer2()
								.getRutaArchivo()
								+ "/"
								+ model.getChofer2().getNombreArchivo() : imagenAnonimo,
				model.getChofer2() != null ? model.getParentezcoChofer2()
						.toString() : "",
				model.getChofer3() != null ? model.getChofer3().getNombre()
						: "",
				model.getChofer3() != null ? model.getChofer3().getDni() : "",
				model.getChofer3() != null ? model.getChofer3().getCuit() : "",
				model.getChofer3() != null ? model.getChofer3().getCuil() : "",
				model.getChofer3() != null ? model.getChofer3()
						.getTipoInscripcion() != null ? model.getChofer3()
						.getTipoInscripcion().toString() : "" : "",
				model.getChofer3() != null ? model.getChofer3().getTelefono()
						: "",
				model.getChofer3() != null ? model.getChofer3()
						.getObservacionTelefono() : "",
				model.getChofer3() != null
						&& model.getChofer3().getDireccion() != null
						&& model.getChofer3().getDireccion().getLocalidad() != null ? model
						.getChofer3().getDireccion().getLocalidad()
						.getDescripcion()
						: "", model.getChofer3() != null
						&& model.getChofer3().getDireccion() != null ? model
						.getChofer3().getDireccion().getCalle()
						+ " " + model.getChofer3().getDireccion().getAltura()
						: "", model.getChofer3() != null && model.getChofer3()
						.getRutaArchivo() != null && !model.getChofer3()
						.getRutaArchivo().equals("")? model.getChofer3()
								.getRutaArchivo()
								+ "/"
								+ model.getChofer3().getNombreArchivo() : imagenAnonimo,
				model.getChofer3() != null ? model.getParentezcoChofer3()
						.toString() : "");
	}

}
