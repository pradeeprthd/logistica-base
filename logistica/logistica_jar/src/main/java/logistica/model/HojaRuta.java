package logistica.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import logistica.common.BaseModel;

@SuppressWarnings("serial")
@Entity
public class HojaRuta extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "HojaRutaSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sucursalID")
	private Sucursal sucursal;

	@Basic
	private Date fechaEmision;

	@Basic
	private Long prefijo;

	@Basic
	private Long numero;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clienteID")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "choferID")
	private Chofer chofer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movilID")
	private Movil movil;

	@Column(length = 50, nullable = false)
	private String numeroRemito;

	@Column(length = 200, nullable = false)
	private String direccion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "localidadID")
	private Localidad localidad;

	@Column(length = 200)
	private String observaciones;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
	private List<DetalleHojaRuta> detalleHojaRutaList;

	public HojaRuta(Long id, Sucursal sucursal, Date fechaEmision,
			Long prefijo, Long numero, Cliente cliente, Chofer chofer,
			Movil movil, String numeroRemito, String direccion,
			Localidad localidad, String observaciones,
			List<DetalleHojaRuta> detalleHojaRutaList) {
		super();
		this.id = id;
		this.sucursal = sucursal;
		this.fechaEmision = fechaEmision;
		this.prefijo = prefijo;
		this.numero = numero;
		this.cliente = cliente;
		this.chofer = chofer;
		this.movil = movil;
		this.numeroRemito = numeroRemito;
		this.direccion = direccion;
		this.localidad = localidad;
		this.observaciones = observaciones;
		this.detalleHojaRutaList = detalleHojaRutaList;
	}

	public HojaRuta() {
		this(null, null, null, null, null, null, null, null, null, null, null,
				null, null);
	}

	@Override
	public Long getID() {
		return id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Long getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(Long prefijo) {
		this.prefijo = prefijo;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNumeroRemito() {
		return numeroRemito;
	}

	public void setNumeroRemito(String numeroRemito) {
		this.numeroRemito = numeroRemito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Chofer getChofer() {
		return chofer;
	}

	public Movil getMovil() {
		return movil;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public List<DetalleHojaRuta> getDetalleHojaRutaList() {
		return detalleHojaRutaList;
	}
}
