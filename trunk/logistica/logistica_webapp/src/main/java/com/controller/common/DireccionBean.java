package com.controller.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Localidad;
import logistica.model.Provincia;
import logistica.query.LocalidadQuery;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.DireccionBuilder;
import com.view.DireccionView;

@SuppressWarnings({ "serial", "restriction" })
@ManagedBean
@ViewScoped
public class DireccionBean implements Serializable {

	private Logger log = Logger.getLogger(DireccionBean.class);
	@ManagedProperty("#{direccionView}")
	private DireccionView direccionView;
	@ManagedProperty("#{direccionBuilder}")
	private DireccionBuilder direccionBuilder;
	private Long provinciaID;

	private BaseModelDAO<Provincia> daoProvincia;
	private BaseModelDAO<Localidad> daoLocalidad;
	private List<SelectItem> provinciaSIL;

	@SuppressWarnings("unchecked")
	public DireccionBean(DireccionView direccionView, Long provinviaID,
			Localidad localidad) {
		super();
		this.direccionView = direccionView;
		this.provinciaID = provinviaID;

		daoProvincia = (BaseModelDAO<Provincia>) FacesContextUtils
				.getWebApplicationContext(FacesContext.getCurrentInstance())
				.getBean("provinciaDAO");

		daoLocalidad = (BaseModelDAO<Localidad>) FacesContextUtils
				.getWebApplicationContext(FacesContext.getCurrentInstance())
				.getBean("localidadDAO");

		provinciaSIL = new ArrayList<SelectItem>();
		for (Provincia provincia : daoProvincia.getList()) {
			provinciaSIL.add(new SelectItem(provincia.getID(), provincia
					.getDescripcion()));
		}
	}

	public DireccionBean() {
		this(null, null, null);
	}

	public DireccionView getDireccionView() {
		return direccionView;
	}

	public void setDireccionView(DireccionView direccionView) {
		this.direccionView = direccionView;
	}

	public Long getProvinciaID() {
		return provinciaID;
	}

	public void setProvinciaID(Long provinciaID) {
		this.provinciaID = provinciaID;
	}

	@PostConstruct
	public void init() {
		this.direccionView = new DireccionView();
	}

	public List<Localidad> completeLocalidad(String query) {
		List<Localidad> localidadList = null;
		try {
			LocalidadQuery localidadQuery = new LocalidadQuery(null, query,
					provinciaID);
			localidadList = daoLocalidad.getList(localidadQuery);

		} catch (Throwable e) {
			log.error("Error en le metodo completeLocalidad: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
		return localidadList;
	}

	public boolean isLocalidadSelected() {
		return (direccionView.getLocalidad() != null);
	}

	public void deselecionarLocalidad(ActionEvent event) {
		direccionView.setLocalidad(null);
	}

	public void handleSelect(SelectEvent event) {
		System.out.println("se eligio una localidad");
		direccionView.setLocalidad((Localidad) event.getObject());
	}

	public void provinciaListener() {
		System.out.println(provinciaID);
	}

	public DireccionBuilder getDireccionBuilder() {
		return direccionBuilder;
	}

	public void setDireccionBuilder(DireccionBuilder direccionBuilder) {
		this.direccionBuilder = direccionBuilder;
	}

	public List<SelectItem> getProvinciaSIL() {
		return provinciaSIL;
	}

	public void clear() {
		direccionView = new DireccionView();
		provinciaID = null;
	}
}
