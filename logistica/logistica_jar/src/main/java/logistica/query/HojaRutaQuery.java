package logistica.query;

import java.util.Date;

import logistica.type.EstadoHojaRutaEnum;

public class HojaRutaQuery extends BaseQuery {

	private Date fechaEmision;
	private Long prefijo;
	private Long numero;
	private EstadoHojaRutaEnum estadoHojaRutaEnum;

	public HojaRutaQuery(Long id, Date fechaEmision, Long prefijo, Long numero,
			EstadoHojaRutaEnum estadoHojaRutaEnum) {
		super(id);
		this.fechaEmision = fechaEmision;
		this.prefijo = prefijo;
		this.numero = numero;
		this.estadoHojaRutaEnum = estadoHojaRutaEnum;
	}

	public HojaRutaQuery() {
		this(null, null, null, null, null);
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

	public EstadoHojaRutaEnum getEstadoHojaRutaEnum() {
		return estadoHojaRutaEnum;
	}

	public void setEstadoHojaRutaEnum(EstadoHojaRutaEnum estadoHojaRutaEnum) {
		this.estadoHojaRutaEnum = estadoHojaRutaEnum;
	}
}
