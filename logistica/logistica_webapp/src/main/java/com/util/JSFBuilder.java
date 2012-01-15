package com.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import logistica.type.RolEnum;
import logistica.type.UnidadMedida;

public class JSFBuilder {

	public static final List<SelectItem> getRoles() {
		final List<SelectItem> rList = new ArrayList<SelectItem>();

		for (RolEnum e : RolEnum.values()) {
			rList.add(new SelectItem(e, e.getLabel()));
		}

		return rList;
	}

	public static final List<SelectItem> getUnidadesMedida() {
		final List<SelectItem> rList = new ArrayList<SelectItem>();

		for (UnidadMedida e : UnidadMedida.values()) {
			rList.add(new SelectItem(e, e.getLabel()));
		}

		return rList;
	}
}
