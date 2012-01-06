package logistica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import logistica.model.composite.Direccion;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cliente extends Sujeto {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "ClienteSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Column(length = 100)
	private String apellido;

	public Cliente(Long id, String nombre, Direccion direccion, String apellido) {
		super(nombre, direccion);
		this.id = id;
		this.apellido = apellido;

	}

	public Cliente() {
		this(null, null, null, null);
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
