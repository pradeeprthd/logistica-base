package logistica.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import logistica.common.BaseModel;
import logistica.model.composite.Direccion;
import logistica.type.TipoInscripcionEnum;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Sujeto extends BaseModel {

	@Column(length = 200, nullable = false, unique = true)
	private String nombre;

	@Embedded
	private Direccion direccion;
	
	@Column(length = 30)
	private String dni;
	
	@Basic
	private Date fechaNacimiento;

	@Enumerated(EnumType.STRING)
	private TipoInscripcionEnum tipoInscripcion;

	@Column(length = 30)
	private String cuil;

	@Column(length = 30)
	private String cuit;

	@Column(length = 30)
	private String telefono;

	@Column(length = 50)
	private String observacionTelefono;

	@Column(length = 30)
	private String telefono2;

	@Column(length = 50)
	private String observacionTelefono2;

	@Column(length = 30)
	private String telefono3;

	@Column(length = 50)
	private String observacionTelefono3;
	
	public Sujeto(String nombre, Direccion direccion, String dni, Date fechaNacimiento,
			TipoInscripcionEnum tipoInscripcion, String cuil, String cuit,
			String telefono, String observacionTelefono, String telefono2,
			String observacionTelefono2, String telefono3,
			String observacionTelefono3) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoInscripcion = tipoInscripcion;
		this.cuil = cuil;
		this.cuit = cuit;
		this.telefono = telefono;
		this.observacionTelefono = observacionTelefono;
		this.telefono2 = telefono2;
		this.observacionTelefono2 = observacionTelefono2;
		this.telefono3 = telefono3;
		this.observacionTelefono3 = observacionTelefono3;
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

	public TipoInscripcionEnum getTipoInscripcion() {
		return tipoInscripcion;
	}

	public void setTipoInscripcion(TipoInscripcionEnum tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getObservacionTelefono() {
		return observacionTelefono;
	}

	public void setObservacionTelefono(String observacionTelefono) {
		this.observacionTelefono = observacionTelefono;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getObservacionTelefono2() {
		return observacionTelefono2;
	}

	public void setObservacionTelefono2(String observacionTelefono2) {
		this.observacionTelefono2 = observacionTelefono2;
	}

	public String getTelefono3() {
		return telefono3;
	}

	public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
	}

	public String getObservacionTelefono3() {
		return observacionTelefono3;
	}

	public void setObservacionTelefono3(String observacionTelefono3) {
		this.observacionTelefono3 = observacionTelefono3;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
