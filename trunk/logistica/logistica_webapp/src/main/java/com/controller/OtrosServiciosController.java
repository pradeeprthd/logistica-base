package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Chofer;
import logistica.model.Movil;
import logistica.model.OtrosServicios;
import logistica.query.ChoferQuery;
import logistica.query.MovilQuery;
import logistica.query.OtrosServiciosQuery;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.OtrosServiciosBuilder;
import com.util.JSFUtil;
import com.view.OtrosServiciosView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class OtrosServiciosController extends
		PaginableController<OtrosServicios> {
	private Logger log = Logger.getLogger(OtrosServiciosController.class);
	private BaseModelDAO<OtrosServicios> dao;
	private BaseModelDAO<Movil> daoMovil;
	private BaseModelDAO<Chofer> daoChofer;
	private OtrosServicios otrosServicios;
	private OtrosServiciosQuery otrosServiciosQuery;

	@ManagedProperty("#{otrosServiciosView}")
	private OtrosServiciosView otrosServiciosView;

	@ManagedProperty("#{otrosServiciosBuilder}")
	private OtrosServiciosBuilder otrosServiciosBuilder;

	@SuppressWarnings("unchecked")
	public OtrosServiciosController() {
		try {
			dao = (BaseModelDAO<OtrosServicios>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("otrosServiciosDAO");
			daoMovil = (BaseModelDAO<Movil>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilDAO");
			daoChofer = (BaseModelDAO<Chofer>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("choferDAO");
			otrosServiciosQuery = new OtrosServiciosQuery();
			addEdit = false;
		} catch (Throwable e) {
			log.error("Error al inicializar la clase OtrosServiciosController",
					e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public OtrosServiciosView getOtrosServiciosView() {
		return otrosServiciosView;
	}

	public void setOtrosServiciosView(OtrosServiciosView otrosServiciosView) {
		this.otrosServiciosView = otrosServiciosView;
	}

	public OtrosServiciosBuilder getOtrosServiciosBuilder() {
		return otrosServiciosBuilder;
	}

	public void setOtrosServiciosBuilder(
			OtrosServiciosBuilder otrosServiciosBuilder) {
		this.otrosServiciosBuilder = otrosServiciosBuilder;
	}

	public OtrosServiciosQuery getOtrosServiciosQuery() {
		return otrosServiciosQuery;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			otrosServicios = (OtrosServicios) lazyDM.getRowData();
			otrosServiciosView = otrosServiciosBuilder.toView(otrosServicios);
			addEdit = true;
		} catch (Throwable e) {
			log.error("Error al editar", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public void delete(ActionEvent event) {
		try {
			otrosServicios = (OtrosServicios) lazyDM.getRowData();
			dao.delete(otrosServicios);
			loadList();
		} catch (Throwable e) {
			log.error("Error al eliminar", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public void add(ActionEvent event) {
		addEdit = true;
		clear();
	}

	public void save(ActionEvent event) {
		try {
			otrosServicios = otrosServiciosBuilder.toDomain(otrosServiciosView);
			if (otrosServicios.getID() != null) {
				dao.edit(otrosServicios);
				addEdit = false;
			} else {
				otrosServicios.setFecha(new Date());
				dao.save(otrosServicios);
			}
			clear();
			JSFUtil.saveMessage("Elemento guardado con exito",
					FacesMessage.SEVERITY_INFO);
			if (!addEdit) {
				loadList();
			}
		} catch (DataIntegrityViolationException e) {
			JSFUtil.saveMessage("Error al guardar: Integridad de los datos",
					FacesMessage.SEVERITY_ERROR);
		} catch (Throwable e) {
			log.error("Error al guardar", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public void cancel(ActionEvent event) {
		addEdit = false;
		lazyDM = null;
	}

	public void clear() {
		otrosServicios = new OtrosServicios();
		otrosServiciosView = new OtrosServiciosView();
		otrosServicios.setHorarioEntrada(new Date());
	}

	private void loadList() {

		lazyDM = new LazyDataModel<OtrosServicios>() {

			@Override
			public List<OtrosServicios> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("movil.patente", otrosServiciosQuery.getPatente());
				filtro.put("chofer.nombre",
						otrosServiciosQuery.getNombreChofer());
				filtro.put("horarioEntrada", otrosServiciosQuery.getFecha());
				if (otrosServiciosQuery.getNumeroMovil() != null
						&& otrosServiciosQuery.getNumeroMovil() != 0) {
					filtro.put("movil.numeroMovil",
							otrosServiciosQuery.getNumeroMovil());
				}
				return dao.getList(first, pageSize, "movil.numeroMovil", true,
						filtro, true);
			}

		};
		Map<String, Object> filtro = new HashMap<String, Object>();
		filtro.put("movil.patente", otrosServiciosQuery.getPatente());
		filtro.put("chofer.nombre", otrosServiciosQuery.getNombreChofer());
		filtro.put("horarioEntrada", otrosServiciosQuery.getFecha());
		if (otrosServiciosQuery.getNumeroMovil() != null
				&& otrosServiciosQuery.getNumeroMovil() != 0) {
			filtro.put("movil.numeroMovil",
					otrosServiciosQuery.getNumeroMovil());
		}
		lazyDM.setRowCount(dao.count(filtro,true).intValue());
	}

	public boolean isMovilSelected() {
		return (otrosServiciosView.getMovil() != null);
	}

	public void deseleccionarMovil(ActionEvent event) {
		otrosServiciosView.setMovil(null);
	}

	public List<Movil> completeMovil(String query) {
		List<Movil> movilList = null;
		try {
			Long numeroMovil = null;
			try {
				if (query != null) {
					numeroMovil = Long.parseLong(query);
				}
			} catch (Exception e) {
			}
			MovilQuery movilQuery = new MovilQuery(null, query, numeroMovil);
			movilList = daoMovil.getList(movilQuery);

		} catch (Throwable e) {
			log.error("Error en le metodo completeMovil: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
		return movilList;
	}

	public void handleMovilSelect(SelectEvent event) {
		System.out.println("se eligio un movil");
	}

	public boolean isChoferSelected() {
		return (otrosServiciosView.getChofer() != null);
	}

	public void deseleccionarChofer(ActionEvent event) {
		otrosServiciosView.setChofer(null);
	}

	public List<Chofer> completeChofer(String query) {
		List<Chofer> choferList = null;
		try {
			ChoferQuery choferQuery = new ChoferQuery(null, query);
			choferList = daoChofer.getList(choferQuery);

		} catch (Throwable e) {
			log.error("Error en le metodo completeChofer: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
		return choferList;
	}

	public void handleChoferSelect(SelectEvent event) {
		System.out.println("se eligio un chofer");
	}
}
