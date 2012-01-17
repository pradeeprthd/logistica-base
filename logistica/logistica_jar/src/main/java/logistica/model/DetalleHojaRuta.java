package logistica.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import logistica.common.BaseModel;
import logistica.type.UnidadMedida;

@SuppressWarnings("serial")
@Entity
public class DetalleHojaRuta extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "HojaRutaSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Column(length = 200, nullable = false)
	private String direccion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "localidadID")
	private Localidad localidad;

	@Enumerated(EnumType.STRING)
	private UnidadMedida unidadMedida;

	@Basic
	private Integer cantidad;

	@Basic
	private Date fechaDesde;

	@Basic
	private Date fechaHasta;

	public DetalleHojaRuta(Long id, String direccion, Localidad localidad,
			UnidadMedida unidadMedida, Integer cantidad, Date fechaDesde,
			Date fechaHasta) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.localidad = localidad;
		this.unidadMedida = unidadMedida;
		this.cantidad = cantidad;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
	}

	public DetalleHojaRuta() {
		this(null, null, null, null, null, null, null);
	}

	@Override
	public Long getID() {
		return id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	@Override
	public boolean equals(Object detalleHojaRuta) {
		boolean ret = true;
		DetalleHojaRuta detalle2 = (DetalleHojaRuta) detalleHojaRuta;
		ret = direccion.equalsIgnoreCase(detalle2.getDireccion());
		if (localidad != null && detalle2.getLocalidad() != null) {
			ret = ret
					& localidad.getID().intValue() == detalle2.getLocalidad()
							.getID().intValue();
		} else if (localidad == null && detalle2.getLocalidad() == null) {
			ret = true;
		} else {
			ret = false;
		}
		ret = ret && unidadMedida == detalle2.getUnidadMedida();
		ret = ret && cantidad == detalle2.getCantidad();
		ret = ret && fechaDesde == detalle2.getFechaDesde();
		ret = ret && fechaHasta == detalle2.getFechaHasta();
		return ret;
	}

}
