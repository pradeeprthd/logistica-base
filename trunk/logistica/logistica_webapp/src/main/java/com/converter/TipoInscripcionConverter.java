package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.TipoInscripcionEnum;

@FacesConverter("com.converter.TipoInscripcionConverter")
public class TipoInscripcionConverter extends
		BaseEnumConverter<TipoInscripcionEnum> {

	public TipoInscripcionConverter() {
		super(TipoInscripcionEnum.class);
	}
}
