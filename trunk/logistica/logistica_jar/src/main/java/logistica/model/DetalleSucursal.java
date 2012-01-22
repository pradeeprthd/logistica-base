package logistica.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import logistica.common.BaseModel;

@Entity
@SuppressWarnings("serial")
public class DetalleSucursal extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "DetalleSucursalSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sucursalID")
	private Sucursal sucursal;

	@Basic
	private Integer cantidadMoviles;

	@OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "detalleSucursalID")
	private List<DetalleAsignacion> detalleAsignacionList;

	public DetalleSucursal(Long id, Sucursal sucursal, Integer cantidadMoviles,
			List<DetalleAsignacion> detalleAsignacionList) {
		super();
		this.id = id;
		this.sucursal = sucursal;
		this.cantidadMoviles = cantidadMoviles;
		this.detalleAsignacionList = detalleAsignacionList;
	}

	public DetalleSucursal() {
		this(null, null, null, null);
	}

	@Override
	public Long getID() {
		return id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Integer getCantidadMoviles() {
		return cantidadMoviles;
	}

	public void setCantidadMoviles(Integer cantidadMoviles) {
		this.cantidadMoviles = cantidadMoviles;
	}

	public List<DetalleAsignacion> getDetalleAsignacionList() {
		return detalleAsignacionList;
	}

	public void setDetalleAsignacionList(
			List<DetalleAsignacion> detalleAsignacionList) {
		this.detalleAsignacionList = detalleAsignacionList;
	}
}
