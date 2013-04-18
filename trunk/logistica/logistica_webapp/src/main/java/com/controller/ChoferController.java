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
import logistica.model.Chofer;
import logistica.query.ChoferQuery;
import logistica.type.TipoInscripcionEnum;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.ChoferBuilder;
import com.controller.common.DireccionBean;
import com.util.JSFUtil;
import com.view.ChoferView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ChoferController extends PaginableController<Chofer> {

	private Logger log = Logger.getLogger(ChoferController.class);
	private BaseModelDAO<Chofer> dao;
	private Chofer chofer;
	private ChoferQuery choferQuery;
	private List<TipoInscripcionEnum> tipoInscripcionEnumList;
	private StreamedContent imagen;

	@ManagedProperty("#{choferView}")
	private ChoferView choferView;

	@ManagedProperty("#{direccionBean}")
	private DireccionBean direccionBean;

	@ManagedProperty("#{choferBuilder}")
	private ChoferBuilder choferBuilder;

	@SuppressWarnings("unchecked")
	public ChoferController() {
		try {
			dao = (BaseModelDAO<Chofer>) (BaseModelDAO<Chofer>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("choferDAO");
			choferQuery = new ChoferQuery();
			tipoInscripcionEnumList = Arrays.asList(TipoInscripcionEnum
					.values());
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
			choferView = new ChoferView();
			chofer = (Chofer) lazyDM.getRowData();
			choferView = choferBuilder.toView(chofer);
			direccionBean.setDireccionView(choferView.getDireccionView());

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
			choferView.setDireccionView(direccionBean.getDireccionView());
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
		chofer = new Chofer();
		choferView = new ChoferView();
		direccionBean.clear();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<Chofer>() {

			@Override
			public List<Chofer> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("nombre", choferQuery.getNombre());
				filtro.put("dni", choferQuery.getDni());
				return dao.getList(first, pageSize, "nombre", true, filtro,
						false);
			}
		};

		Map<String, Object> filtro = new HashMap<String, Object>();
		filtro.put("nombre", choferQuery.getNombre());
		filtro.put("dni", choferQuery.getDni());
		lazyDM.setRowCount(dao.count(filtro, false).intValue());
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
		choferView.setImagen(event.getFile());

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
		choferView.setImagen(event.getFile());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
