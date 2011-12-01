package logistica.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import logistica.common.BaseModel;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Sujeto extends BaseModel {

	@Column(length = 200, nullable = false, unique = true)
	private String nombre;

	public Sujeto(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
