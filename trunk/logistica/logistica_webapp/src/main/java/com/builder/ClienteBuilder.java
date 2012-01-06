package com.builder;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.Cliente;

import com.controller.common.DireccionBean;
import com.view.ClienteView;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBuilder extends BaseBuilder<ClienteView, Cliente> implements
		Serializable {
	
	@ManagedProperty("#{direccionBuilder}")
	private DireccionBuilder direccionBuilder;

	public Cliente toDomain(ClienteView view) {

		return new Cliente(view.getId(), view.getNombre(), direccionBuilder.toDomain(view.get) ,null);
	}

	public ClienteView toView(Cliente model) {
		if (model != null) {
			return new ClienteView(model.getID(), model.getNombre());
		}

		return null;
	}

}
