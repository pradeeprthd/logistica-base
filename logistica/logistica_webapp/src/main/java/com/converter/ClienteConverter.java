package com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Cliente;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@FacesConverter("com.converter.ClienteConverter")
public class ClienteConverter implements Converter {
	private BaseModelDAO<Cliente> dao;

	@SuppressWarnings("unchecked")
	public ClienteConverter() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		dao = (BaseModelDAO<Cliente>) ctx.getBean("clienteDAO");
	}

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		String[] temp = arg2.split("-");
		Long clave = Long.parseLong(temp[0]);
		Cliente cliente = null;
		if (arg2 != null && !"".equalsIgnoreCase(arg2)) {
			cliente = dao.find(clave);
		}
		return cliente;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String ret = null;
		if (value != null) {
			Cliente cliente = (Cliente) value;
			ret = cliente.getID() + "-" + cliente.getNombre();
		}
		return ret;
	}
}
