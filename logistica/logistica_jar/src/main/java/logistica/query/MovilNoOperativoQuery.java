package logistica.query;

import java.util.Date;

public class MovilNoOperativoQuery extends BaseQuery {
	private String patente;
	private Long numeroMovil;
	private Date fecha;

	public MovilNoOperativoQuery(Long id, String patente, Long numeroMovil,
			Date fecha) {
		super(id);
		this.patente = patente;
		this.numeroMovil = numeroMovil;
		this.fecha = fecha;
	}

	public MovilNoOperativoQuery() {
		this(null, null, null, null);
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Long getNumeroMovil() {
		return numeroMovil;
	}

	public void setNumeroMovil(Long numeroMovil) {
		this.numeroMovil = numeroMovil;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
