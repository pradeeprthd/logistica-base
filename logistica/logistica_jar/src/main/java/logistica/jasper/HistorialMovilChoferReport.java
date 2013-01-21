package logistica.jasper;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class HistorialMovilChoferReport implements Serializable {

	private Long numeroMovil;
	private String patente;
	private Date fecha;
	private String estado;
	private String nombreChofer;
	private String dniChofer;

	public HistorialMovilChoferReport() {
	}

	public HistorialMovilChoferReport(Long numeroMovil, String patente,
			Date fecha, String estado, String nombreChofer, String dniChofer) {
		super();
		this.numeroMovil = numeroMovil;
		this.patente = patente;
		this.fecha = fecha;
		this.estado = estado;
		this.nombreChofer = nombreChofer;
		this.dniChofer = dniChofer;
	}

	public Long getNumeroMovil() {
		return numeroMovil;
	}

	public void setNumeroMovil(Long numeroMovil) {
		this.numeroMovil = numeroMovil;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

	public String getDniChofer() {
		return dniChofer;
	}

	public void setDniChofer(String dniChofer) {
		this.dniChofer = dniChofer;
	}
}
