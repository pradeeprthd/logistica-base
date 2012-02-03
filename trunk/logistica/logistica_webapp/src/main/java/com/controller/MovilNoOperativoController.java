package com.controller;

import java.util.Arrays;
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
import logistica.model.MovilNoOperativo;
import logistica.query.MovilNoOperativoQuery;
import logistica.query.MovilQuery;
import logistica.type.EstadoMovilEnum;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.MovilNoOperativoBuilder;
import com.util.JSFUtil;
import com.view.MovilNoOperativoView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MovilNoOperativoController extends
		PaginableController<MovilNoOperativo> {
	private Logger log = Logger.getLogger(MovilNoOperativoController.class);
	private BaseModelDAO<MovilNoOperativo> dao;
	private BaseModelDAO<Movil> daoMovil;
	private MovilNoOperativo movilNoOperativo;
	private MovilNoOperativoQuery movilNoOperativoQuery;
	private List<EstadoMovilEnum> estadoMovilEnumList;

	@ManagedProperty("#{movilNoOperativoView}")
	private MovilNoOperativoView movilNoOperativoView;

	@ManagedProperty("#{movilNoOperativoBuilder}")
	private MovilNoOperativoBuilder movilNoOperativoBuilder;

	@SuppressWarnings("unchecked")
	public MovilNoOperativoController() {
		try {
			dao = (BaseModelDAO<MovilNoOperativo>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilNoOperativoDAO");
			daoMovil = (BaseModelDAO<Movil>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilDAO");
			movilNoOperativoQuery = new MovilNoOperativoQuery();
			estadoMovilEnumList = Arrays.asList(EstadoMovilEnum.values());
			addEdit = false;
		} catch (Throwable e) {
			log.error(
					"Error al inicializar la clase MovilNoOperativoController",
					e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public MovilNoOperativoView getMovilNoOperativoView() {
		return movilNoOperativoView;
	}

	public void setMovilNoOperativoView(
			MovilNoOperativoView movilNoOperativoView) {
		this.movilNoOperativoView = movilNoOperativoView;
	}

	public MovilNoOperativoBuilder getMovilNoOperativoBuilder() {
		return movilNoOperativoBuilder;
	}

	public void setMovilNoOperativoBuilder(
			MovilNoOperativoBuilder movilNoOperativoBuilder) {
		this.movilNoOperativoBuilder = movilNoOperativoBuilder;
	}

	public MovilNoOperativoQuery getMovilNoOperativoQuery() {
		return movilNoOperativoQuery;
	}

	public List<EstadoMovilEnum> getEstadoMovilEnumList() {
		return estadoMovilEnumList;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			movilNoOperativo = (MovilNoOperativo) lazyDM.getRowData();
			movilNoOperativoView = movilNoOperativoBuilder
					.toView(movilNoOperativo);
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
			movilNoOperativo = (MovilNoOperativo) lazyDM.getRowData();
			dao.delete(movilNoOperativo);
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
			movilNoOperativo = movilNoOperativoBuilder
					.toDomain(movilNoOperativoView);
			if (movilNoOperativo.getID() != null) {
				dao.edit(movilNoOperativo);
				addEdit = false;
			} else {
				dao.save(movilNoOperativo);
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
		movilNoOperativo = new MovilNoOperativo();
		movilNoOperativoView = new MovilNoOperativoView();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<MovilNoOperativo>() {

			@Override
			public List<MovilNoOperativo> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("movil.patente", movilNoOperativoQuery.getPatente());
				if (movilNoOperativoQuery.getNumeroMovil() != null
						&& movilNoOperativoQuery.getNumeroMovil() != 0) {
					filtro.put("movil.numeroMovil",
							movilNoOperativoQuery.getNumeroMovil());
				}
				return dao.getList(first, pageSize, "movil.numeroMovil", true,
						filtro);
			}

		};
		Map<String, Object> filtro = new HashMap<String, Object>();
		filtro.put("movil.patente", movilNoOperativoQuery.getPatente());
		if (movilNoOperativoQuery.getNumeroMovil() != null
				&& movilNoOperativoQuery.getNumeroMovil() != 0) {
			filtro.put("movil.numeroMovil",
					movilNoOperativoQuery.getNumeroMovil());
		}
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}

	public boolean isMovilSelected() {
		return (movilNoOperativoView.getMovil() != null);
	}

	public void deseleccionarMovil(ActionEvent event) {
		movilNoOperativoView.setMovil(null);
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
}
