package com.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import logistica.model.Chofer;
import logistica.model.Propietario;
import logistica.type.AsignacionMovilEnum;
import logistica.type.EstadoEnum;
import logistica.type.ParentezcoEnum;
import logistica.type.TipoCombustibleEnum;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MovilView implements Serializable {
	private Long id;

	@NotNull(message = "Valor requerido")
	private Long numeroMovil;

	@NotNull(message = "Valor requerido")
	@Size(min = 6, max = 20, message = "El número de patente debe tener entre 6 y 20 caracteres.")
	private String patente;

	private String descripcion;

	@NotNull(message = "Valor requerido")
	private AsignacionMovilEnum asignacionMovil;

	@NotNull(message = "Valor requerido")
	private EstadoEnum estado;

	@NotNull(message = "Valor requerido")
	private Date fechaIngreso;

	private Date fechaEgreso;

	private Boolean controlado;

	private Boolean comodato;

	private Chofer chofer1;

	private ParentezcoEnum parentezcoChofer1;

	private Chofer chofer2;

	private ParentezcoEnum parentezcoChofer2;

	private Chofer chofer3;

	private ParentezcoEnum parentezcoChofer3;

	private Propietario propietario;

	private String marca;

	private String modelo;

	private Integer anio;

	private String tipo;

	private String motor;

	private String numeroMotor;

	private String potencia;

	private String chasis;

	private String numeroChasis;

	private Integer cantidadEjes;

	private Integer tara;

	private Integer capacidadCarga;

	private Boolean prenda;

	private Date fechaPrenda;

	private String acreedor;

	private Boolean informeDominio;

	private Date fechaVerificacionTecnica;

	private String numeroVerificacionTecnica;

	private Date fechaSenasa;

	private String numeroSenasa;

	private Date fechaOtraHabilitacion1;

	private String numeroOtraHabilitacion1;

	private Date fechaOtraHabilitacion2;

	private String numeroOtraHabilitacion2;

	private Date fechaOtraHabilitacion3;

	private String numeroOtraHabilitacion3;

	private Date fechaOtraHabilitacion4;

	private String numeroOtraHabilitacion4;

	private Boolean capital;

	private Date fechaVencimientoCapital;

	private String numeroHabilitacionCapital;

	private String libreDeudaInfracciones;

	private String libreDeudaPatentes;

	private Boolean impuestoDocente;

	private Boolean contratoFirmado;

	private String accidente;

	private List<AutonomoView> patcomList;

	private TipoCombustibleEnum tipoCombustible;

	private String comunicacion;

	private String observacionesComunicacion;

	private Double altura;

	private Double ancho;

	private Double largo;

	public MovilView(Long id, Long numeroMovil, String patente,
			String descripcion, AsignacionMovilEnum asignacionMovil,
			EstadoEnum estado, Date fechaIngreso, Date fechaEgreso,
			Boolean controlado, Boolean comodato, Chofer chofer1,
			ParentezcoEnum parentezcoChofer1, Chofer chofer2,
			ParentezcoEnum parentezcoChofer2, Chofer chofer3,
			ParentezcoEnum parentezcoChofer3, Propietario propietario,
			String marca, String modelo, Integer anio, String tipo,
			String motor, String numeroMotor, String potencia, String chasis,
			String numeroChasis, Integer cantidadEjes, Integer tara,
			Integer capacidadCarga, Boolean prenda, Date fechaPrenda,
			String acreedor, Boolean informeDominio,
			Date fechaVerificacionTecnica, String numeroVerificacionTecnica,
			Date fechaSenasa, String numeroSenasa, Date fechaOtraHabilitacion1,
			String numeroOtraHabilitacion1, Date fechaOtraHabilitacion2,
			String numeroOtraHabilitacion2, Date fechaOtraHabilitacion3,
			String numeroOtraHabilitacion3, Date fechaOtraHabilitacion4,
			String numeroOtraHabilitacion4, Boolean capital,
			Date fechaVencimientoCapital, String numeroHabilitacionCapital,
			String libreDeudaInfracciones, String libreDeudaPatentes,
			Boolean impuestoDocente, Boolean contratoFirmado, String accidente,
			List<AutonomoView> patcomList, TipoCombustibleEnum tipoCombustible,
			String comunicacion, String observacionesComunicacion,
			Double altura, Double ancho, Double largo) {
		super();
		this.id = id;
		this.numeroMovil = numeroMovil;
		this.patente = patente;
		this.descripcion = descripcion;
		this.asignacionMovil = asignacionMovil;
		this.estado = estado;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.controlado = controlado;
		this.comodato = comodato;
		this.chofer1 = chofer1;
		this.parentezcoChofer1 = parentezcoChofer1;
		this.chofer2 = chofer2;
		this.parentezcoChofer2 = parentezcoChofer2;
		this.chofer3 = chofer3;
		this.parentezcoChofer3 = parentezcoChofer3;
		this.propietario = propietario;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.tipo = tipo;
		this.motor = motor;
		this.numeroMotor = numeroMotor;
		this.potencia = potencia;
		this.chasis = chasis;
		this.numeroChasis = numeroChasis;
		this.cantidadEjes = cantidadEjes;
		this.tara = tara;
		this.capacidadCarga = capacidadCarga;
		this.prenda = prenda;
		this.fechaPrenda = fechaPrenda;
		this.acreedor = acreedor;
		this.informeDominio = informeDominio;
		this.fechaVerificacionTecnica = fechaVerificacionTecnica;
		this.numeroVerificacionTecnica = numeroVerificacionTecnica;
		this.fechaSenasa = fechaSenasa;
		this.numeroSenasa = numeroSenasa;
		this.fechaOtraHabilitacion1 = fechaOtraHabilitacion1;
		this.numeroOtraHabilitacion1 = numeroOtraHabilitacion1;
		this.fechaOtraHabilitacion2 = fechaOtraHabilitacion2;
		this.numeroOtraHabilitacion2 = numeroOtraHabilitacion2;
		this.fechaOtraHabilitacion3 = fechaOtraHabilitacion3;
		this.numeroOtraHabilitacion3 = numeroOtraHabilitacion3;
		this.fechaOtraHabilitacion4 = fechaOtraHabilitacion4;
		this.numeroOtraHabilitacion4 = numeroOtraHabilitacion4;
		this.capital = capital;
		this.fechaVencimientoCapital = fechaVencimientoCapital;
		this.numeroHabilitacionCapital = numeroHabilitacionCapital;
		this.libreDeudaInfracciones = libreDeudaInfracciones;
		this.libreDeudaPatentes = libreDeudaPatentes;
		this.impuestoDocente = impuestoDocente;
		this.contratoFirmado = contratoFirmado;
		this.accidente = accidente;
		this.patcomList = patcomList;
		this.tipoCombustible = tipoCombustible;
		this.comunicacion = comunicacion;
		this.observacionesComunicacion = observacionesComunicacion;
		this.altura = altura;
		this.ancho = ancho;
		this.largo = largo;
	}

	public MovilView() {
		this(null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Long getNumeroMovil() {
		return numeroMovil;
	}

	public void setNumeroMovil(Long numeroMovil) {
		this.numeroMovil = numeroMovil;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public AsignacionMovilEnum getAsignacionMovil() {
		return asignacionMovil;
	}

	public void setAsignacionMovil(AsignacionMovilEnum asignacionMovil) {
		this.asignacionMovil = asignacionMovil;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
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

	public Boolean getControlado() {
		return controlado;
	}

	public void setControlado(Boolean controlado) {
		this.controlado = controlado;
	}

	public Boolean getComodato() {
		return comodato;
	}

	public void setComodato(Boolean comodato) {
		this.comodato = comodato;
	}

	public Chofer getChofer1() {
		return chofer1;
	}

	public void setChofer1(Chofer chofer1) {
		this.chofer1 = chofer1;
	}

	public ParentezcoEnum getParentezcoChofer1() {
		return parentezcoChofer1;
	}

	public void setParentezcoChofer1(ParentezcoEnum parentezcoChofer1) {
		this.parentezcoChofer1 = parentezcoChofer1;
	}

	public Chofer getChofer2() {
		return chofer2;
	}

	public void setChofer2(Chofer chofer2) {
		this.chofer2 = chofer2;
	}

	public ParentezcoEnum getParentezcoChofer2() {
		return parentezcoChofer2;
	}

	public void setParentezcoChofer2(ParentezcoEnum parentezcoChofer2) {
		this.parentezcoChofer2 = parentezcoChofer2;
	}

	public Chofer getChofer3() {
		return chofer3;
	}

	public void setChofer3(Chofer chofer3) {
		this.chofer3 = chofer3;
	}

	public ParentezcoEnum getParentezcoChofer3() {
		return parentezcoChofer3;
	}

	public void setParentezcoChofer3(ParentezcoEnum parentezcoChofer3) {
		this.parentezcoChofer3 = parentezcoChofer3;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
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

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getNumeroMotor() {
		return numeroMotor;
	}

	public void setNumeroMotor(String numeroMotor) {
		this.numeroMotor = numeroMotor;
	}

	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public String getChasis() {
		return chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public String getNumeroChasis() {
		return numeroChasis;
	}

	public void setNumeroChasis(String numeroChasis) {
		this.numeroChasis = numeroChasis;
	}

	public Integer getCantidadEjes() {
		return cantidadEjes;
	}

	public void setCantidadEjes(Integer cantidadEjes) {
		this.cantidadEjes = cantidadEjes;
	}

	public Integer getTara() {
		return tara;
	}

	public void setTara(Integer tara) {
		this.tara = tara;
	}

	public Integer getCapacidadCarga() {
		return capacidadCarga;
	}

	public void setCapacidadCarga(Integer capacidadCarga) {
		this.capacidadCarga = capacidadCarga;
	}

	public Boolean getPrenda() {
		return prenda;
	}

	public void setPrenda(Boolean prenda) {
		this.prenda = prenda;
	}

	public Date getFechaPrenda() {
		return fechaPrenda;
	}

	public void setFechaPrenda(Date fechaPrenda) {
		this.fechaPrenda = fechaPrenda;
	}

	public String getAcreedor() {
		return acreedor;
	}

	public void setAcreedor(String acreedor) {
		this.acreedor = acreedor;
	}

	public Boolean getInformeDominio() {
		return informeDominio;
	}

	public void setInformeDominio(Boolean informeDominio) {
		this.informeDominio = informeDominio;
	}

	public Date getFechaVerificacionTecnica() {
		return fechaVerificacionTecnica;
	}

	public void setFechaVerificacionTecnica(Date fechaVerificacionTecnica) {
		this.fechaVerificacionTecnica = fechaVerificacionTecnica;
	}

	public String getNumeroVerificacionTecnica() {
		return numeroVerificacionTecnica;
	}

	public void setNumeroVerificacionTecnica(String numeroVerificacionTecnica) {
		this.numeroVerificacionTecnica = numeroVerificacionTecnica;
	}

	public Date getFechaSenasa() {
		return fechaSenasa;
	}

	public void setFechaSenasa(Date fechaSenasa) {
		this.fechaSenasa = fechaSenasa;
	}

	public String getNumeroSenasa() {
		return numeroSenasa;
	}

	public void setNumeroSenasa(String numeroSenasa) {
		this.numeroSenasa = numeroSenasa;
	}

	public Date getFechaOtraHabilitacion1() {
		return fechaOtraHabilitacion1;
	}

	public void setFechaOtraHabilitacion1(Date fechaOtraHabilitacion1) {
		this.fechaOtraHabilitacion1 = fechaOtraHabilitacion1;
	}

	public String getNumeroOtraHabilitacion1() {
		return numeroOtraHabilitacion1;
	}

	public void setNumeroOtraHabilitacion1(String numeroOtraHabilitacion1) {
		this.numeroOtraHabilitacion1 = numeroOtraHabilitacion1;
	}

	public Date getFechaOtraHabilitacion2() {
		return fechaOtraHabilitacion2;
	}

	public void setFechaOtraHabilitacion2(Date fechaOtraHabilitacion2) {
		this.fechaOtraHabilitacion2 = fechaOtraHabilitacion2;
	}

	public String getNumeroOtraHabilitacion2() {
		return numeroOtraHabilitacion2;
	}

	public void setNumeroOtraHabilitacion2(String numeroOtraHabilitacion2) {
		this.numeroOtraHabilitacion2 = numeroOtraHabilitacion2;
	}

	public Date getFechaOtraHabilitacion3() {
		return fechaOtraHabilitacion3;
	}

	public void setFechaOtraHabilitacion3(Date fechaOtraHabilitacion3) {
		this.fechaOtraHabilitacion3 = fechaOtraHabilitacion3;
	}

	public String getNumeroOtraHabilitacion3() {
		return numeroOtraHabilitacion3;
	}

	public void setNumeroOtraHabilitacion3(String numeroOtraHabilitacion3) {
		this.numeroOtraHabilitacion3 = numeroOtraHabilitacion3;
	}

	public Date getFechaOtraHabilitacion4() {
		return fechaOtraHabilitacion4;
	}

	public void setFechaOtraHabilitacion4(Date fechaOtraHabilitacion4) {
		this.fechaOtraHabilitacion4 = fechaOtraHabilitacion4;
	}

	public String getNumeroOtraHabilitacion4() {
		return numeroOtraHabilitacion4;
	}

	public void setNumeroOtraHabilitacion4(String numeroOtraHabilitacion4) {
		this.numeroOtraHabilitacion4 = numeroOtraHabilitacion4;
	}

	public Boolean getCapital() {
		return capital;
	}

	public void setCapital(Boolean capital) {
		this.capital = capital;
	}

	public Date getFechaVencimientoCapital() {
		return fechaVencimientoCapital;
	}

	public void setFechaVencimientoCapital(Date fechaVencimientoCapital) {
		this.fechaVencimientoCapital = fechaVencimientoCapital;
	}

	public String getNumeroHabilitacionCapital() {
		return numeroHabilitacionCapital;
	}

	public void setNumeroHabilitacionCapital(String numeroHabilitacionCapital) {
		this.numeroHabilitacionCapital = numeroHabilitacionCapital;
	}

	public String getLibreDeudaInfracciones() {
		return libreDeudaInfracciones;
	}

	public void setLibreDeudaInfracciones(String libreDeudaInfracciones) {
		this.libreDeudaInfracciones = libreDeudaInfracciones;
	}

	public String getLibreDeudaPatentes() {
		return libreDeudaPatentes;
	}

	public void setLibreDeudaPatentes(String libreDeudaPatentes) {
		this.libreDeudaPatentes = libreDeudaPatentes;
	}

	public Boolean getImpuestoDocente() {
		return impuestoDocente;
	}

	public void setImpuestoDocente(Boolean impuestoDocente) {
		this.impuestoDocente = impuestoDocente;
	}

	public Boolean getContratoFirmado() {
		return contratoFirmado;
	}

	public void setContratoFirmado(Boolean contratoFirmado) {
		this.contratoFirmado = contratoFirmado;
	}

	public String getAccidente() {
		return accidente;
	}

	public void setAccidente(String accidente) {
		this.accidente = accidente;
	}

	public List<AutonomoView> getPatcomList() {
		return patcomList;
	}

	public void setPatcomList(List<AutonomoView> patcomList) {
		this.patcomList = patcomList;
	}

	public TipoCombustibleEnum getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(TipoCombustibleEnum tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public String getComunicacion() {
		return comunicacion;
	}

	public void setComunicacion(String comunicacion) {
		this.comunicacion = comunicacion;
	}

	public String getObservacionesComunicacion() {
		return observacionesComunicacion;
	}

	public void setObservacionesComunicacion(String observacionesComunicacion) {
		this.observacionesComunicacion = observacionesComunicacion;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getAncho() {
		return ancho;
	}

	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

	public Double getLargo() {
		return largo;
	}

	public void setLargo(Double largo) {
		this.largo = largo;
	}

	public Double getMetrosCubicos() {

		Double metrosCubicos = null;
		if (altura != null && ancho != null && largo != null) {
			metrosCubicos = altura * ancho * largo;
		}

		if (metrosCubicos == null) {
			metrosCubicos = 0.0;
		}

		BigDecimal bd = new BigDecimal(Double.toString(metrosCubicos));
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

}
