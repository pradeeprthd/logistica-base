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
import logistica.type.EstadoEnum;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistorialMovilChofer extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "HistorialMovilChoferSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movilID")
	private Movil movil;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "choferID")
	private Chofer chofer;

	@Enumerated(EnumType.STRING)
	private EstadoEnum estado;

	@Basic
	private Date fecha;

	public HistorialMovilChofer(Long id, Movil movil, Chofer chofer,
			EstadoEnum estado, Date fecha) {
		super();
		this.id = id;
		this.movil = movil;
		this.chofer = chofer;
		this.estado = estado;
		this.fecha = fecha;
	}

	public HistorialMovilChofer() {
		this(null, null, null, null, null);
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}

	public Chofer getChofer() {
		return chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
