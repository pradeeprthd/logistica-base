package logistica.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import logistica.common.BaseModel;
import logistica.type.AsignacionMovilEnum;
import logistica.type.EstadoEnum;
import logistica.type.ParentezcoEnum;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Movil extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "MovilSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Column(unique = true, nullable = false)
	private Long numeroMovil;

	@Column(length = 30, unique = true, nullable = false)
	private String patente;

	@Column(length = 200)
	private String descripcion;

	@Enumerated(EnumType.STRING)
	private AsignacionMovilEnum asignacionMovil;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Basic
	private Date fechaIngreso;

	@Basic
	private Date fechaEgreso;

	@Basic
	private Boolean controlado;

	@Basic
	private Boolean comodato;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chofer1ID")
	private Chofer chofer1;

	@Enumerated(EnumType.STRING)
	private ParentezcoEnum parentezcoChofer1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chofer2ID")
	private Chofer chofer2;

	@Enumerated(EnumType.STRING)
	private ParentezcoEnum parentezcoChofer2;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chofer3ID")
	private Chofer chofer3;

	@Enumerated(EnumType.STRING)
	private ParentezcoEnum parentezcoChofer3;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "propietarioID")
	private Propietario propietario;

	@Column(length = 200)
	private String marca;

	@Column(length = 200)
	private String modelo;

	@Basic
	private Integer anio;

	@Column(length = 200)
	private String tipo;

	@Column(length = 200)
	private String motor;

	@Column(length = 200)
	private String numeroMotor;

	@Column(length = 200)
	private String potencia;

	@Column(length = 200)
	private String chasis;

	@Column(length = 200)
	private String numeroChasis;

	@Basic
	private Integer cantidadEjes;

	@Basic
	private Integer tara;

	@Basic
	private Integer capacidadCarga;

	@Basic
	private Boolean prenda;

	@Basic
	private Date fechaPrenda;

	@Column(length = 200)
	private String acreedor;

	@Basic
	private Boolean informeDominio;

	public Movil(Long id, Long numeroMovil, String patente, String descripcion,
			AsignacionMovilEnum asignacionMovil, EstadoEnum estado,
			Date fechaIngreso, Date fechaEgreso, Boolean controlado,
			Boolean comodato, Chofer chofer1, ParentezcoEnum parentezcoChofer1,
			Chofer chofer2, ParentezcoEnum parentezcoChofer2, Chofer chofer3,
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

	public Movil() {
		this(null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null);
	}

	@Override
	public Long getID() {
		return id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
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

	public Chofer getChofer2() {
		return chofer2;
	}

	public void setChofer2(Chofer chofer2) {
		this.chofer2 = chofer2;
	}

	public Chofer getChofer3() {
		return chofer3;
	}

	public void setChofer3(Chofer chofer3) {
		this.chofer3 = chofer3;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public ParentezcoEnum getParentezcoChofer1() {
		return parentezcoChofer1;
	}

	public void setParentezcoChofer1(ParentezcoEnum parentezcoChofer1) {
		this.parentezcoChofer1 = parentezcoChofer1;
	}

	public ParentezcoEnum getParentezcoChofer2() {
		return parentezcoChofer2;
	}

	public void setParentezcoChofer2(ParentezcoEnum parentezcoChofer2) {
		this.parentezcoChofer2 = parentezcoChofer2;
	}

	public ParentezcoEnum getParentezcoChofer3() {
		return parentezcoChofer3;
	}

	public void setParentezcoChofer3(ParentezcoEnum parentezcoChofer3) {
		this.parentezcoChofer3 = parentezcoChofer3;
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

	@Override
	public boolean equals(Object movilArg) {
		boolean ret = false;
		Movil movil = (Movil) movilArg;
		if (numeroMovil != null && movil.getNumeroMovil() != null) {
			if (numeroMovil.intValue() == movil.getNumeroMovil().intValue()) {
				ret = true;
			}
		} else if (numeroMovil == null && movil.getNumeroMovil() == null) {
			ret = true;
		} else {
			ret = false;
		}

		return ret;
	}
}
