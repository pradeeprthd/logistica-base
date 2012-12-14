package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.TipoUsoEnum;

@FacesConverter("com.converter.TipoUsoConverter")
public class TipoUsoConverter extends BaseEnumConverter<TipoUsoEnum> {

	public TipoUsoConverter() {
		super(TipoUsoEnum.class);
	}
}
