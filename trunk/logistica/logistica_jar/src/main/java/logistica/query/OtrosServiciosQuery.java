package logistica.query;

import java.util.Date;

public class OtrosServiciosQuery extends BaseQuery {
	private String patente;
	private Long numeroMovil;
	private String nombreChofer;
	private Date fecha;

	public OtrosServiciosQuery(Long id, String patente, Long numeroMovil,
			String nombreChofer, Date fecha) {
		super(id);
		this.patente = patente;
		this.numeroMovil = numeroMovil;
		this.nombreChofer = nombreChofer;
		this.fecha = fecha;
	}

	public OtrosServiciosQuery() {
		this(null, null, null, null, null);
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

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
