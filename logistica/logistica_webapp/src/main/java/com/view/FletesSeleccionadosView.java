package com.view;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FletesSeleccionadosView implements Serializable,
		Comparable<FletesSeleccionadosView> {
	private Long numeroSucursal;
	private String descripcionFlete;
	private String nombreAgenciaFlete;
	private String codigoCoto;

	public FletesSeleccionadosView(Long numeroSucursal,
			String descripcionFlete, String nombreAgenciaFlete,
			String codigoCoto) {
		this.numeroSucursal = numeroSucursal;
		this.descripcionFlete = descripcionFlete;
		this.nombreAgenciaFlete = nombreAgenciaFlete;
		this.codigoCoto = codigoCoto;
	}

	public FletesSeleccionadosView() {
		this(null, null, null, null);
	}

	public Long getNumeroSucursal() {
		return numeroSucursal;
	}

	public void setNumeroSucursal(Long numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	public String getDescripcionFlete() {
		return descripcionFlete;
	}

	public void setDescripcionFlete(String descripcionFlete) {
		this.descripcionFlete = descripcionFlete;
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

	public int compareTo(FletesSeleccionadosView o) {
		int ret = 0;
		if (getNumeroSucursal().intValue() == o.getNumeroSucursal().intValue()) {
			ret = 0;
		} else if (getNumeroSucursal().intValue() > o.getNumeroSucursal()
				.intValue()) {
			ret = 1;
		} else {
			ret = -1;
		}

		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = true;
		FletesSeleccionadosView fleteSeleccionado = (FletesSeleccionadosView) obj;

		if (numeroSucursal != null
				&& fleteSeleccionado.getNumeroSucursal() != null) {
			ret = numeroSucursal.equals(fleteSeleccionado.getNumeroSucursal());
		} else if (numeroSucursal == null
				&& fleteSeleccionado.getNumeroSucursal() == null) {
			ret = true;
		} else {
			ret = false;
		}

		if (descripcionFlete != null
				&& fleteSeleccionado.getDescripcionFlete() != null) {
			ret = ret
					&& descripcionFlete.equals(fleteSeleccionado
							.getDescripcionFlete());
		} else if (descripcionFlete == null
				&& fleteSeleccionado.getDescripcionFlete() == null) {
			ret = ret && true;
		} else {
			ret = ret && false;
		}

		if (nombreAgenciaFlete != null
				&& fleteSeleccionado.getNombreAgenciaFlete() != null) {
			ret = ret
					&& nombreAgenciaFlete.equals(fleteSeleccionado
							.getNombreAgenciaFlete());
		} else if (nombreAgenciaFlete == null
				&& fleteSeleccionado.getNombreAgenciaFlete() == null) {
			ret = ret && true;
		} else {
			ret = ret && false;
		}

		if (codigoCoto != null && fleteSeleccionado.getCodigoCoto() != null) {
			ret = ret && codigoCoto.equals(fleteSeleccionado.getCodigoCoto());
		} else if (codigoCoto == null
				&& fleteSeleccionado.getCodigoCoto() == null) {
			ret = ret && true;
		} else {
			ret = ret && false;
		}

		return ret;
	}

}
