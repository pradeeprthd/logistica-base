package com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Movil;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@FacesConverter("com.converter.MovilConverter")
public class MovilConverter implements Converter {
	private BaseModelDAO<Movil> dao;

	@SuppressWarnings("unchecked")
	public MovilConverter() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		dao = (BaseModelDAO<Movil>) ctx.getBean("movilDAO");
	}

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Movil movil = null;
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
