package com.builder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import logistica.model.Usuario;

import com.view.UsuarioView;

@ManagedBean
@ViewScoped
public class UsuarioBuilder extends BaseBuilder<UsuarioView, Usuario> {

	@ManagedProperty("#{authorityBuilder}")
	private AuthorityBuilder authorityBuilder;

	public Usuario toDomain(UsuarioView view) {
		return new Usuario(view.getId(), view.getUsuario(),
				view.getContrsenia(), view.getEnabled().booleanValue(),
				authorityBuilder.toDomain(view.getAuthorityViewList()));
	}

	public UsuarioView toView(Usuario model) {
		return new UsuarioView(model.getID(), model.getUsuario(),
				model.getContrasenia(), model.isEnable(),
				authorityBuilder.toView(model.getAuthorityList()));
	}
}
