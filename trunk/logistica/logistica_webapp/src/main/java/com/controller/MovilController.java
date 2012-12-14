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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Autonomo;
import logistica.model.Chofer;
import logistica.model.Movil;
import logistica.query.ChoferQuery;
import logistica.query.MovilQuery;
import logistica.type.AsignacionMovilEnum;
import logistica.type.CoberturaAdicionalEnum;
import logistica.type.EstadoEnum;
import logistica.type.ParentezcoEnum;
import logistica.type.TipoCombustibleEnum;
import logistica.type.TipoUsoEnum;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.AutonomoBuilder;
import com.builder.MovilBuilder;
import com.util.JSFUtil;
import com.view.AutonomoView;
import com.view.MovilView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MovilController extends PaginableController<Movil> {
	private Logger log = Logger.getLogger(MovilController.class);
	private BaseModelDAO<Movil> dao;
	private BaseModelDAO<Chofer> daoChofer;
	private Movil movil;
	private MovilQuery movilQuery;
	private List<AsignacionMovilEnum> asignacionMovilEnumList;
	private List<TipoCombustibleEnum> tipoCombustibleEnumList;
	private List<ParentezcoEnum> parentezcoEnumList;
	private List<EstadoEnum> estadoEnumList;
	private List<TipoUsoEnum> tipoUsoEnumList;
	private List<CoberturaAdicionalEnum> coberturaAdicionalEnumList;
	private Autonomo patcom;
	private DataModel<AutonomoView> patcomDM;

	@ManagedProperty("#{autonomoBuilder}")
	private AutonomoBuilder autonomoBuilder;

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
			daoChofer = (BaseModelDAO<Chofer>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("choferDAO");
			movilQuery = new MovilQuery();
			asignacionMovilEnumList = Arrays.asList(AsignacionMovilEnum
					.values());
			tipoCombustibleEnumList = Arrays.asList(TipoCombustibleEnum
					.values());
			tipoUsoEnumList = Arrays.asList(TipoUsoEnum.values());
			coberturaAdicionalEnumList = Arrays.asList(CoberturaAdicionalEnum
					.values());
			parentezcoEnumList = Arrays.asList(ParentezcoEnum.values());
			estadoEnumList = Arrays.asList(EstadoEnum.values());
			patcomDM = new ListDataModel<AutonomoView>();
			patcom = new Autonomo();
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

	public List<ParentezcoEnum> getParentezcoEnumList() {
		return parentezcoEnumList;
	}

	public List<EstadoEnum> getEstadoEnumList() {
		return estadoEnumList;
	}

	public Autonomo getPatcom() {
		return patcom;
	}

	public DataModel<AutonomoView> getPatcomDM() {
		return patcomDM;
	}

	public AutonomoBuilder getAutonomoBuilder() {
		return autonomoBuilder;
	}

	public void setAutonomoBuilder(AutonomoBuilder autonomoBuilder) {
		this.autonomoBuilder = autonomoBuilder;
	}

	public List<TipoCombustibleEnum> getTipoCombustibleEnumList() {
		return tipoCombustibleEnumList;
	}

	public List<TipoUsoEnum> getTipoUsoEnumList() {
		return tipoUsoEnumList;
	}

	public List<CoberturaAdicionalEnum> getCoberturaAdicionalEnumList() {
		return coberturaAdicionalEnumList;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			movil = (Movil) lazyDM.getRowData();
			movil = dao.findFULL(movil.getID());
			patcomDM = new ListDataModel<AutonomoView>(
					autonomoBuilder.toView(movil.getPatcomList()));
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

	public boolean isChofer1Selected() {
		return (movilView.getChofer1() != null);
	}

	public void deseleccionarChofer1(ActionEvent event) {
		movilView.setChofer1(null);
	}

	public boolean isChofer2Selected() {
		return (movilView.getChofer2() != null);
	}

	public void deseleccionarChofer2(ActionEvent event) {
		movilView.setChofer2(null);
	}

	public boolean isChofer3Selected() {
		return (movilView.getChofer3() != null);
	}

	public void deseleccionarChofer3(ActionEvent event) {
		movilView.setChofer3(null);
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
		// hojaRutaView.setChofer((Chofer) event.getObject());
	}

	public void addPatcom(ActionEvent event) {
		movilView.getPatcomList().add(
				(AutonomoView) autonomoBuilder.toView(patcom));
		patcomDM = new ListDataModel<AutonomoView>(movilView.getPatcomList());
	}

	public void deletePatcom(ActionEvent event) {
		AutonomoView detalle = patcomDM.getRowData();
		movilView.getPatcomList().remove(detalle);
		patcomDM = new ListDataModel<AutonomoView>(movilView.getPatcomList());
	}
}
