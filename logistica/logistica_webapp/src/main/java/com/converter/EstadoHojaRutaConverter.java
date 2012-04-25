package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.EstadoHojaRutaEnum;

@FacesConverter("com.converter.EstadoHojaRutaConverter")
public class EstadoHojaRutaConverter extends
		BaseEnumConverter<EstadoHojaRutaEnum> {

	public EstadoHojaRutaConverter() {
		super(EstadoHojaRutaEnum.class);
	}
}
