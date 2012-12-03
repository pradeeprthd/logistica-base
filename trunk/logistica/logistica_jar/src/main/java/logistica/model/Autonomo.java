package logistica.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import logistica.common.BaseModel;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Autonomo extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "AutonomoSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Column(length = 20)
	private String numeroAutonomo;

	@Basic
	private Date fechaAutonomo;

	public Autonomo(Long id, String numeroAutonomo, Date fechaAutonomo) {
		this.id = id;
		this.numeroAutonomo = numeroAutonomo;
		this.fechaAutonomo = fechaAutonomo;
	}

	public Autonomo() {
		this(null, null, new Date());
	}

	public String getNumeroAutonomo() {
		return numeroAutonomo;
	}

	public void setNumeroAutonomo(String numeroAutonomo) {
		this.numeroAutonomo = numeroAutonomo;
	}

	public Date getFechaAutonomo() {
		return fechaAutonomo;
	}

	public void setFechaAutonomo(Date fechaAutonomo) {
		this.fechaAutonomo = fechaAutonomo;
	}

	@Override
	public Long getID() {
		return id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}
}
