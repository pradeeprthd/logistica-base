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

	@ManagedProperty("#{reciboBuilder}")
	private ReciboBuilder reciboBuilder;

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
				view.getAltura(), view.getAncho(), view.getLargo(),
				view.getAseguradoEn(), view.getComprobante(),
				view.getTipoUso(), view.getFechaSeguroDesde(),
				view.getFechaSeguroHasta(), view.getFechaReciboVencimiento(),
				view.getComprobante2(), view.getNumeroCuenta(),
				view.getNumeroPoliza(), view.getNumeroCuota(),
				view.getNumeroSocio(), view.getNumeroLoJack(),
				view.getObservacionesSeguro(), view.getValorMovil(),
				view.getValorAccesorios(), view.getValorTotalAsegurado(),
				view.getValorLoJack(), view.getCoberturaAdicional(),
				view.getNotas(), view.getNotasControl(),
				view.getForm817List() != null ? autonomoBuilder.toDomain(view
						.getForm817List()) : null,
				view.getForm170List() != null ? autonomoBuilder.toDomain(view
						.getForm170List()) : null,
				view.getReciboList() != null ? reciboBuilder.toDomain(view
						.getReciboList()) : null,
				view.getNominaList() != null ? reciboBuilder.toDomain(view
						.getNominaList()) : null, view.getCategoria(),
				view.getReciboSueldo(), view.getObservacionBlanqueo(),
				view.getNominaEmpleado(), view.getSeguroVida(),
				view.getAltaEmpleador(), view.getAltaTemprana(),
				view.getObraSocial(), view.getSindicato());
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
				model.getAltura(), model.getAncho(), model.getLargo(),
				model.getAseguradoEn(), model.getComprobante(),
				model.getTipoUso(), model.getFechaSeguroDesde(),
				model.getFechaSeguroHasta(), model.getFechaReciboVencimiento(),
				model.getComprobante2(), model.getNumeroCuenta(),
				model.getNumeroPoliza(), model.getNumeroCuota(),
				model.getNumeroSocio(), model.getNumeroLoJack(),
				model.getObservacionesSeguro(), model.getValorMovil(),
				model.getValorAccesorios(), model.getValorTotalAsegurado(),
				model.getValorLoJack(), model.getCoberturaAdicional(),
				model.getNotas(), model.getNotasControl(),
				model.getForm817List() != null ? autonomoBuilder.toView(model
						.getForm817List()) : null,
				model.getForm170List() != null ? autonomoBuilder.toView(model
						.getForm170List()) : null,
				model.getReciboList() != null ? reciboBuilder.toView(model
						.getReciboList()) : null,
				model.getNominaList() != null ? reciboBuilder.toView(model
						.getNominaList()) : null, model.getCategoria(),
				model.getReciboSueldo(), model.getObservacionBlanqueo(),
				model.getNominaEmpleado(), model.getSeguroVida(),
				model.getAltaEmpleador(), model.getAltaTemprana(),
				model.getObraSocial(), model.getSindicato());
	}

	public AutonomoBuilder getAutonomoBuilder() {
		return autonomoBuilder;
	}

	public void setAutonomoBuilder(AutonomoBuilder autonomoBuilder) {
		this.autonomoBuilder = autonomoBuilder;
	}

	public ReciboBuilder getReciboBuilder() {
		return reciboBuilder;
	}

	public void setReciboBuilder(ReciboBuilder reciboBuilder) {
		this.reciboBuilder = reciboBuilder;
	}

}
