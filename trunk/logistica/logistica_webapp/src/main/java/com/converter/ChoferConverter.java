package com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Chofer;

import org.springframework.web.jsf.FacesContextUtils;

@FacesConverter("com.converter.ChoferConverter")
public class ChoferConverter implements Converter {

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Chofer chofer = null;
		BaseModelDAO<Chofer> dao = (BaseModelDAO<Chofer>) FacesContextUtils
				.getWebApplicationContext(arg0).getBean("choferDAO");
		try {
			String[] temp = arg2.split("-");
			Long clave = Long.parseLong(temp[0]);
			if (arg2 != null && !"".equalsIgnoreCase(arg2)) {
				chofer = dao.find(clave);
			}
		} catch (Exception e) {
		}
		return chofer;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String ret = null;
		if (value != null) {
			Chofer chofer = (Chofer) value;
			ret = chofer.getID() + "-" + chofer.getNombre();
		}
		return ret;
	}
}
