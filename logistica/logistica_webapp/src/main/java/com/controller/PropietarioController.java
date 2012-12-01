package com.controller;

import java.io.IOException;
import java.util.Arrays;
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
import logistica.model.Propietario;
import logistica.query.PropietarioQuery;
import logistica.type.TipoInscripcionEnum;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.PropietarioBuilder;
import com.controller.common.DireccionBean;
import com.util.JSFUtil;
import com.view.PropietarioView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class PropietarioController extends PaginableController<Propietario> {

	private Logger log = Logger.getLogger(PropietarioController.class);
	private BaseModelDAO<Propietario> dao;
	private Propietario propietario;
	private PropietarioQuery propietarioQuery;
	private List<TipoInscripcionEnum> tipoInscripcionEnumList;
	private StreamedContent imagen;

	@ManagedProperty("#{propietarioView}")
	private PropietarioView propietarioView;

	@ManagedProperty("#{direccionBean}")
	private DireccionBean direccionBean;

	@ManagedProperty("#{propietarioBuilder}")
	private PropietarioBuilder propietarioBuilder;

	@SuppressWarnings("unchecked")
	public PropietarioController() {
		try {
			dao = (BaseModelDAO<Propietario>) (BaseModelDAO<Propietario>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("propietarioDAO");
			propietarioQuery = new PropietarioQuery();
			tipoInscripcionEnumList = Arrays.asList(TipoInscripcionEnum
					.values());
			addEdit = false;
		} catch (Throwable e) {
			log.error("Error al inicializar la clase PropietarioController", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public PropietarioView getPropietarioView() {
		return propietarioView;
	}

	public void setPropietarioView(PropietarioView propietarioView) {
		this.propietarioView = propietarioView;
	}

	public PropietarioBuilder getPropietarioBuilder() {
		return propietarioBuilder;
	}

	public void setPropietarioBuilder(PropietarioBuilder propietarioBuilder) {
		this.propietarioBuilder = propietarioBuilder;
	}

	public PropietarioQuery getPropietarioQuery() {
		return propietarioQuery;
	}

	public DireccionBean getDireccionBean() {
		return direccionBean;
	}

	public void setDireccionBean(DireccionBean direccionBean) {
		this.direccionBean = direccionBean;
	}

	public List<TipoInscripcionEnum> getTipoInscripcionEnumList() {
		return tipoInscripcionEnumList;
	}

	public StreamedContent getImagen() {
		return imagen;
	}

	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			propietario = (Propietario) lazyDM.getRowData();
			propietarioView = propietarioBuilder.toView(propietario);
			direccionBean.setDireccionView(propietarioView.getDireccionView());

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
			propietario = (Propietario) lazyDM.getRowData();
			dao.delete(propietario);
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
			propietarioView.setDireccionView(direccionBean.getDireccionView());
			propietario = propietarioBuilder.toDomain(propietarioView);
			if (propietario.getID() != null) {
				dao.edit(propietario);
				addEdit = false;
			} else {
				dao.save(propietario);
			}
			clear();
			JSFUtil.saveMessage("Elemento guardado con exito",
					FacesMessage.SEVERITY_INFO);
			if (!addEdit) {
				loadList();
			}
		} catch (DataIntegrityViolationException e) {
			JSFUtil.saveMessage(
					"Error al guardar: El nombre debe ser unico en el sistema.",
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
		propietario = new Propietario();
		propietarioView = new PropietarioView();
		direccionBean.clear();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<Propietario>() {

			@Override
			public List<Propietario> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("nombre", propietarioQuery.getNombre());
				return dao.getList(first, pageSize, "nombre", true, filtro);
			}
		};

		Map<String, Object> filtro = new HashMap<String, Object>();
		filtro.put("nombre", propietarioQuery.getNombre());
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}

	@PostConstruct
	public void init() {
		direccionBean.init();
	}

	public void handleFileUpload(FileUploadEvent event) {
		/*
		 * FacesMessage msg = new FacesMessage("Mensaje", event.getFile()
		 * .getFileName() + " cargado correctamente.");
		 */

		JSFUtil.saveMessage(event.getFile().getFileName()
				+ " cargado correctamente.", FacesMessage.SEVERITY_INFO);
		propietarioView.setImagen(event.getFile());

		try {
			imagen = new DefaultStreamedContent(event.getFile()
					.getInputstream(), "image/jpeg");
		} catch (IOException e) {
		}
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void upload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Mensaje", event.getFile()
				.getFileName() + " cargado correctamente.");
		propietarioView.setImagen(event.getFile());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
