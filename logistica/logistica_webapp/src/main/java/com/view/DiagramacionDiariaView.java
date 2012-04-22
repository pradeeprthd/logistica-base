package com.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DiagramacionDiariaView implements Serializable {

	private Long id;
	private Date fecha;
	private List<String> novedades;

	@NotNull(message = "Valor requerido")
	private List<DetalleSucursalView> detalleSucursalViewList;

	public DiagramacionDiariaView(Long id, Date fecha, List<String> novedades,
			List<DetalleSucursalView> detalleSucursalViewList) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.novedades = novedades;
		this.detalleSucursalViewList = detalleSucursalViewList;
	}

	public DiagramacionDiariaView() {
		this(null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<String> getNovedades() {
		return novedades;
	}

	public void setNovedades(List<String> novedades) {
		this.novedades = novedades;
	}

	public List<DetalleSucursalView> getDetalleSucursalViewList() {
		return detalleSucursalViewList;
	}

	public void setDetalleSucursalViewList(
			List<DetalleSucursalView> detalleSucursalViewList) {
		this.detalleSucursalViewList = detalleSucursalViewList;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new DiagramacionDiariaView(id, fecha, novedades,
				detalleSucursalViewList);
	}
}
