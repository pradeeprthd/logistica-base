package logistica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import logistica.common.BaseModel;


@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Authority extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "AuthoritySEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Column(length = 200, unique = true)
	private String nombre;

	public Authority(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Authority() {
		this(null, null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}
}
