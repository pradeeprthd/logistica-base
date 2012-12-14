package com.converter;

import javax.faces.convert.FacesConverter;

import logistica.type.CoberturaAdicionalEnum;

@FacesConverter("com.converter.CoberturaAdicionalConverter")
public class CoberturaAdicionalConverter extends
		BaseEnumConverter<CoberturaAdicionalEnum> {

	public CoberturaAdicionalConverter() {
		super(CoberturaAdicionalEnum.class);
	}
}
