package com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.Converter;

public abstract class BaseEnumConverter<T> implements Converter {

	// TODO revisar
	
	/**
	 */
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Object value = null;
		if (!"".equals(arg2)) {
			//value = super.getAsObject(arg0, arg1, arg2);
		}
		return value;
	}

	/**
	 */
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String cadena = "";
		if (value instanceof Enum<?>) {
			//cadena = super.getAsString(context, component, value);
		}
		return cadena;
	}
}
