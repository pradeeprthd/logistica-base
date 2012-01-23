package com.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class SucursalCotoView implements Serializable {

	private Long id;

	private String nombre;

	@NotNull(message = "Valor requerido")
	private Long numeroSucursal;

	@NotNull(message = "Valor requerido")
	private Integer cantidadMoviles;

	@ManagedProperty("#{direccionView}")
	private DireccionView direccionView;

	public SucursalCotoView(Long id, String nombre, Long numeroSucursal,
			Integer cantidadMoviles, DireccionView direccionView) {
		super();
		this.id = id;
		this.direccionView = direccionView;
		this.nombre = nombre;
		this.numeroSucursal = numeroSucursal;
		this.cantidadMoviles = cantidadMoviles;
	}

	public SucursalCotoView() {
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

	public Integer getCantidadMoviles() {
		return cantidadMoviles;
	}

	public void setCantidadMoviles(Integer cantidadMoviles) {
		this.cantidadMoviles = cantidadMoviles;
	}
}
