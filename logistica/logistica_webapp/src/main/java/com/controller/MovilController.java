package com.controller;

import java.util.Arrays;
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
import logistica.model.Movil;
import logistica.query.MovilQuery;
import logistica.type.AsignacionMovilEnum;
import logistica.type.EstadoEnum;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.MovilBuilder;
import com.util.JSFUtil;
import com.view.MovilView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MovilController extends PaginableController<Movil> {
	private Logger log = Logger.getLogger(MovilController.class);
	private BaseModelDAO<Movil> dao;
	private Movil movil;
	private MovilQuery movilQuery;
	private List<AsignacionMovilEnum> asignacionMovilEnumList;
	private List<EstadoEnum> estadoEnumList;

	@ManagedProperty("#{movilView}")
	private MovilView movilView;

	@ManagedProperty("#{movilBuilder}")
	private MovilBuilder movilBuilder;

	@SuppressWarnings("unchecked")
	public MovilController() {
		try {
			dao = (BaseModelDAO<Movil>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilDAO");
			movilQuery = new MovilQuery();
			asignacionMovilEnumList = Arrays.asList(AsignacionMovilEnum
					.values());
			estadoEnumList = Arrays.asList(EstadoEnum.values());
			addEdit = false;
		} catch (Throwable e) {
			log.error("Error al inicializar la clase MovilController", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public MovilView getMovilView() {
		return movilView;
	}

	public void setMovilView(MovilView movilView) {
		this.movilView = movilView;
	}

	public MovilBuilder getMovilBuilder() {
		return movilBuilder;
	}

	public void setMovilBuilder(MovilBuilder movilBuilder) {
		this.movilBuilder = movilBuilder;
	}

	public Movil getMovil() {
		return movil;
	}

	public MovilQuery getMovilQuery() {
		return movilQuery;
	}

	public List<AsignacionMovilEnum> getAsignacionMovilEnumList() {
		return asignacionMovilEnumList;
	}

	public List<EstadoEnum> getEstadoEnumList() {
		return estadoEnumList;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			movil = (Movil) lazyDM.getRowData();
			movilView = movilBuilder.toView(movil);
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
			movil = (Movil) lazyDM.getRowData();
			movil.setEstado(EstadoEnum.INACTIVO);
			movil.setFechaEgreso(new Date());
			dao.edit(movil);
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
			movil = movilBuilder.toDomain(movilView);
			if (movil.getID() != null) {
				if (movil.getEstado() == EstadoEnum.INACTIVO) {
					movil.setFechaEgreso(new Date());
				} else {
					movil.setFechaEgreso(null);
				}
				dao.edit(movil);
				addEdit = false;
			} else {
				movil.setFechaIngreso(new Date());
				dao.save(movil);
			}
			clear();
			JSFUtil.saveMessage("Elemento guardado con exito",
					FacesMessage.SEVERITY_INFO);
			if (!addEdit) {
				loadList();
			}
		} catch (DataIntegrityViolationException e) {
			JSFUtil.saveMessage(
					"Error al guardar: La patente y el numero de movil deben ser unicos en el sistema.",
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
		movil = new Movil();
		movilView = new MovilView();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<Movil>() {

			@Override
			public List<Movil> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("patente", movilQuery.getPatente());
				if (movilQuery.getNumeroMovil() != null
						&& movilQuery.getNumeroMovil() != 0) {
					filtro.put("numeroMovil", movilQuery.getNumeroMovil());
				}
				return dao
						.getList(first, pageSize, "numeroMovil", true, filtro);
			}

		};
		Map<String, Object> filtro = new HashMap<String, Object>();
		filtro.put("patente", movilQuery.getPatente());
		if (movilQuery.getNumeroMovil() != null
				&& movilQuery.getNumeroMovil() != 0) {
			filtro.put("numeroMovil", movilQuery.getNumeroMovil());
		}
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}
}
