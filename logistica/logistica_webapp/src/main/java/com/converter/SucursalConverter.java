package com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Sucursal;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@FacesConverter("com.converter.SucursalConverter")
public class SucursalConverter implements Converter {
	private BaseModelDAO<Sucursal> dao;

	@SuppressWarnings("unchecked")
	public SucursalConverter() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		dao = (BaseModelDAO<Sucursal>) ctx.getBean("sucursalDAO");
	}

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Sucursal sucursal = null;

		try {
			String[] temp = arg2.split("-");
			Long clave = Long.parseLong(temp[0]);

			if (arg2 != null && !"".equalsIgnoreCase(arg2)) {
				sucursal = dao.find(clave);
			}
		} catch (Exception e) {
		}
		return sucursal;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String ret = null;
		if (value != null) {
			Sucursal sucursal = (Sucursal) value;
			ret = sucursal.getID() + "-" + sucursal.getNombre();
		}
		return ret;
	}
}
