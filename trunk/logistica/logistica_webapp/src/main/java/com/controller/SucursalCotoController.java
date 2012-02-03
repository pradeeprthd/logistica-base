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
import logistica.model.SucursalCoto;
import logistica.query.SucursalCotoQuery;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.SucursalCotoBuilder;
import com.controller.common.DireccionBean;
import com.util.JSFUtil;
import com.view.SucursalCotoView;

@ManagedBean
@ViewScoped
@SuppressWarnings({ "serial", "restriction" })
public class SucursalCotoController extends PaginableController<SucursalCoto> {
	private Logger log = Logger.getLogger(SucursalCotoController.class);
	private BaseModelDAO<SucursalCoto> dao;
	private SucursalCoto sucursalCoto;
	private SucursalCotoQuery sucursalCotoQuery;

	@ManagedProperty("#{sucursalCotoView}")
	private SucursalCotoView sucursalCotoView;

	@ManagedProperty("#{direccionBean}")
	private DireccionBean direccionBean;

	@ManagedProperty("#{sucursalCotoBuilder}")
	private SucursalCotoBuilder sucursalCotoBuilder;

	@SuppressWarnings("unchecked")
	public SucursalCotoController() {
		try {
			dao = (BaseModelDAO<SucursalCoto>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("sucursalCotoDAO");
			sucursalCotoQuery = new SucursalCotoQuery();
			addEdit = false;
		} catch (Throwable e) {
			log.error("Error al inicializar la clase SucursalCotoController", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public SucursalCotoView getSucursalCotoView() {
		return sucursalCotoView;
	}

	public void setSucursalCotoView(SucursalCotoView sucursalCotoView) {
		this.sucursalCotoView = sucursalCotoView;
	}

	public SucursalCotoBuilder getSucursalCotoBuilder() {
		return sucursalCotoBuilder;
	}

	public void setSucursalCotoBuilder(SucursalCotoBuilder sucursalCotoBuilder) {
		this.sucursalCotoBuilder = sucursalCotoBuilder;
	}

	public SucursalCotoQuery getSucursalCotoQuery() {
		return sucursalCotoQuery;
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
			sucursalCoto = (SucursalCoto) lazyDM.getRowData();
			sucursalCotoView = sucursalCotoBuilder.toView(sucursalCoto);
			direccionBean.setDireccionView(sucursalCotoView.getDireccionView());
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
			sucursalCoto = (SucursalCoto) lazyDM.getRowData();
			dao.delete(sucursalCoto);
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
			sucursalCotoView.setDireccionView(direccionBean.getDireccionView());
			sucursalCoto = sucursalCotoBuilder.toDomain(sucursalCotoView);
			if (sucursalCoto.getID() != null) {
				dao.edit(sucursalCoto);
				addEdit = false;
			} else {
				dao.save(sucursalCoto);
			}
			clear();
			JSFUtil.saveMessage("Elemento guardado con exito",
					FacesMessage.SEVERITY_INFO);
			if (!addEdit) {
				loadList();
			}
		} catch (DataIntegrityViolationException e) {
			JSFUtil.saveMessage(
					"Error al guardar: El n�mero de sucursal debe ser �nico en el sistema.",
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
		sucursalCoto = new SucursalCoto();
		sucursalCotoView = new SucursalCotoView();
		direccionBean.clear();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<SucursalCoto>() {

			@Override
			public List<SucursalCoto> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("nombre", sucursalCotoQuery.getNombre());
				if (sucursalCotoQuery.getNumeroSucursal() != null
						&& sucursalCotoQuery.getNumeroSucursal() != 0) {
					filtro.put("numeroSucursal",
							sucursalCotoQuery.getNumeroSucursal());
				}
				return dao.getList(first, pageSize, "numeroSucursal", true,
						filtro);
			}

		};
		Map<String, Object> filtro = new HashMap<String, Object>();
		filtro.put("nombre", sucursalCotoQuery.getNombre());
		if (sucursalCotoQuery.getNumeroSucursal() != null
				&& sucursalCotoQuery.getNumeroSucursal() != 0) {
			filtro.put("numeroSucursal", sucursalCotoQuery.getNumeroSucursal());
		}
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}

	@PostConstruct
	public void init() {
		direccionBean.init();
	}
}
