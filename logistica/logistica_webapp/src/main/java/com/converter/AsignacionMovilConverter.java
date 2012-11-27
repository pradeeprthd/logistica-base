package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.AsignacionMovilEnum;

@FacesConverter("com.converter.AsignacionMovilConverter")
public class AsignacionMovilConverter extends
		BaseEnumConverter<AsignacionMovilEnum> {

	public AsignacionMovilConverter() {
		super(AsignacionMovilEnum.class);
	}
}
