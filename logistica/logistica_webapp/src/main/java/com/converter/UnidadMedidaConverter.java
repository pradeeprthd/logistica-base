package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.UnidadMedidaEnum;

@FacesConverter("com.converter.UnidadMedidaConverter")
public class UnidadMedidaConverter extends BaseEnumConverter<UnidadMedidaEnum> {

	public UnidadMedidaConverter() {
		super(UnidadMedidaEnum.class);
	}
}
