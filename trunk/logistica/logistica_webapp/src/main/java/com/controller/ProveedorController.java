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
import logistica.model.Proveedor;
import logistica.query.ProveedorQuery;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.builder.ProveedorBuilder;
import com.util.JSFUtil;
import com.view.ProveedorView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ProveedorController extends PaginableController<Proveedor> {

	private Logger log = Logger.getLogger(ProveedorController.class);
	private ClassPathXmlApplicationContext ctx;
	private BaseModelDAO<Proveedor> dao;
	private Proveedor proveedor;
	private ProveedorQuery proveedorQuery;

	@ManagedProperty("#{proveedorView}")
	private ProveedorView proveedorView;

	@ManagedProperty("#{proveedorBuilder}")
	private ProveedorBuilder proveedorBuilder;

	@SuppressWarnings("unchecked")
	public ProveedorController() {
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			dao = (BaseModelDAO<Proveedor>) ctx.getBean("proveedorDAO");
			proveedorQuery = new ProveedorQuery();
			addEdit = false;
		} catch (Throwable e) {
			log.error("Error al inicializar la clase ProveedorController", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public ProveedorView getProveedorView() {
		return proveedorView;
	}

	public void setProveedorView(ProveedorView proveedorView) {
		this.proveedorView = proveedorView;
	}

	public ProveedorBuilder getProveedorBuilder() {
		return proveedorBuilder;
	}

	public void setProveedorBuilder(ProveedorBuilder proveedorBuilder) {
		this.proveedorBuilder = proveedorBuilder;
	}

	public ProveedorQuery getProveedorQuery() {
		return proveedorQuery;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			proveedor = (Proveedor) lazyDM.getRowData();
			proveedorView = proveedorBuilder.toView(proveedor);
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
			proveedor = (Proveedor) lazyDM.getRowData();
			dao.delete(proveedor);
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
			proveedor = proveedorBuilder.toDomain(proveedorView);
			if (proveedor.getID() != null) {
				dao.edit(proveedor);
				addEdit = false;
			} else {
				dao.save(proveedor);
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
		proveedor = new Proveedor();
		proveedorView = new ProveedorView();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<Proveedor>() {

			@Override
			public List<Proveedor> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("nombre", proveedorQuery.getNombre());
				return dao.getList(first, pageSize, "nombre", true, filtro);
			}
		};

		Map<String, Object> filtro = new HashMap<String, Object>();
		filtro.put("nombre", proveedorQuery.getNombre());
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}
}
