package com.controller;

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
import logistica.query.ChoferQuery;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.builder.ChoferBuilder;
import com.util.JSFUtil;
import com.view.ChoferView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ChoferController extends PaginableController<Chofer> {

	private Logger log = Logger.getLogger(ChoferController.class);
	private ClassPathXmlApplicationContext ctx;
	private BaseModelDAO<Chofer> dao;
	private Chofer chofer;
	private ChoferQuery choferQuery;

	@ManagedProperty("#{choferView}")
	private ChoferView choferView;

	@ManagedProperty("#{choferBuilder}")
	private ChoferBuilder choferBuilder;

	@SuppressWarnings("unchecked")
	public ChoferController() {
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			dao = (BaseModelDAO<Chofer>) ctx.getBean("choferDAO");
			choferQuery = new ChoferQuery();
			addEdit = false;
		} catch (Throwable e) {
			log.error("Error al inicializar la clase ChoferController", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public ChoferView getChoferView() {
		return choferView;
	}

	public void setChoferView(ChoferView choferView) {
		this.choferView = choferView;
	}

	public ChoferBuilder getChoferBuilder() {
		return choferBuilder;
	}

	public void setChoferBuilder(ChoferBuilder choferBuilder) {
		this.choferBuilder = choferBuilder;
	}

	public ChoferQuery getChoferQuery() {
		return choferQuery;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			chofer = (Chofer) lazyDM.getRowData();
			choferView = choferBuilder.toView(chofer);
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
			chofer = (Chofer) lazyDM.getRowData();
			dao.delete(chofer);
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
			chofer = choferBuilder.toDomain(choferView);
			if (chofer.getID() != null) {
				dao.edit(chofer);
				addEdit = false;
			} else {
				dao.save(chofer);
			}
			clear();
			JSFUtil.saveMessage("Elemento guardado con exito",
					FacesMessage.SEVERITY_INFO);
			if (!addEdit) {
				loadList();
			}
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
		chofer = new Chofer();
		choferView = new ChoferView();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<Chofer>() {

			@Override
			public List<Chofer> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				Map<String, String> filtro = new HashMap<String, String>();
				filtro.put("nombre", choferQuery.getNombre());
				return dao.getList(first, pageSize, "nombre", true, filtro);
			}
		};

		Map<String, String> filtro = new HashMap<String, String>();
		filtro.put("nombre", choferQuery.getNombre());
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}

}
