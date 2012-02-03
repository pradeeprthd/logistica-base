package com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Movil;

import org.springframework.web.jsf.FacesContextUtils;

@FacesConverter("com.converter.MovilConverter")
public class MovilConverter implements Converter {

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Movil movil = null;
		BaseModelDAO<Movil> dao = (BaseModelDAO<Movil>) FacesContextUtils
				.getWebApplicationContext(arg0).getBean("movilDAO");
		try {
			String[] temp = arg2.split("-");
			Long clave = Long.parseLong(temp[0]);

			if (arg2 != null && !"".equalsIgnoreCase(arg2)) {
				movil = dao.find(clave);
			}
		} catch (Exception e) {
		}
		return movil;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String ret = null;
		if (value != null) {
			Movil movil = (Movil) value;
			ret = movil.getID() + "-" + movil.getPatente();
		}
		return ret;
	}
}
