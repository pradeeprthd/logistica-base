package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import logistica.model.Authority;

import com.view.AuthorityView;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AuthorityBuilder extends BaseBuilder<AuthorityView, Authority> {

	public Authority toDomain(AuthorityView view) {
		return new Authority(view.getId(), view.getNombre());
	}

	public AuthorityView toView(Authority model) {
		return new AuthorityView(model.getID(), model.getNombre());
	}
}
