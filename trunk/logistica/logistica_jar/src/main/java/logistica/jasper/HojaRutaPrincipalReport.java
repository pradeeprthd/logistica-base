package logistica.jasper;

import java.util.Date;

public class HojaRutaPrincipalReport {
	private String numeroHojaRuta;
	private String sucursal;
	private Date fechaEmision;
	private String prefijoNumero;
	private String cliente;
	private String chofer;
	private String numeroMovilPatente;
	private String numeroRemito;
	private String estado;

	public HojaRutaPrincipalReport(String numeroHojaRuta, String sucursal,
			Date fechaEmision, String prefijoNumero, String cliente,
			String chofer, String numeroMovilPatente, String numeroRemito,
			String estado) {
		super();
		this.numeroHojaRuta = numeroHojaRuta;
		this.sucursal = sucursal;
		this.fechaEmision = fechaEmision;
		this.prefijoNumero = prefijoNumero;
		this.cliente = cliente;
		this.chofer = chofer;
		this.numeroMovilPatente = numeroMovilPatente;
		this.numeroRemito = numeroRemito;
		this.estado = estado;
	}

	public String getNumeroHojaRuta() {
		return numeroHojaRuta;
	}

	public void setNumeroHojaRuta(String numeroHojaRuta) {
		this.numeroHojaRuta = numeroHojaRuta;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getPrefijoNumero() {
		return prefijoNumero;
	}

	public void setPrefijoNumero(String prefijoNumero) {
		this.prefijoNumero = prefijoNumero;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getChofer() {
		return chofer;
	}

	public void setChofer(String chofer) {
		this.chofer = chofer;
	}

	public String getNumeroMovilPatente() {
		return numeroMovilPatente;
	}

	public void setNumeroMovilPatente(String numeroMovilPatente) {
		this.numeroMovilPatente = numeroMovilPatente;
	}

	public String getNumeroRemito() {
		return numeroRemito;
	}

	public void setNumeroRemito(String numeroRemito) {
		this.numeroRemito = numeroRemito;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
