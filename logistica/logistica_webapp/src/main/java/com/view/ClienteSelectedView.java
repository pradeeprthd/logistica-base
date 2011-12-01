package com.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;

import logistica.model.Cliente;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ClienteSelectedView implements Serializable {

	@NotNull(message = "Valor requerido")
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
