package com.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import logistica.common.dao.BaseModelDAO;
import logistica.model.DetalleAsignacion;
import logistica.model.DetalleSucursal;
import logistica.model.DiagramacionDiaria;
import logistica.model.Movil;
import logistica.model.MovilNoOperativo;
import logistica.model.OtrosServicios;
import logistica.query.DiagramacionDiariaQuery;
import logistica.query.MovilDisponibleQuery;
import logistica.query.MovilNoOperativoQuery;
import logistica.query.OtrosServiciosQuery;

import org.apache.log4j.Logger;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
@SuppressWarnings({ "serial" })
public class MovilDisponibleController extends PaginableController<Movil> {

	private Logger log = Logger.getLogger(MovilDisponibleController.class);
	private BaseModelDAO<MovilNoOperativo> daoMovilNoOperativo;
	private BaseModelDAO<OtrosServicios> daoOtrosServicios;
	private BaseModelDAO<DiagramacionDiaria> daoDiagramacionDiaria;
	private BaseModelDAO<Movil> daoMovil;
	private MovilDisponibleQuery movilDisponibleQuery;

	private List<MovilNoOperativo> movilNoOperativoList;
	private List<OtrosServicios> otrosServiciosList;
	private DiagramacionDiaria diagramacionDiaria;

	private List<Movil> movilDisponibleList;

	@SuppressWarnings("unchecked")
	public MovilDisponibleController() {
		try {
			daoDiagramacionDiaria = (BaseModelDAO<DiagramacionDiaria>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("diagramacionDiariaDAO");
			daoMovilNoOperativo = (BaseModelDAO<MovilNoOperativo>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilNoOperativoDAO");
			daoOtrosServicios = (BaseModelDAO<OtrosServicios>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("otrosServiciosDAO");
			daoMovil = (BaseModelDAO<Movil>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilDAO");

			movilDisponibleQuery = new MovilDisponibleQuery();

			addEdit = false;
		} catch (Throwable e) {
			log.error(
					"Error al inicializar la clase MovilDisponibleController",
					e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	@Override
	public void query(ActionEvent event) {
		if (movilDisponibleQuery.getFecha() == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Debe seleccionar una fecha", ""));
		} else {
			movilNoOperativoList = daoMovilNoOperativo
					.getList(new MovilNoOperativoQuery(null, null, null,
							movilDisponibleQuery.getFecha()));
			otrosServiciosList = daoOtrosServicios
					.getList(new OtrosServiciosQuery(null, null, null, null,
							movilDisponibleQuery.getFecha()));
			diagramacionDiaria = daoDiagramacionDiaria
					.get(new DiagramacionDiariaQuery(null, movilDisponibleQuery
							.getFecha()));
			if (diagramacionDiaria != null) {
				diagramacionDiaria = daoDiagramacionDiaria
						.findFULL(diagramacionDiaria.getID());
			}

			// obtengo todos los móviles
			movilDisponibleList = daoMovil.getList();

			// le voy sacando los moviles no disponibles
			for (MovilNoOperativo mno : movilNoOperativoList) {
				movilDisponibleList.remove(mno.getMovil());
			}

			for (OtrosServicios os : otrosServiciosList) {
				movilDisponibleList.remove(os.getMovil());
			}

			if (diagramacionDiaria != null) {
				for (DetalleSucursal detalleSucursal : diagramacionDiaria
						.getDetalleSucursalList()) {
					for (DetalleAsignacion detalleAsignacion : detalleSucursal
							.getDetalleAsignacionList()) {
						movilDisponibleList
								.remove(detalleAsignacion.getMovil());
					}
				}
			}
		}
	}

	@Override
	public void edit(ActionEvent event) {

	}

	@Override
	public void delete(ActionEvent event) {

	}

	@Override
	public void add(ActionEvent event) {

	}

	@Override
	public void save(ActionEvent event) {

	}

	@Override
	public void cancel(ActionEvent event) {

	}

	@Override
	public void clear() {

	}

	public List<Movil> getMovilDisponibleList() {
		return movilDisponibleList;
	}

	public void setMovilDisponibleList(List<Movil> movilDisponbleList) {
		this.movilDisponibleList = movilDisponbleList;
	}

	public MovilDisponibleQuery getMovilDisponibleQuery() {
		return movilDisponibleQuery;
	}
}
