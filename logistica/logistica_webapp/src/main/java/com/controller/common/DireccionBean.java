package com.controller.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Localidad;
import logistica.model.Provincia;
import logistica.model.composite.Direccion;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DireccionBean implements Serializable {

	private Logger log = Logger.getLogger(DireccionBean.class);
	private Direccion direccion;
	private Long provinciaID;
	private Localidad localidad;

	private ClassPathXmlApplicationContext ctx;
	private BaseModelDAO<Provincia> daoProvincia;
	private BaseModelDAO<Localidad> daoLocalidad;

	@SuppressWarnings("unchecked")
	public DireccionBean(Direccion direccion, Long provinviaID,
			Localidad localidad) {
		super();
		this.direccion = direccion;
		this.provinciaID = provinviaID;
		this.localidad = localidad;

		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		daoProvincia = (BaseModelDAO<Provincia>) ctx.getBean("provinciaDAO");

		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		daoLocalidad = (BaseModelDAO<Localidad>) ctx.getBean("localidadDAO");
	}

	public DireccionBean() {
		this(null, null, null);
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Long getProvinciaID() {
		return provinciaID;
	}

	public void setProvinciaID(Long provinviaID) {
		this.provinciaID = provinviaID;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public void setLocalidadID(Localidad localidad) {
		this.localidad = localidad;
	}

	@PostConstruct
	public void init() {
		this.direccion = new Direccion();
		this.provinciaID = null;
		this.localidad = null;
	}

	/**
	 */
	public final List<SelectItem> getProvinciaSIL() {
		final List<SelectItem> list = new ArrayList<SelectItem>();

		for (Provincia provincia : daoProvincia.getList()) {
			list.add(new SelectItem(provincia.getID(), provincia
					.getDescripcion()));
		}

		return list;
	}

	public List<Localidad> completeLocalidad(String query) {
		List<Localidad> localidadList = null;
		try {
			localidadList = daoLocalidad.getList(query);

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
		return (getLocalidad() != null);
	}

	public void deselecionarLocalidad(ActionEvent event) {
		localidad = null;
	}

	public void handleSelect(SelectEvent event) {
		System.out.println("se eligio una localidad");
	}
}
