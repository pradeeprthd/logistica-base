package logistica.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import logistica.model.composite.Direccion;
import logistica.type.TipoInscripcionEnum;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Propietario extends Sujeto {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "PropietarioSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Basic
	private Boolean partidaNacimiento;

	@Column(length = 150)
	private String observaciones;

	@Transient
	private byte[] imagen;

	@Column(length = 200)
	private String rutaArchivo;

	@Column(length = 200)
	private String nombreArchivo;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "propietarioID")
	private List<Autonomo> autonomoList;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "propietarioID")
	private List<Movil> movilList;

	public Propietario(Long id, String nombre, Direccion direccion, String dni,
			Date fechaNacimiento, TipoInscripcionEnum tipoInscripcion,
			String cuil, String cuit, String telefono,
			String observacionTelefono, String telefono2,
			String observacionTelefono2, String telefono3,
			String observacionTelefono3, Boolean partidaNacimiento,
			String observaciones, byte[] imagen, String rutaArchivo,
			String nombreArchivo, List<Movil> movilList,
			List<Autonomo> autonomoList) {
		super(nombre, direccion, dni, fechaNacimiento, tipoInscripcion, cuil,
				cuit, telefono, observacionTelefono, telefono2,
				observacionTelefono2, telefono3, observacionTelefono3);
		this.id = id;
		this.partidaNacimiento = partidaNacimiento;
		this.observaciones = observaciones;
		this.imagen = imagen;
		this.rutaArchivo = rutaArchivo;
		this.nombreArchivo = nombreArchivo;
		this.autonomoList = autonomoList;
		this.movilList = movilList;
	}

	public Propietario() {
		this(null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null);
	}

	@Override
	public Long getID() {
		return id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	public Boolean getPartidaNacimiento() {
		return partidaNacimiento;
	}

	public void setPartidaNacimiento(Boolean partidaNacimiento) {
		this.partidaNacimiento = partidaNacimiento;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public List<Autonomo> getAutonomoList() {
		return autonomoList;
	}

	public void setAutonomoList(List<Autonomo> autonomoList) {
		this.autonomoList = autonomoList;
	}

	public List<Movil> getMovilList() {
		return movilList;
	}

	public void setMovilList(List<Movil> movilList) {
		this.movilList = movilList;
	}
}
