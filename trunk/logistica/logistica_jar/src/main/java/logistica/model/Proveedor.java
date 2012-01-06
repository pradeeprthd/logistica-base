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
public class Proveedor extends Sujeto {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "ClienteSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	public Proveedor(Long id, String nombre, Direccion direccion) {
		super(nombre, direccion);
		this.id = id;
	}

	public Proveedor() {
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
}
