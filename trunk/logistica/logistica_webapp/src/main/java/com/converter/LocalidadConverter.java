package com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Localidad;

import org.springframework.web.jsf.FacesContextUtils;

@FacesConverter("com.converter.LocalidadConverter")
public class LocalidadConverter implements Converter {

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Localidad localidad = null;
		BaseModelDAO<Localidad> dao = (BaseModelDAO<Localidad>) FacesContextUtils
				.getWebApplicationContext(arg0).getBean("localidadDAO");
		try {
			String[] temp = arg2.split("-");
			Long clave = Long.parseLong(temp[0]);
			if (arg2 != null && !"".equalsIgnoreCase(arg2)) {

				localidad = dao.find(clave);
			}
		} catch (Exception e) {
		}
		return localidad;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String ret = null;
		if (value != null) {
			Localidad localidad = (Localidad) value;
			ret = localidad.getID() + "-" + localidad.getDescripcion();
		}
		return ret;
	}
}
