package com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Localidad;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@FacesConverter("com.converter.LocalidadConverter")
public class LocalidadConverter implements Converter {

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Localidad localidad = null;
		if (arg2 != null && !"".equalsIgnoreCase(arg2)) {
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			BaseModelDAO<Localidad> dao = (BaseModelDAO<Localidad>) ctx
					.getBean("localidadDAO");
			localidad = dao.get(arg2);
		}
		return localidad;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String ret = null;
		if (value != null) {
			Localidad localidad = (Localidad) value;
			ret = localidad.getDescripcion();
		}
		return ret;
	}
}
