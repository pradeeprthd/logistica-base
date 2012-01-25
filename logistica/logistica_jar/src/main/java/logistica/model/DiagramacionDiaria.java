package logistica.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import logistica.common.BaseModel;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@SuppressWarnings("serial")
public class DiagramacionDiaria extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "DiagramacionDiariaSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = true)
	private Date fecha;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection()
	@JoinTable(name = "diagramacionNovedades")
	@JoinColumn(name = "diagramacionDiariaID")
	private List<String> novedades;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "diagramacionDiariaID")
	private List<DetalleSucursal> detalleSucursalList;

	public DiagramacionDiaria(Long id, Date fecha, List<String> novedades,
			List<DetalleSucursal> detalleSucursalList) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.novedades = novedades;
		this.detalleSucursalList = detalleSucursalList;
	}

	public DiagramacionDiaria() {
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<String> getNovedades() {
		return novedades;
	}

	public void setNovedades(List<String> novedades) {
		this.novedades = novedades;
	}

	public List<DetalleSucursal> getDetalleSucursalList() {
		return detalleSucursalList;
	}

	public void setDetalleSucursalList(List<DetalleSucursal> detalleSucursalList) {
		this.detalleSucursalList = detalleSucursalList;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		DiagramacionDiaria diagramacionDiaria = new DiagramacionDiaria(id,
				fecha, novedades, detalleSucursalList);
		return diagramacionDiaria;
	}
}
