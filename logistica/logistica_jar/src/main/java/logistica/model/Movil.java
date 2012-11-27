package logistica.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import logistica.common.BaseModel;
import logistica.type.AsignacionMovilEnum;
import logistica.type.EstadoEnum;

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

	public Movil(Long id, Long numeroMovil, String patente, String descripcion,
			AsignacionMovilEnum asignacionMovil, EstadoEnum estado,
			Date fechaIngreso, Date fechaEgreso, Boolean controlado,
			Boolean comodato) {
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
	}

	public Movil() {
		this(null, null, null, null, null, null, null, null, null, null);
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
