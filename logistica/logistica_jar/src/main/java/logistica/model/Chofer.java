package logistica.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import logistica.model.composite.Direccion;
import logistica.type.TipoInscripcionEnum;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Chofer extends Sujeto {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "ChoferSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Basic
	private Date fechaLibreataSanitaria;

	@Basic
	private Date fechaVencimientoRegistro;

	@Basic
	private Boolean aurizacionManejo;

	@Basic
	private Boolean partidaNacimiento;

	@Basic
	private Boolean seguroAccidente;

	@Basic
	private Boolean antecedentesBuenaConducta;

	@Basic
	private Boolean constanciaRegistro;

	@Column(length = 50)
	private String licenciaNacionalHabilitante;

	@Basic
	private Date fechaCurso;

	@Basic
	private Date fechaRegistro;

	@Column(length = 150)
	private String observaciones;

	/*
	 * @Lob
	 * 
	 * @Basic(fetch = FetchType.LAZY)
	 * 
	 * @Column(nullable = true)
	 * 
	 * @Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	 */
	@Transient
	private byte[] imagen;

	@Column(length = 200)
	private String rutaArchivo;

	@Column(length = 200)
	private String nombreArchivo;

	public Chofer(Long id, String nombre, Direccion direccion, String dni,
			Date fechaNacimiento, TipoInscripcionEnum tipoInscripcion,
			String cuil, String cuit, String telefono,
			String observacionTelefono, String telefono2,
			String observacionTelefono2, String telefono3,
			String observacionTelefono3, Date fechaLibreataSanitaria,
			Date fechaVencimientoRegistro, Boolean aurizacionManejo,
			Boolean partidaNacimiento, Boolean seguroAccidente,
			Boolean antecedentesBuenaConducta, Boolean constanciaRegistro,
			String licenciaNacionalHabilitante, Date fechaCurso,
			Date fechaRegistro, String observaciones, byte[] imagen,
			String rutaArchivo, String nombreArchivo) {
		super(nombre, direccion, dni, fechaNacimiento, tipoInscripcion, cuil,
				cuit, telefono, observacionTelefono, telefono2,
				observacionTelefono2, telefono3, observacionTelefono3);
		this.id = id;
		this.fechaLibreataSanitaria = fechaLibreataSanitaria;
		this.fechaVencimientoRegistro = fechaVencimientoRegistro;
		this.aurizacionManejo = aurizacionManejo;
		this.partidaNacimiento = partidaNacimiento;
		this.seguroAccidente = seguroAccidente;
		this.antecedentesBuenaConducta = antecedentesBuenaConducta;
		this.constanciaRegistro = constanciaRegistro;
		this.licenciaNacionalHabilitante = licenciaNacionalHabilitante;
		this.fechaCurso = fechaCurso;
		this.fechaRegistro = fechaRegistro;
		this.observaciones = observaciones;
		this.imagen = imagen;
		this.rutaArchivo = rutaArchivo;
		this.nombreArchivo = nombreArchivo;
	}

	public Chofer() {
		this(null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null);
	}

	@Override
	public Long getID() {
		return id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	public Date getFechaLibreataSanitaria() {
		return fechaLibreataSanitaria;
	}

	public void setFechaLibreataSanitaria(Date fechaLibreataSanitaria) {
		this.fechaLibreataSanitaria = fechaLibreataSanitaria;
	}

	public Date getFechaVencimientoRegistro() {
		return fechaVencimientoRegistro;
	}

	public void setFechaVencimientoRegistro(Date fechaVencimientoRegistro) {
		this.fechaVencimientoRegistro = fechaVencimientoRegistro;
	}

	public Boolean getAurizacionManejo() {
		return aurizacionManejo;
	}

	public void setAurizacionManejo(Boolean aurizacionManejo) {
		this.aurizacionManejo = aurizacionManejo;
	}

	public Boolean getPartidaNacimiento() {
		return partidaNacimiento;
	}

	public void setPartidaNacimiento(Boolean partidaNacimiento) {
		this.partidaNacimiento = partidaNacimiento;
	}

	public Boolean getSeguroAccidente() {
		return seguroAccidente;
	}

	public void setSeguroAccidente(Boolean seguroAccidente) {
		this.seguroAccidente = seguroAccidente;
	}

	public Boolean getAntecedentesBuenaConducta() {
		return antecedentesBuenaConducta;
	}

	public void setAntecedentesBuenaConducta(Boolean antecedentesBuenaConducta) {
		this.antecedentesBuenaConducta = antecedentesBuenaConducta;
	}

	public Boolean getConstanciaRegistro() {
		return constanciaRegistro;
	}

	public void setConstanciaRegistro(Boolean constanciaRegistro) {
		this.constanciaRegistro = constanciaRegistro;
	}

	public String getLicenciaNacionalHabilitante() {
		return licenciaNacionalHabilitante;
	}

	public void setLicenciaNacionalHabilitante(
			String licenciaNacionalHabilitante) {
		this.licenciaNacionalHabilitante = licenciaNacionalHabilitante;
	}

	public Date getFechaCurso() {
		return fechaCurso;
	}

	public void setFechaCurso(Date fechaCurso) {
		this.fechaCurso = fechaCurso;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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
}
