package com.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import logistica.model.Chofer;
import logistica.model.Movil;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class OtrosServiciosView implements Serializable {

	private Long id;

	@NotNull(message = "Valor requerido")
	private Movil movil;

	@NotNull(message = "Valor requerido")
	private Chofer chofer;

	private Date fecha;

	@NotNull(message = "Valor requerido")
	private Date horarioEntrada;

	@NotNull(message = "Valor requerido")
	@Size(min = 1, max = 200, message = "La descripción de la tarea asignada debe tener entre 1 y 200 caracteres.")
	private String asignadoA;

	public OtrosServiciosView(Long id, Movil movil, Chofer chofer, Date fecha,
			Date horarioEntrada, String asignadoA) {
		super();
		this.id = id;
		this.movil = movil;
		this.chofer = chofer;
		this.fecha = fecha;
		this.horarioEntrada = horarioEntrada;
		this.asignadoA = asignadoA;
	}

	public OtrosServiciosView() {
		this(null, null, null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Movil getMovil() {
		return movil;
	}

	public void setMovil(Movil movil) {
		this.movil = movil;
	}

	public Chofer getChofer() {
		return chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(Date horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public String getAsignadoA() {
		return asignadoA;
	}

	public void setAsignadoA(String asignadoA) {
		this.asignadoA = asignadoA;
	}
}
