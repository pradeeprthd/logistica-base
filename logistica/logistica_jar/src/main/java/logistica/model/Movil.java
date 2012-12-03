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

	public Movil(Long id, Long numeroMovil, String patente, String descripcion,
			AsignacionMovilEnum asignacionMovil, EstadoEnum estado,
			Date fechaIngreso, Date fechaEgreso, Boolean controlado,
			Boolean comodato, Chofer chofer1, ParentezcoEnum parentezcoChofer1,
			Chofer chofer2, ParentezcoEnum parentezcoChofer2, Chofer chofer3,
			ParentezcoEnum parentezcoChofer3, Propietario propietario) {
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
		this.chofer2 = chofer2;
		this.chofer3 = chofer3;
		this.propietario = propietario;
		this.parentezcoChofer1 = parentezcoChofer1;
		this.parentezcoChofer2 = parentezcoChofer2;
		this.parentezcoChofer3 = parentezcoChofer3;
	}

	public Movil() {
		this(null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null);
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
