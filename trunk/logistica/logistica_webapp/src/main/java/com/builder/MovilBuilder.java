package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.Movil;

import com.view.MovilView;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MovilBuilder extends BaseBuilder<MovilView, Movil> {

	@ManagedProperty("#{autonomoBuilder}")
	private AutonomoBuilder autonomoBuilder;

	public Movil toDomain(MovilView view) {
		return new Movil(view.getId(), view.getNumeroMovil(),
				view.getPatente(), view.getDescripcion(),
				view.getAsignacionMovil(), view.getEstado(),
				view.getFechaIngreso(), view.getFechaEgreso(),
				view.getControlado(), view.getComodato(), view.getChofer1(),
				view.getParentezcoChofer1(), view.getChofer2(),
				view.getParentezcoChofer2(), view.getChofer3(),
				view.getParentezcoChofer3(), view.getPropietario(),
				view.getMarca(), view.getModelo(), view.getAnio(),
				view.getTipo(), view.getMotor(), view.getNumeroMotor(),
				view.getPotencia(), view.getChasis(), view.getNumeroChasis(),
				view.getCantidadEjes(), view.getTara(),
				view.getCapacidadCarga(), view.getPrenda(),
				view.getFechaPrenda(), view.getAcreedor(),
				view.getInformeDominio(), view.getFechaVerificacionTecnica(),
				view.getNumeroVerificacionTecnica(), view.getFechaSenasa(),
				view.getNumeroSenasa(), view.getFechaOtraHabilitacion1(),
				view.getNumeroOtraHabilitacion1(),
				view.getFechaOtraHabilitacion2(),
				view.getNumeroOtraHabilitacion2(),
				view.getFechaOtraHabilitacion3(),
				view.getNumeroOtraHabilitacion3(),
				view.getFechaOtraHabilitacion4(),
				view.getNumeroOtraHabilitacion4(), view.getCapital(),
				view.getFechaVencimientoCapital(),
				view.getNumeroHabilitacionCapital(),
				view.getLibreDeudaInfracciones(), view.getLibreDeudaPatentes(),
				view.getImpuestoDocente(), view.getContratoFirmado(),
				view.getAccidente(),
				view.getPatcomList() != null ? autonomoBuilder.toDomain(view
						.getPatcomList()) : null, view.getTipoCombustible(),
				view.getComunicacion(), view.getObservacionesComunicacion(),
				view.getAltura(), view.getAncho(), view.getLargo());
	}

	public MovilView toView(Movil model) {
		return new MovilView(model.getID(), model.getNumeroMovil(),
				model.getPatente(), model.getDescripcion(),
				model.getAsignacionMovil(), model.getEstado(),
				model.getFechaIngreso(), model.getFechaEgreso(),
				model.getControlado(), model.getComodato(), model.getChofer1(),
				model.getParentezcoChofer1(), model.getChofer2(),
				model.getParentezcoChofer2(), model.getChofer3(),
				model.getParentezcoChofer3(), model.getPropietario(),
				model.getMarca(), model.getModelo(), model.getAnio(),
				model.getTipo(), model.getMotor(), model.getNumeroMotor(),
				model.getPotencia(), model.getChasis(),
				model.getNumeroChasis(), model.getCantidadEjes(),
				model.getTara(), model.getCapacidadCarga(), model.getPrenda(),
				model.getFechaPrenda(), model.getAcreedor(),
				model.getInformeDominio(), model.getFechaVerificacionTecnica(),
				model.getNumeroVerificacionTecnica(), model.getFechaSenasa(),
				model.getNumeroSenasa(), model.getFechaOtraHabilitacion1(),
				model.getNumeroOtraHabilitacion1(),
				model.getFechaOtraHabilitacion2(),
				model.getNumeroOtraHabilitacion2(),
				model.getFechaOtraHabilitacion3(),
				model.getNumeroOtraHabilitacion3(),
				model.getFechaOtraHabilitacion4(),
				model.getNumeroOtraHabilitacion4(), model.getCapital(),
				model.getFechaVencimientoCapital(),
				model.getNumeroHabilitacionCapital(),
				model.getLibreDeudaInfracciones(),
				model.getLibreDeudaPatentes(), model.getImpuestoDocente(),
				model.getContratoFirmado(), model.getAccidente(),
				model.getPatcomList() != null ? autonomoBuilder.toView(model
						.getPatcomList()) : null, model.getTipoCombustible(),
				model.getComunicacion(), model.getObservacionesComunicacion(),
				model.getAltura(), model.getAncho(), model.getLargo());
	}

	public AutonomoBuilder getAutonomoBuilder() {
		return autonomoBuilder;
	}

	public void setAutonomoBuilder(AutonomoBuilder autonomoBuilder) {
		this.autonomoBuilder = autonomoBuilder;
	}
}
