package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.UnidadMedida;

@FacesConverter("unidadMedidaConverter")
public class UnidadMedidaConverter extends BaseEnumConverter<UnidadMedida> {

	public UnidadMedidaConverter() {
		super(UnidadMedida.class);
	}
}
