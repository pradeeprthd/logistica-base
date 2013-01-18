package logistica.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import logistica.common.BaseModel;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MovilPropietarioDetalle extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "MovilPropietarioSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Basic
	private Date fechaTitularDesde;

	@Basic
	private Date fechaCedulaVerde;

	@Basic
	private Integer numeroCedulaVerde;

	@Basic
	private Integer numeroTitulo;

	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "movilID")
	private Movil movil;	

	public MovilPropietarioDetalle(Long id, Date fechaTitularDesde,
			Date fechaCedulaVerde, Integer numeroCedulaVerde,
			Integer numeroTitulo, Movil movil) {
		super();
		this.id = id;
		this.fechaTitularDesde = fechaTitularDesde;
		this.fechaCedulaVerde = fechaCedulaVerde;
		this.numeroCedulaVerde = numeroCedulaVerde;
		this.numeroTitulo = numeroTitulo;
		this.movil = movil;
	}

	public MovilPropietarioDetalle() {
		this(null, null, null, null, null, new Movil());
	}

	public Date getFechaTitularDesde() {
		return fechaTitularDesde;
	}

	public void setFechaTitularDesde(Date fechaTitularDesde) {
		this.fechaTitularDesde = fechaTitularDesde;
	}

	public Integer getNumeroCedulaVerde() {
		return numeroCedulaVerde;
	}

	public void setNumeroCedulaVerde(Integer numeroCedulaVerde) {
		this.numeroCedulaVerde = numeroCedulaVerde;
	}

	public Integer getNumeroTitulo() {
		return numeroTitulo;
	}

	public void setNumeroTitulo(Integer numeroTitulo) {
		this.numeroTitulo = numeroTitulo;
	}

	public Date getFechaCedulaVerde() {
		return fechaCedulaVerde;
	}

	public void setFechaCedulaVerde(Date fechaCedulaVerde) {
		this.fechaCedulaVerde = fechaCedulaVerde;
	}

	@Override
	public Long getID() {
		return id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}
}
