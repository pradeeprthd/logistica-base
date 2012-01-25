package logistica.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import logistica.common.BaseModel;

@Entity
@SuppressWarnings("serial")
public class DetalleAsignacion extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "DetalleAsignacionSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movilID")
	private Movil movil;

	@Column(length = 200)
	private String descripcionFlete;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date horarioEntrada;

	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioSalida;

	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioPedidoFlete;

	@Column(length = 200)
	private String nombreAgenciaFlete;

	@Column(length = 200)
	private String codigoCoto;

	public DetalleAsignacion(Long id, Movil movil, String descripcionFlete,
			Date horarioEntrada, Date horarioSalida, Date horarioPedidoFlete,
			String nombreAgenciaFlete, String codigoCoto) {
		super();
		this.id = id;
		this.movil = movil;
		this.descripcionFlete = descripcionFlete;
		this.horarioEntrada = horarioEntrada;
		this.horarioSalida = horarioSalida;
		this.horarioPedidoFlete = horarioPedidoFlete;
		this.nombreAgenciaFlete = nombreAgenciaFlete;
		this.codigoCoto = codigoCoto;
	}

	public DetalleAsignacion() {
		this(null, null, null, null, null, null, null, null);
	}

	@Override
	public Long getID() {
		return id;
	}

	@Override
	public void setID(Long id) {
		this.id = id;
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}

	public Date getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(Date horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public Date getHorarioSalida() {
		return horarioSalida;
	}

	public void setHorarioSalida(Date horarioSalida) {
		this.horarioSalida = horarioSalida;
	}

	public Date getHorarioPedidoFlete() {
		return horarioPedidoFlete;
	}

	public void setHorarioPedidoFlete(Date horarioPedidoFlete) {
		this.horarioPedidoFlete = horarioPedidoFlete;
	}

	public String getNombreAgenciaFlete() {
		return nombreAgenciaFlete;
	}

	public void setNombreAgenciaFlete(String nombreAgenciaFlete) {
		this.nombreAgenciaFlete = nombreAgenciaFlete;
	}

	public String getCodigoCoto() {
		return codigoCoto;
	}

	public void setCodigoCoto(String codigoCoto) {
		this.codigoCoto = codigoCoto;
	}

	public String getDescripcionFlete() {
		return descripcionFlete;
	}

	public void setDescripcionFlete(String descripcionFlete) {
		this.descripcionFlete = descripcionFlete;
	}
}
