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
	private String descripcionFlete;
	private Date horarioEntrada;
	private Date horarioSalida;
	private Date horarioPedidoFlete;
	private String nombreAgenciaFlete;
	private String codigoCoto;
	private Boolean llegoMovil;

	public DetalleAsignacionView(Long id, Movil movil, String descripcionFlete,
			Date horarioEntrada, Date horarioSalida, Date horarioPedidoFlete,
			String nombreAgenciaFlete, String codigoCoto,Boolean llegoMovil) {
		super();
		this.id = id;
		this.movil = movil;
		this.descripcionFlete = descripcionFlete;
		this.horarioEntrada = horarioEntrada;
		this.horarioSalida = horarioSalida;
		this.horarioPedidoFlete = horarioPedidoFlete;
		this.nombreAgenciaFlete = nombreAgenciaFlete;
		this.codigoCoto = codigoCoto;
		this.llegoMovil = llegoMovil;
	}

	public DetalleAsignacionView() {
		this(null, null, null, null, null, null, null, null,null);
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

	public String getDescripcionFlete() {
		return descripcionFlete;
	}

	public void setDescripcionFlete(String descripcionFlete) {
		this.descripcionFlete = descripcionFlete;
	}

	public Boolean getLlegoMovil() {
		return llegoMovil;
	}

	public void setLlegoMovil(Boolean llegoMovil) {
		this.llegoMovil = llegoMovil;
	}

	public String getLabel() {
		String label = null;

		if (movil != null) {
			label = movil.getNumeroMovil() + "-" + movil.getPatente();
		} else {
			if (descripcionFlete != null && descripcionFlete.length() > 0) {
				label = descripcionFlete;
			}
			if (nombreAgenciaFlete != null && nombreAgenciaFlete.length() > 0) {
				label = label + " (" + nombreAgenciaFlete + ")";
			}
		}
		return label;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new DetalleAsignacionView(id, movil, descripcionFlete,
				horarioEntrada, horarioSalida, horarioPedidoFlete,
				nombreAgenciaFlete, codigoCoto, llegoMovil);
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = true;
		DetalleAsignacionView detalle = (DetalleAsignacionView) obj;

		if (movil != null && detalle.getMovil() != null) {
			ret = movil.equals(detalle.getMovil());
		} else if (movil == null && detalle.getMovil() == null) {
			ret = true;
		} else {
			ret = false;
		}

		if (descripcionFlete != null && detalle.getDescripcionFlete() != null) {
			ret = ret && descripcionFlete.equals(detalle.getDescripcionFlete());
		} else if (descripcionFlete == null
				&& detalle.getDescripcionFlete() == null) {
			ret = ret && true;
		} else {
			ret = false;
		}

		if (horarioEntrada != null && detalle.getHorarioEntrada() != null) {
			ret = ret
					&& horarioEntrada.getTime() == detalle.getHorarioEntrada()
							.getTime();
		} else if (horarioEntrada == null
				&& detalle.getHorarioEntrada() == null) {
			ret = ret && true;
		} else {
			ret = false;
		}

		if (horarioSalida != null && detalle.getHorarioSalida() != null) {
			ret = ret
					&& horarioSalida.getTime() == detalle.getHorarioSalida()
							.getTime();
		} else if (horarioSalida == null && detalle.getHorarioSalida() == null) {
			ret = ret && true;
		} else {
			ret = false;
		}

		if (horarioPedidoFlete != null
				&& detalle.getHorarioPedidoFlete() != null) {
			ret = ret
					&& horarioPedidoFlete.getTime() == detalle
							.getHorarioPedidoFlete().getTime();
		} else if (horarioPedidoFlete == null
				&& detalle.getHorarioPedidoFlete() == null) {
			ret = ret && true;
		} else {
			ret = false;
		}

		if (nombreAgenciaFlete != null
				&& detalle.getNombreAgenciaFlete() != null) {
			ret = ret
					&& nombreAgenciaFlete.equals(detalle
							.getNombreAgenciaFlete());
		} else if (nombreAgenciaFlete == null
				&& detalle.getNombreAgenciaFlete() == null) {
			ret = ret && true;
		} else {
			ret = false;
		}

		if (codigoCoto != null && detalle.getCodigoCoto() != null) {
			ret = ret && codigoCoto.equals(detalle.getCodigoCoto());
		} else if (codigoCoto == null && detalle.getCodigoCoto() == null) {
			ret = ret && true;
		} else {
			ret = false;
		}
		
		if (llegoMovil != null && detalle.getLlegoMovil() != null) {
			ret = ret && llegoMovil.equals(detalle.getLlegoMovil());
		} else if (llegoMovil == null && detalle.getLlegoMovil() == null) {
			ret = ret && true;
		} else {
			ret = false;
		}

		return ret;
	}
	
	public boolean isFleteAsignado(){
		boolean ret = false;
		
		if((descripcionFlete != null && !"".equals(descripcionFlete))||
				(nombreAgenciaFlete != null && !"".equals(nombreAgenciaFlete))){
			ret = true;
		}
		
		return ret;
	}
}
