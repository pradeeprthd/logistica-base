package logistica.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import logistica.common.BaseModel;
import logistica.model.composite.Direccion;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sucursal extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "SucuarsalSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Column(length = 200, unique = true, nullable = false)
	private String nombre;

	@Column(unique = true, nullable = false)
	private Long numeroSucursal;

	@Basic
	private Long numeroHojaRuta;

	@Embedded
	private Direccion direccion;

	public Sucursal(Long id, String nombre, Long numeroSucursal,
			Long numeroHojaRuta, Direccion direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numeroSucursal = numeroSucursal;
		this.numeroHojaRuta = numeroHojaRuta;
		this.direccion = direccion;
	}

	public Sucursal() {
		this(null, null, null, null, null);
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getNumeroSucursal() {
		return numeroSucursal;
	}

	public void setNumeroSucursal(Long numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	public Long getNumeroHojaRuta() {
		return numeroHojaRuta;
	}

	public void setNumeroHojaRuta(Long numeroHojaRuta) {
		this.numeroHojaRuta = numeroHojaRuta;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}
