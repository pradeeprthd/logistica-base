package com.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class SucursalView implements Serializable {

	private Long id;

	@NotNull(message = "Valor requerido")
	@Size(min = 1, max = 200, message = "El nombre de la sucursal debe tener entre 1 y 200 caracteres.")
	private String nombre;

	@NotNull(message = "Valor requerido")
	private Long numeroSucursal;

	@NotNull(message = "Valor requerido")
	private Long numeroHojaRuta;

	@ManagedProperty("#{direccionView}")
	private DireccionView direccionView;

	public SucursalView(Long id, String nombre, Long numeroSucursal,
			Long numeroHojaRuta, DireccionView direccionView) {
		super();
		this.id = id;
		this.direccionView = direccionView;
		this.nombre = nombre;
		this.numeroSucursal = numeroSucursal;
		this.numeroHojaRuta = numeroHojaRuta;
	}

	public SucursalView() {
		this(null, null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DireccionView getDireccionView() {
		return direccionView;
	}

	public void setDireccionView(DireccionView direccionView) {
		this.direccionView = direccionView;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getNumeroSucursal() {
		return numeroSucursal;
	}

	public void setNumeroSucursal(Long numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	public Long getNumeroHojaRuta() {
		return numeroHojaRuta;
	}

	public void setNumeroHojaRuta(Long numeroHojaRuta) {
		this.numeroHojaRuta = numeroHojaRuta;
	}
}
