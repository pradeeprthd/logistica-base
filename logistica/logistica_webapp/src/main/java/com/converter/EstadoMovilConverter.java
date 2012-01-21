package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.EstadoMovilEnum;

@FacesConverter("com.converter.EstadoMovilConverter")
public class EstadoMovilConverter extends BaseEnumConverter<EstadoMovilEnum> {

	public EstadoMovilConverter() {
		super(EstadoMovilEnum.class);
	}
}
