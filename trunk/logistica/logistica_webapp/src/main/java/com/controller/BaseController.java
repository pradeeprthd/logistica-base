package com.controller;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;

import logistica.common.BaseModel;

@SuppressWarnings("serial")
public abstract class BaseController<T extends BaseModel> implements
		Serializable {
	protected boolean addEdit;
	protected ListDataModel<T> listDM;

	public abstract void query(ActionEvent event);

	public abstract void edit(ActionEvent event);

	public abstract void delete(ActionEvent event);

	public abstract void add(ActionEvent event);

	public abstract void save(ActionEvent event);

	public abstract void cancel(ActionEvent event);

	public abstract void clear();

	public boolean isAddEdit() {
		return addEdit;
	}

	public void setAddEdit(boolean addEdit) {
		this.addEdit = addEdit;
	}

	public ListDataModel<T> getListDM() {
		return listDM;
	}
}
