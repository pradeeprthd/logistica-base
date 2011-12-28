package logistica.model;

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
public class Provincia extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "ProvinciaSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Column(length = 200, nullable = false, unique = true)
	private String descripcion;

	public Provincia(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public Provincia() {
		this(null, null);
	}

	@Override
	public Long getID() {
		return id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
