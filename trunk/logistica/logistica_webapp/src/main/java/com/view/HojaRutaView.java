package com.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import logistica.model.Chofer;
import logistica.model.Cliente;
import logistica.model.Localidad;
import logistica.model.Movil;
import logistica.model.Sucursal;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class HojaRutaView implements Serializable {

	private Long id;

	@NotNull(message = "Valor requerido")
	private Sucursal sucursal;

	@NotNull(message = "Valor requerido")
	private Date fechaEmision;

	@NotNull(message = "Valor requerido")
	private Long prefijo;

	@NotNull(message = "Valor requerido")
	private Long numero;

	@NotNull(message = "Valor requerido")
	private Cliente cliente;

	@NotNull(message = "Valor requerido")
	private Chofer chofer;

	@NotNull(message = "Valor requerido")
	private Movil movil;

	@NotNull(message = "Valor requerido")
	@Size(min = 1, max = 50, message = "El número de remito debe tener entre 1 y 50 caracteres.")
	private String numeroRemito;

	@NotNull(message = "Valor requerido")
	@Size(min = 1, max = 200, message = "La dirección debe tener entre 1 y 200 caracteres.")
	private String direccion;

	@NotNull(message = "Valor requerido")
	private Localidad localidad;

	private String observaciones;

	private List<DetalleHojaRutaView> detalleHojaRutaViewList;

	public HojaRutaView(Long id, Sucursal sucursal, Date fechaEmision,
			Long prefijo, Long numero, Cliente cliente, Chofer chofer,
			Movil movil, String numeroRemito, String direccion,
			Localidad localidad, String observaciones,
			List<DetalleHojaRutaView> detalleHojaRutaViewList) {
		super();
		this.id = id;
		this.sucursal = sucursal;
		this.fechaEmision = fechaEmision;
		this.prefijo = prefijo;
		this.numero = numero;
		this.cliente = cliente;
		this.chofer = chofer;
		this.movil = movil;
		this.numeroRemito = numeroRemito;
		this.direccion = direccion;
		this.localidad = localidad;
		this.observaciones = observaciones;
		this.detalleHojaRutaViewList = detalleHojaRutaViewList;
	}

	public HojaRutaView() {
		this(null, null, null, null, null, null, null, null, null, null, null,
				null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Chofer getChofer() {
		return chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}

	public String getNumeroRemito() {
		return numeroRemito;
	}

	public void setNumeroRemito(String numeroRemito) {
		this.numeroRemito = numeroRemito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<DetalleHojaRutaView> getDetalleHojaRutaViewList() {
		return detalleHojaRutaViewList;
	}

	public void setDetalleHojaRutaViewList(
			List<DetalleHojaRutaView> detalleHojaRutaViewList) {
		this.detalleHojaRutaViewList = detalleHojaRutaViewList;
	}
}
