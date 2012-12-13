package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.TipoCombustibleEnum;

@FacesConverter("com.converter.TipoCombustibleConverter")
public class TipoCombustibleConverter extends
		BaseEnumConverter<TipoCombustibleEnum> {

	public TipoCombustibleConverter() {
		super(TipoCombustibleEnum.class);
	}
}
