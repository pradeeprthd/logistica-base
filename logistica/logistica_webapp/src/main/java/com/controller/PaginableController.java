package com.controller;

import logistica.common.BaseModel;

import org.primefaces.model.LazyDataModel;

@SuppressWarnings("serial")
public abstract class PaginableController<T extends BaseModel> extends
		BaseController<T> {
	public static final int DEFAULT_PAGE_SIZE = 5;

	protected LazyDataModel<T> lazyDM;

	public LazyDataModel<T> getLazyDM() {
		return lazyDM;
	}
}
