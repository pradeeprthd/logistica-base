package com.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Movil;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DetalleAsignacionView implements Serializable {
	private Long id;
	private Movil movil;
	private Date horarioEntrada;
	private Date horarioSalida;
	private Date horarioPedidoFlete;
	private String nombreAgenciaFlete;
	private String codigoCoto;

	public DetalleAsignacionView(Long id, Movil movil, Date horarioEntrada,
			Date horarioSalida, Date horarioPedidoFlete,
			String nombreAgenciaFlete, String codigoCoto) {
		super();
		this.id = id;
		this.movil = movil;
		this.horarioEntrada = horarioEntrada;
		this.horarioSalida = horarioSalida;
		this.horarioPedidoFlete = horarioPedidoFlete;
		this.nombreAgenciaFlete = nombreAgenciaFlete;
		this.codigoCoto = codigoCoto;
	}

	public DetalleAsignacionView() {
		this(null, null, null, null, null, null, null);
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

	public Date getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(Date horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public Date getHorarioSalida() {
		return horarioSalida;
	}

	public void setHorarioSalida(Date horarioSalida) {
		this.horarioSalida = horarioSalida;
	}

	public Date getHorarioPedidoFlete() {
		return horarioPedidoFlete;
	}

	public void setHorarioPedidoFlete(Date horarioPedidoFlete) {
		this.horarioPedidoFlete = horarioPedidoFlete;
	}

	public String getNombreAgenciaFlete() {
		return nombreAgenciaFlete;
	}

	public void setNombreAgenciaFlete(String nombreAgenciaFlete) {
		this.nombreAgenciaFlete = nombreAgenciaFlete;
	}

	public String getCodigoCoto() {
		return codigoCoto;
	}

	public void setCodigoCoto(String codigoCoto) {
		this.codigoCoto = codigoCoto;
	}

	public String getLabel() {
		String label = null;

		if (movil != null) {
			label = movil.getNumeroMovil() + "-" + movil.getPatente();
		} else {
			if (nombreAgenciaFlete != null) {
				label = label + " Flete: " + nombreAgenciaFlete;
			}
		}

		return label;
	}
}
