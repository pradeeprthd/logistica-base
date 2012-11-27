package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.EstadoEnum;

@FacesConverter("com.converter.EstadoConverter")
public class EstadoConverter extends BaseEnumConverter<EstadoEnum> {

	public EstadoConverter() {
		super(EstadoEnum.class);
	}
}
