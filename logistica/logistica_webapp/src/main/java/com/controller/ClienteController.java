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
import logistica.model.Cliente;
import logistica.query.ClienteQuery;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.builder.ClienteBuilder;
import com.util.JSFUtil;
import com.view.ClienteView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ClienteController extends PaginableController<Cliente> {
	private Logger log = Logger.getLogger(ClienteController.class);
	private ClassPathXmlApplicationContext ctx;
	private BaseModelDAO<Cliente, ClienteQuery> dao;
	private Cliente cliente;
	private ClienteQuery clienteQuery;

	@ManagedProperty("#{clienteView}")
	private ClienteView clienteView;

	@ManagedProperty("#{clienteBuilder}")
	private ClienteBuilder clienteBuilder;

	@SuppressWarnings("unchecked")
	public ClienteController() {
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			dao = (BaseModelDAO<Cliente, ClienteQuery>) ctx
					.getBean("clienteDAO");
			clienteQuery = new ClienteQuery();
			addEdit = false;
		} catch (Throwable e) {
			log.error("Error al inicializar la clase ClienteController", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public ClienteView getClienteView() {
		return clienteView;
	}

	public void setClienteView(ClienteView clienteView) {
		this.clienteView = clienteView;
	}

	public ClienteBuilder getClienteBuilder() {
		return clienteBuilder;
	}

	public void setClienteBuilder(ClienteBuilder clienteBuilder) {
		this.clienteBuilder = clienteBuilder;
	}

	public ClienteQuery getClienteQuery() {
		return clienteQuery;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			cliente = (Cliente) lazyDM.getRowData();
			// cliente = dao.find(cliente.getID());
			clienteView = clienteBuilder.toView(cliente);
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
			cliente = (Cliente) lazyDM.getRowData();
			dao.delete(cliente);
			JSFUtil.reloadPage();
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
			cliente = clienteBuilder.toDomain(clienteView);
			if (cliente.getID() != null) {
				dao.edit(cliente);
				addEdit = false;
			} else {
				dao.save(cliente);
			}
			clear();
			JSFUtil.saveMessage("Elemento guardado con exito",
					FacesMessage.SEVERITY_INFO);
			if (!addEdit) {
				JSFUtil.reloadPage();
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
		JSFUtil.reloadPage();
	}

	public void clear() {
		cliente = new Cliente();
		clienteView = new ClienteView();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<Cliente>() {

			@Override
			public List<Cliente> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, String> filtro = new HashMap<String, String>();
				filtro.put("nombre", clienteQuery.getNombre());
				return dao.getList(first, pageSize, "nombre", true, filtro);
			}

		};

		Map<String, String> filtro = new HashMap<String, String>();
		filtro.put("nombre", clienteQuery.getNombre());
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}
}
