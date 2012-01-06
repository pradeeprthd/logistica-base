package logistica.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import logistica.common.BaseModel;
import logistica.model.composite.Direccion;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Sujeto extends BaseModel {

	@Column(length = 200, nullable = false, unique = true)
	private String nombre;
	
	@Embedded
	private Direccion direccion;

	public Sujeto(String nombre,Direccion direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
