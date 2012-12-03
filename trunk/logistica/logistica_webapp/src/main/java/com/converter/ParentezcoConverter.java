package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.ParentezcoEnum;

@FacesConverter("com.converter.ParentezcoConverter")
public class ParentezcoConverter extends BaseEnumConverter<ParentezcoEnum> {

	public ParentezcoConverter() {
		super(ParentezcoEnum.class);
	}
}
