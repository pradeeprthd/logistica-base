package logistica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import logistica.common.BaseModel;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "descripcion",
		"provinciaID" }) })
public class Localidad extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "LocalidadSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Column(length = 200, nullable = false)
	private String descripcion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "provinciaID")
	private Provincia provincia;

	public Localidad(Long id, String descripcion, Provincia provincia) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.provincia = provincia;
	}

	public Localidad() {
		this(null, null, null);
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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
}
