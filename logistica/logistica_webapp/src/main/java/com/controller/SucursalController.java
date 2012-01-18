package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Sucursal;
import logistica.query.SucursalQuery;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.builder.SucursalBuilder;
import com.controller.common.DireccionBean;
import com.util.JSFUtil;
import com.view.SucursalView;

@ManagedBean
@ViewScoped
@SuppressWarnings({ "serial", "restriction" })
public class SucursalController extends PaginableController<Sucursal> {
	private Logger log = Logger.getLogger(SucursalController.class);
	private ClassPathXmlApplicationContext ctx;
	private BaseModelDAO<Sucursal> dao;
	private Sucursal sucursal;
	private SucursalQuery sucursalQuery;

	@ManagedProperty("#{sucursalView}")
	private SucursalView sucursalView;

	@ManagedProperty("#{direccionBean}")
	private DireccionBean direccionBean;

	@ManagedProperty("#{sucursalBuilder}")
	private SucursalBuilder sucursalBuilder;

	@SuppressWarnings("unchecked")
	public SucursalController() {
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			dao = (BaseModelDAO<Sucursal>) ctx.getBean("sucursalDAO");
			sucursalQuery = new SucursalQuery();
			addEdit = false;
		} catch (Throwable e) {
			log.error("Error al inicializar la clase SucursalController", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public SucursalView getSucursalView() {
		return sucursalView;
	}

	public void setSucursalView(SucursalView sucursalView) {
		this.sucursalView = sucursalView;
	}

	public SucursalBuilder getSucursalBuilder() {
		return sucursalBuilder;
	}

	public void setSucursalBuilder(SucursalBuilder sucursalBuilder) {
		this.sucursalBuilder = sucursalBuilder;
	}

	public SucursalQuery getSucursalQuery() {
		return sucursalQuery;
	}

	public DireccionBean getDireccionBean() {
		return direccionBean;
	}

	public void setDireccionBean(DireccionBean direccionBean) {
		this.direccionBean = direccionBean;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			sucursal = (Sucursal) lazyDM.getRowData();
			sucursalView = sucursalBuilder.toView(sucursal);
			direccionBean.setDireccionView(sucursalView.getDireccionView());
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
			sucursal = (Sucursal) lazyDM.getRowData();
			dao.delete(sucursal);
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
			sucursalView.setDireccionView(direccionBean.getDireccionView());
			sucursal = sucursalBuilder.toDomain(sucursalView);
			if (sucursal.getID() != null) {
				dao.edit(sucursal);
				addEdit = false;
			} else {
				dao.save(sucursal);
			}
			clear();
			JSFUtil.saveMessage("Elemento guardado con exito",
					FacesMessage.SEVERITY_INFO);
			if (!addEdit) {
				loadList();
			}
		} catch (DataIntegrityViolationException e) {
			JSFUtil.saveMessage(
					"Error al guardar: El nombre y el número de sucursal deben ser únicos en el sistema.",
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
		sucursal = new Sucursal();
		sucursalView = new SucursalView();
		direccionBean.clear();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<Sucursal>() {

			@Override
			public List<Sucursal> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("nombre", sucursalQuery.getNombre());
				if (sucursalQuery.getNumeroSucursal() != null
						&& sucursalQuery.getNumeroSucursal() != 0) {
					filtro.put("numeroSucursal",
							sucursalQuery.getNumeroSucursal());
				}
				return dao.getList(first, pageSize, "nombre", true, filtro);
			}

		};
		Map<String, Object> filtro = new HashMap<String, Object>();
		filtro.put("nombre", sucursalQuery.getNombre());
		if (sucursalQuery.getNumeroSucursal() != null
				&& sucursalQuery.getNumeroSucursal() != 0) {
			filtro.put("numeroSucursal", sucursalQuery.getNumeroSucursal());
		}
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}

	@PostConstruct
	public void init() {
		direccionBean.init();
	}
}
