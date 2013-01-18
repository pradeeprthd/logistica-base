package logistica.jasper;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResumenDetalladoDetalleReport implements Serializable {

	private Long numeroMovil;
	private String patente;
	private String nombreChofer1;
	private String dniChofer1;
	private String nombreChofer2;
	private String dniChofer2;
	private String comuncacion;
	private Integer total;

	public ResumenDetalladoDetalleReport() {

	}

	public ResumenDetalladoDetalleReport(Long numeroMovil, String patente,
			String nombreChofer1, String dniChofer1, String nombreChofer2,
			String dniChofer2, String comuncacion, Integer total) {
		super();
		this.numeroMovil = numeroMovil;
		this.patente = patente;
		this.nombreChofer1 = nombreChofer1;
		this.dniChofer1 = dniChofer1;
		this.nombreChofer2 = nombreChofer2;
		this.dniChofer2 = dniChofer2;
		this.comuncacion = comuncacion;
		this.total = total;
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

	public String getNombreChofer1() {
		return nombreChofer1;
	}

	public void setNombreChofer1(String nombreChofer1) {
		this.nombreChofer1 = nombreChofer1;
	}

	public String getDniChofer1() {
		return dniChofer1;
	}

	public void setDniChofer1(String dniChofer1) {
		this.dniChofer1 = dniChofer1;
	}

	public String getNombreChofer2() {
		return nombreChofer2;
	}

	public void setNombreChofer2(String nombreChofer2) {
		this.nombreChofer2 = nombreChofer2;
	}

	public String getDniChofer2() {
		return dniChofer2;
	}

	public void setDniChofer2(String dniChofer2) {
		this.dniChofer2 = dniChofer2;
	}

	public String getComuncacion() {
		return comuncacion;
	}

	public void setComuncacion(String comuncacion) {
		this.comuncacion = comuncacion;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
