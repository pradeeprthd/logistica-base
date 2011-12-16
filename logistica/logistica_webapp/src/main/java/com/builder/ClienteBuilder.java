package com.builder;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Cliente;

import com.view.ClienteView;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBuilder extends BaseBuilder<ClienteView, Cliente> implements
		Serializable {

	public Cliente toDomain(ClienteView view) {

		return new Cliente(view.getId(), view.getNombre(), null);
	}

	public ClienteView toView(Cliente model) {
		if (model != null) {
			return new ClienteView(model.getID(), model.getNombre());
		}

		return null;
	}

}
