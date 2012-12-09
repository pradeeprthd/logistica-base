package com.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import logistica.model.Chofer;
import logistica.model.Propietario;
import logistica.type.AsignacionMovilEnum;
import logistica.type.EstadoEnum;
import logistica.type.ParentezcoEnum;

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
			String acreedor, Boolean informeDominio) {
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
	}

	public MovilView() {
		this(null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null);
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

}
