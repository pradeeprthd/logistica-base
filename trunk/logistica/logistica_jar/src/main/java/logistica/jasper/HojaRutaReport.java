package logistica.jasper;

import java.util.Date;

public class HojaRutaReport {
	private String numeroHojaRuta;
	private Date fechaEmision;
	private String cliente;
	private String movil;
	private String chofer;
	private String numeroRemito;
	private String direccionOrigen;
	private String localidad;
	private Integer totalBultos;
	private Integer totalKilogramos;
	private Integer totalMetrosCubicos;
	private String observaciones;
	private String direccionEntrega;
	private String localidadEntrega;
	private String unidadMedida;
	private Integer cantidad;
	private Date fechaDesde;
	private Date fechaHasta;

	public HojaRutaReport(String numeroHojaRuta, Date fechaEmision,
			String cliente, String movil, String chofer, String numeroRemito,
			String direccionOrigen, String localidad, Integer totalBultos,
			Integer totalKilogramos, Integer totalMetrosCubicos,
			String observaciones, String direccionEntrega,
			String localidadEntrega, String unidadMedida, Integer cantidad,
			Date fechaDesde, Date fechaHasta) {
		super();
		this.numeroHojaRuta = numeroHojaRuta;
		this.fechaEmision = fechaEmision;
		this.cliente = cliente;
		this.movil = movil;
		this.chofer = chofer;
		this.numeroRemito = numeroRemito;
		this.direccionOrigen = direccionOrigen;
		this.localidad = localidad;
		this.totalBultos = totalBultos;
		this.totalKilogramos = totalKilogramos;
		this.totalMetrosCubicos = totalMetrosCubicos;
		this.observaciones = observaciones;
		this.direccionEntrega = direccionEntrega;
		this.localidadEntrega = localidadEntrega;
		this.unidadMedida = unidadMedida;
		this.cantidad = cantidad;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
	}

	public String getNumeroHojaRuta() {
		return numeroHojaRuta;
	}

	public void setNumeroHojaRuta(String numeroHojaRuta) {
		this.numeroHojaRuta = numeroHojaRuta;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getChofer() {
		return chofer;
	}

	public void setChofer(String chofer) {
		this.chofer = chofer;
	}

	public String getNumeroRemito() {
		return numeroRemito;
	}

	public void setNumeroRemito(String numeroRemito) {
		this.numeroRemito = numeroRemito;
	}

	public String getDireccionOrigen() {
		return direccionOrigen;
	}

	public void setDireccionOrigen(String direccionOrigen) {
		this.direccionOrigen = direccionOrigen;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Integer getTotalBultos() {
		return totalBultos;
	}

	public void setTotalBultos(Integer totalBultos) {
		this.totalBultos = totalBultos;
	}

	public Integer getTotalKilogramos() {
		return totalKilogramos;
	}

	public void setTotalKilogramos(Integer totalKilogramos) {
		this.totalKilogramos = totalKilogramos;
	}

	public Integer getTotalMetrosCubicos() {
		return totalMetrosCubicos;
	}

	public void setTotalMetrosCubicos(Integer totalMetrosCubicos) {
		this.totalMetrosCubicos = totalMetrosCubicos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public String getLocalidadEntrega() {
		return localidadEntrega;
	}

	public void setLocalidadEntrega(String localidadEntrega) {
		this.localidadEntrega = localidadEntrega;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
