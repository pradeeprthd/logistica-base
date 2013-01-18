package logistica.jasper;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class MovilReport implements Serializable {

	private Long numeroMovil;

	private String patente;

	private Date fechaIngreso;

	private Date fechaEgreso;

	private String marca;

	private String modelo;

	private Integer anio;

	private String tipo;

	private String aseguradoEn;

	private String comunicacion;

	private String nombrePropietario;

	private String dniPropietario;

	private String cuitPropietario;

	private String tipoInscripcionPropietario;

	private String telefonoPropietario;

	private String observacionTelefonoPropietario;

	private String localidadPropietario;

	private String direccionPropietario;

	private String direccionFotoPropietario;

	private String nombreChofer1;

	private String dniChofer1;

	private String cuitChofer1;

	private String cuilChofer1;

	private String tipoInscripcionChofer1;

	private String telefonoChofer1;

	private String observacionTelefonoChofer1;

	private String localidadChofer1;

	private String direccionChofer1;

	private String direccionFotoChofer1;

	private String parentescoChofer1;

	private String nombreChofer2;

	private String dniChofer2;

	private String cuitChofer2;

	private String cuilChofer2;

	private String tipoInscripcionChofer2;

	private String telefonoChofer2;

	private String observacionTelefonoChofer2;

	private String localidadChofer2;

	private String direccionChofer2;

	private String direccionFotoChofer2;

	private String parentescoChofer2;

	private String nombreChofer3;

	private String dniChofer3;

	private String cuitChofer3;

	private String cuilChofer3;

	private String tipoInscripcionChofer3;

	private String telefonoChofer3;

	private String observacionTelefonoChofer3;

	private String localidadChofer3;

	private String direccionChofer3;

	private String direccionFotoChofer3;

	private String parentescoChofer3;

	public MovilReport(Long numeroMovil, String patente, Date fechaIngreso,
			Date fechaEgreso, String marca, String modelo, Integer anio,
			String tipo, String aseguradoEn, String comunicacion,
			String nombrePropietario, String dniPropietario,
			String cuitPropietario, String tipoInscripcionPropietario,
			String telefonoPropietario, String observacionTelefonoPropietario,
			String localidadPropietario, String direccionPropietario,
			String direccionFotoPropietario, String nombreChofer1,
			String dniChofer1, String cuitChofer1, String cuilChofer1,
			String tipoInscripcionChofer1, String telefonoChofer1,
			String observacionTelefonoChofer1, String localidadChofer1,
			String direccionChofer1, String direccionFotoChofer1,
			String parentescoChofer1, String nombreChofer2, String dniChofer2,
			String cuitChofer2, String cuilChofer2,
			String tipoInscripcionChofer2, String telefonoChofer2,
			String observacionTelefonoChofer2, String localidadChofer2,
			String direccionChofer2, String direccionFotoChofer2,
			String parentescoChofer2, String nombreChofer3, String dniChofer3,
			String cuitChofer3, String cuilChofer3,
			String tipoInscripcionChofer3, String telefonoChofer3,
			String observacionTelefonoChofer3, String localidadChofer3,
			String direccionChofer3, String direccionFotoChofer3,
			String parentescoChofer3) {
		super();
		this.numeroMovil = numeroMovil;
		this.patente = patente;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.tipo = tipo;
		this.aseguradoEn = aseguradoEn;
		this.comunicacion = comunicacion;
		this.nombrePropietario = nombrePropietario;
		this.dniPropietario = dniPropietario;
		this.cuitPropietario = cuitPropietario;
		this.tipoInscripcionPropietario = tipoInscripcionPropietario;
		this.telefonoPropietario = telefonoPropietario;
		this.observacionTelefonoPropietario = observacionTelefonoPropietario;
		this.localidadPropietario = localidadPropietario;
		this.direccionPropietario = direccionPropietario;
		this.direccionFotoPropietario = direccionFotoPropietario;
		this.nombreChofer1 = nombreChofer1;
		this.dniChofer1 = dniChofer1;
		this.cuitChofer1 = cuitChofer1;
		this.cuilChofer1 = cuilChofer1;
		this.tipoInscripcionChofer1 = tipoInscripcionChofer1;
		this.telefonoChofer1 = telefonoChofer1;
		this.observacionTelefonoChofer1 = observacionTelefonoChofer1;
		this.localidadChofer1 = localidadChofer1;
		this.direccionChofer1 = direccionChofer1;
		this.direccionFotoChofer1 = direccionFotoChofer1;
		this.parentescoChofer1 = parentescoChofer1;
		this.nombreChofer2 = nombreChofer2;
		this.dniChofer2 = dniChofer2;
		this.cuitChofer2 = cuitChofer2;
		this.cuilChofer2 = cuilChofer2;
		this.tipoInscripcionChofer2 = tipoInscripcionChofer2;
		this.telefonoChofer2 = telefonoChofer2;
		this.observacionTelefonoChofer2 = observacionTelefonoChofer2;
		this.localidadChofer2 = localidadChofer2;
		this.direccionChofer2 = direccionChofer2;
		this.direccionFotoChofer2 = direccionFotoChofer2;
		this.parentescoChofer2 = parentescoChofer2;
		this.nombreChofer3 = nombreChofer3;
		this.dniChofer3 = dniChofer3;
		this.cuitChofer3 = cuitChofer3;
		this.cuilChofer3 = cuilChofer3;
		this.tipoInscripcionChofer3 = tipoInscripcionChofer3;
		this.telefonoChofer3 = telefonoChofer3;
		this.observacionTelefonoChofer3 = observacionTelefonoChofer3;
		this.localidadChofer3 = localidadChofer3;
		this.direccionChofer3 = direccionChofer3;
		this.direccionFotoChofer3 = direccionFotoChofer3;
		this.parentescoChofer3 = parentescoChofer3;
	}

	public MovilReport() {

	}

	public String getParentescoChofer1() {
		return parentescoChofer1;
	}

	public void setParentescoChofer1(String parentescoChofer1) {
		this.parentescoChofer1 = parentescoChofer1;
	}

	public String getParentescoChofer2() {
		return parentescoChofer2;
	}

	public void setParentescoChofer2(String parentescoChofer2) {
		this.parentescoChofer2 = parentescoChofer2;
	}

	public String getParentescoChofer3() {
		return parentescoChofer3;
	}

	public void setParentescoChofer3(String parentescoChofer3) {
		this.parentescoChofer3 = parentescoChofer3;
	}

	public Long getNumeroMovil() {
		return numeroMovil;
	}

	public void setNumeroMovil(Long numeroMovil) {
		this.numeroMovil = numeroMovil;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAseguradoEn() {
		return aseguradoEn;
	}

	public void setAseguradoEn(String aseguradoEn) {
		this.aseguradoEn = aseguradoEn;
	}

	public String getComunicacion() {
		return comunicacion;
	}

	public void setComunicacion(String comunicacion) {
		this.comunicacion = comunicacion;
	}

	public String getNombrePropietario() {
		return nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public String getDniPropietario() {
		return dniPropietario;
	}

	public void setDniPropietario(String dniPropietario) {
		this.dniPropietario = dniPropietario;
	}

	public String getCuitPropietario() {
		return cuitPropietario;
	}

	public void setCuitPropietario(String cuitPropietario) {
		this.cuitPropietario = cuitPropietario;
	}

	public String getTipoInscripcionPropietario() {
		return tipoInscripcionPropietario;
	}

	public void setTipoInscripcionPropietario(String tipoInscripcionPropietario) {
		this.tipoInscripcionPropietario = tipoInscripcionPropietario;
	}

	public String getTelefonoPropietario() {
		return telefonoPropietario;
	}

	public void setTelefonoPropietario(String telefonoPropietario) {
		this.telefonoPropietario = telefonoPropietario;
	}

	public String getObservacionTelefonoPropietario() {
		return observacionTelefonoPropietario;
	}

	public void setObservacionTelefonoPropietario(
			String observacionTelefonoPropietario) {
		this.observacionTelefonoPropietario = observacionTelefonoPropietario;
	}

	public String getLocalidadPropietario() {
		return localidadPropietario;
	}

	public void setLocalidadPropietario(String localidadPropietario) {
		this.localidadPropietario = localidadPropietario;
	}

	public String getDireccionPropietario() {
		return direccionPropietario;
	}

	public void setDireccionPropietario(String direccionPropietario) {
		this.direccionPropietario = direccionPropietario;
	}

	public String getDireccionFotoPropietario() {
		return direccionFotoPropietario;
	}

	public void setDireccionFotoPropietario(String direccionFotoPropietario) {
		this.direccionFotoPropietario = direccionFotoPropietario;
	}

	public String getNombreChofer1() {
		return nombreChofer1;
	}

	public void setNombreChofer1(String nombreChofer1) {
		this.nombreChofer1 = nombreChofer1;
	}

	public String getDniChofer1() {
		return dniChofer1;
	}

	public void setDniChofer1(String dniChofer1) {
		this.dniChofer1 = dniChofer1;
	}

	public String getCuitChofer1() {
		return cuitChofer1;
	}

	public void setCuitChofer1(String cuitChofer1) {
		this.cuitChofer1 = cuitChofer1;
	}

	public String getCuilChofer1() {
		return cuilChofer1;
	}

	public void setCuilChofer1(String cuilChofer1) {
		this.cuilChofer1 = cuilChofer1;
	}

	public String getTipoInscripcionChofer1() {
		return tipoInscripcionChofer1;
	}

	public void setTipoInscripcionChofer1(String tipoInscripcionChofer1) {
		this.tipoInscripcionChofer1 = tipoInscripcionChofer1;
	}

	public String getTelefonoChofer1() {
		return telefonoChofer1;
	}

	public void setTelefonoChofer1(String telefonoChofer1) {
		this.telefonoChofer1 = telefonoChofer1;
	}

	public String getObservacionTelefonoChofer1() {
		return observacionTelefonoChofer1;
	}

	public void setObservacionTelefonoChofer1(String observacionTelefonoChofer1) {
		this.observacionTelefonoChofer1 = observacionTelefonoChofer1;
	}

	public String getLocalidadChofer1() {
		return localidadChofer1;
	}

	public void setLocalidadChofer1(String localidadChofer1) {
		this.localidadChofer1 = localidadChofer1;
	}

	public String getDireccionChofer1() {
		return direccionChofer1;
	}

	public void setDireccionChofer1(String direccionChofer1) {
		this.direccionChofer1 = direccionChofer1;
	}

	public String getDireccionFotoChofer1() {
		return direccionFotoChofer1;
	}

	public void setDireccionFotoChofer1(String direccionFotoChofer1) {
		this.direccionFotoChofer1 = direccionFotoChofer1;
	}

	public String getNombreChofer2() {
		return nombreChofer2;
	}

	public void setNombreChofer2(String nombreChofer2) {
		this.nombreChofer2 = nombreChofer2;
	}

	public String getDniChofer2() {
		return dniChofer2;
	}

	public void setDniChofer2(String dniChofer2) {
		this.dniChofer2 = dniChofer2;
	}

	public String getCuitChofer2() {
		return cuitChofer2;
	}

	public void setCuitChofer2(String cuitChofer2) {
		this.cuitChofer2 = cuitChofer2;
	}

	public String getCuilChofer2() {
		return cuilChofer2;
	}

	public void setCuilChofer2(String cuilChofer2) {
		this.cuilChofer2 = cuilChofer2;
	}

	public String getTipoInscripcionChofer2() {
		return tipoInscripcionChofer2;
	}

	public void setTipoInscripcionChofer2(String tipoInscripcionChofer2) {
		this.tipoInscripcionChofer2 = tipoInscripcionChofer2;
	}

	public String getTelefonoChofer2() {
		return telefonoChofer2;
	}

	public void setTelefonoChofer2(String telefonoChofer2) {
		this.telefonoChofer2 = telefonoChofer2;
	}

	public String getObservacionTelefonoChofer2() {
		return observacionTelefonoChofer2;
	}

	public void setObservacionTelefonoChofer2(String observacionTelefonoChofer2) {
		this.observacionTelefonoChofer2 = observacionTelefonoChofer2;
	}

	public String getLocalidadChofer2() {
		return localidadChofer2;
	}

	public void setLocalidadChofer2(String localidadChofer2) {
		this.localidadChofer2 = localidadChofer2;
	}

	public String getDireccionChofer2() {
		return direccionChofer2;
	}

	public void setDireccionChofer2(String direccionChofer2) {
		this.direccionChofer2 = direccionChofer2;
	}

	public String getDireccionFotoChofer2() {
		return direccionFotoChofer2;
	}

	public void setDireccionFotoChofer2(String direccionFotoChofer2) {
		this.direccionFotoChofer2 = direccionFotoChofer2;
	}

	public String getNombreChofer3() {
		return nombreChofer3;
	}

	public void setNombreChofer3(String nombreChofer3) {
		this.nombreChofer3 = nombreChofer3;
	}

	public String getDniChofer3() {
		return dniChofer3;
	}

	public void setDniChofer3(String dniChofer3) {
		this.dniChofer3 = dniChofer3;
	}

	public String getCuitChofer3() {
		return cuitChofer3;
	}

	public void setCuitChofer3(String cuitChofer3) {
		this.cuitChofer3 = cuitChofer3;
	}

	public String getCuilChofer3() {
		return cuilChofer3;
	}

	public void setCuilChofer3(String cuilChofer3) {
		this.cuilChofer3 = cuilChofer3;
	}

	public String getTipoInscripcionChofer3() {
		return tipoInscripcionChofer3;
	}

	public void setTipoInscripcionChofer3(String tipoInscripcionChofer3) {
		this.tipoInscripcionChofer3 = tipoInscripcionChofer3;
	}

	public String getTelefonoChofer3() {
		return telefonoChofer3;
	}

	public void setTelefonoChofer3(String telefonoChofer3) {
		this.telefonoChofer3 = telefonoChofer3;
	}

	public String getObservacionTelefonoChofer3() {
		return observacionTelefonoChofer3;
	}

	public void setObservacionTelefonoChofer3(String observacionTelefonoChofer3) {
		this.observacionTelefonoChofer3 = observacionTelefonoChofer3;
	}

	public String getLocalidadChofer3() {
		return localidadChofer3;
	}

	public void setLocalidadChofer3(String localidadChofer3) {
		this.localidadChofer3 = localidadChofer3;
	}

	public String getDireccionChofer3() {
		return direccionChofer3;
	}

	public void setDireccionChofer3(String direccionChofer3) {
		this.direccionChofer3 = direccionChofer3;
	}

	public String getDireccionFotoChofer3() {
		return direccionFotoChofer3;
	}

	public void setDireccionFotoChofer3(String direccionFotoChofer3) {
		this.direccionFotoChofer3 = direccionFotoChofer3;
	}

}
