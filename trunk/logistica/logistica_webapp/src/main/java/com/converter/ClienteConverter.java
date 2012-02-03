package com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Cliente;

import org.springframework.web.jsf.FacesContextUtils;

@FacesConverter("com.converter.ClienteConverter")
public class ClienteConverter implements Converter {

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Cliente cliente = null;
		BaseModelDAO<Cliente> dao = (BaseModelDAO<Cliente>) FacesContextUtils
				.getWebApplicationContext(arg0).getBean("clienteDAO");
		try {
			String[] temp = arg2.split("-");
			Long clave = Long.parseLong(temp[0]);
			if (arg2 != null && !"".equalsIgnoreCase(arg2)) {
				cliente = dao.find(clave);
			}
		} catch (Exception e) {
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
