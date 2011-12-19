package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.RolEnum;

@FacesConverter("rolConverter")
public class RolConverter extends BaseEnumConverter<RolEnum> {

	// TODO revisar
//	public RolConverter(Class<RolEnum> targetClass) {
//		super(RolEnum.class);
//	}
}
