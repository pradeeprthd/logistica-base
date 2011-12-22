package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.RolEnum;

@FacesConverter("rolConverter")
public class RolConverter extends BaseEnumConverter<RolEnum> {

	public RolConverter() {
		super(RolEnum.class);
	}
}
