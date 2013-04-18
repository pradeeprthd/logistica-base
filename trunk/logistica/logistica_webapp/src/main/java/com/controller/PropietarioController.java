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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Autonomo;
import logistica.model.Movil;
import logistica.model.Propietario;
import logistica.query.MovilQuery;
import logistica.query.PropietarioQuery;
import logistica.type.TipoInscripcionEnum;

import org.apache.log4j.Logger;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.AutonomoBuilder;
import com.builder.MovilPropietarioDetalleBuilder;
import com.builder.PropietarioBuilder;
import com.controller.common.DireccionBean;
import com.util.JSFUtil;
import com.view.AutonomoView;
import com.view.MovilPropietarioDetalleView;
import com.view.PropietarioView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class PropietarioController extends PaginableController<Propietario> {

	private Logger log = Logger.getLogger(PropietarioController.class);
	private BaseModelDAO<Propietario> dao;
	private BaseModelDAO<Movil> daoMovil;
	private Propietario propietario;
	private PropietarioQuery propietarioQuery;
	private List<TipoInscripcionEnum> tipoInscripcionEnumList;
	private StreamedContent imagen;
	private DataModel<AutonomoView> autonomoDM;
	private DataModel<MovilPropietarioDetalleView> movilDM;
	private Autonomo autonomo;
	private MovilPropietarioDetalleView movilPropietarioDetalle;

	@ManagedProperty("#{propietarioView}")
	private PropietarioView propietarioView;

	@ManagedProperty("#{direccionBean}")
	private DireccionBean direccionBean;

	@ManagedProperty("#{propietarioBuilder}")
	private PropietarioBuilder propietarioBuilder;

	@ManagedProperty("#{autonomoBuilder}")
	private AutonomoBuilder autonomoBuilder;

	@ManagedProperty("#{movilPropietarioDetalleBuilder}")
	private MovilPropietarioDetalleBuilder movilPropietarioDetalleBuilder;

	@SuppressWarnings("unchecked")
	public PropietarioController() {
		try {
			dao = (BaseModelDAO<Propietario>) (BaseModelDAO<Propietario>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("propietarioDAO");
			daoMovil = (BaseModelDAO<Movil>) (BaseModelDAO<Movil>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilDAO");
			propietarioQuery = new PropietarioQuery();
			tipoInscripcionEnumList = Arrays.asList(TipoInscripcionEnum
					.values());
			autonomoDM = new ListDataModel<AutonomoView>();
			autonomo = new Autonomo();
			movilPropietarioDetalle = new MovilPropietarioDetalleView();
			movilDM = new ListDataModel<MovilPropietarioDetalleView>();
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

	public Autonomo getAutonomo() {
		return autonomo;
	}

	public void setAutonomo(Autonomo autonomo) {
		this.autonomo = autonomo;
	}

	public AutonomoBuilder getAutonomoBuilder() {
		return autonomoBuilder;
	}

	public void setAutonomoBuilder(AutonomoBuilder autonomoBuilder) {
		this.autonomoBuilder = autonomoBuilder;
	}

	public MovilPropietarioDetalleView getMovilPropietarioDetalle() {
		return movilPropietarioDetalle;
	}

	public void setMovilPropietarioDetalle(
			MovilPropietarioDetalleView movilPropietarioDetalle) {
		this.movilPropietarioDetalle = movilPropietarioDetalle;
	}

	public DataModel<MovilPropietarioDetalleView> getMovilDM() {
		return movilDM;
	}

	public MovilPropietarioDetalleBuilder getMovilPropietarioDetalleBuilder() {
		return movilPropietarioDetalleBuilder;
	}

	public void setMovilPropietarioDetalleBuilder(
			MovilPropietarioDetalleBuilder movilPropietarioDetalleBuilder) {
		this.movilPropietarioDetalleBuilder = movilPropietarioDetalleBuilder;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			propietario = (Propietario) lazyDM.getRowData();
			propietario = dao.findFULL(propietario.getID());
			autonomoDM = new ListDataModel<AutonomoView>(
					autonomoBuilder.toView(propietario.getAutonomoList()));
			movilDM = new ListDataModel<MovilPropietarioDetalleView>(
					movilPropietarioDetalleBuilder.toView(propietario
							.getMovilPropietarioDetalleList()));
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
		autonomo = new Autonomo();
		movilPropietarioDetalle = new MovilPropietarioDetalleView();
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
				filtro.put("dni", propietarioQuery.getDni());
				filtro.put("cuit", propietarioQuery.getCuit());
				return dao.getList(first, pageSize, "nombre", true, filtro,
						false);
			}
		};

		Map<String, Object> filtro = new HashMap<String, Object>();
		filtro.put("nombre", propietarioQuery.getNombre());
		filtro.put("dni", propietarioQuery.getDni());
		filtro.put("cuit", propietarioQuery.getCuit());
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

	public DataModel<AutonomoView> getAutonomoDM() {
		return autonomoDM;
	}

	public void addAutonomo(ActionEvent event) {
		propietarioView.getAutonomoViewList().add(
				(AutonomoView) autonomoBuilder.toView(autonomo));
		autonomoDM = new ListDataModel<AutonomoView>(
				propietarioView.getAutonomoViewList());
	}

	public void deleteAutonomo(ActionEvent event) {
		AutonomoView detalle = autonomoDM.getRowData();
		propietarioView.getAutonomoViewList().remove(detalle);
		autonomoDM = new ListDataModel<AutonomoView>(
				propietarioView.getAutonomoViewList());
	}

	public boolean isMovilSelected() {
		return (movilPropietarioDetalle != null && movilPropietarioDetalle
				.getMovil() != null);
	}

	public void deseleccionarMovil(ActionEvent event) {
		movilPropietarioDetalle.setMovil(null);
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
		// hojaRutaView.setMovil((Movil) event.getObject());
	}

	public void addMovil(ActionEvent event) {
		if (movilPropietarioDetalle != null
				&& !propietarioView.getMovilPropietarioDetalleList().contains(
						movilPropietarioDetalle)) {

			if (!isDetalleVacio(movilPropietarioDetalle)) {
				try {
					propietarioView
							.getMovilPropietarioDetalleList()
							.add((MovilPropietarioDetalleView) movilPropietarioDetalle
									.clone());
				} catch (CloneNotSupportedException e) {
				}
				movilDM = new ListDataModel<MovilPropietarioDetalleView>(
						propietarioView.getMovilPropietarioDetalleList());
				movilPropietarioDetalle.setMovil(null);
			}
		}
	}

	public void deleteMovil(ActionEvent event) {
		MovilPropietarioDetalleView detalle = movilDM.getRowData();
		propietarioView.getMovilPropietarioDetalleList().remove(detalle);
		movilDM = new ListDataModel<MovilPropietarioDetalleView>(
				propietarioView.getMovilPropietarioDetalleList());
	}

	private boolean isDetalleVacio(
			MovilPropietarioDetalleView movilPropietarioDetalle) {
		boolean ret = false;

		ret = movilPropietarioDetalle.getMovil() == null
				|| movilPropietarioDetalle.getFechaTitularDesde() == null
				|| movilPropietarioDetalle.getFechaCedulaVerde() == null
				|| movilPropietarioDetalle.getNumeroCedulaVerde() == null
				|| movilPropietarioDetalle.getNumeroTitulo() == null;
		return ret;
	}

	public void handleDateSelect(DateSelectEvent event) {
	}
}
