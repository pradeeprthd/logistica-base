package com.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class UsuarioView implements Serializable {

	private Long id;

	@NotNull(message = "Valor requerido")
	@Size(min = 1, max = 200, message = "El nombre del usuario debe tener entre 1 y 200 caracteres.")
	private String usuario;

	@NotNull(message = "Valor requerido")
	@Size(min = 6, max = 200, message = "La contraseña del usuario debe tener entre 1 y 200 caracteres.")
	private String contrsenia;

	private Boolean enabled;

	@ManagedProperty("#{authorityView}")
	private List<AuthorityView> authorityViewList;

	public UsuarioView(Long id, String usuario, String contrsenia,
			Boolean enabled, List<AuthorityView> authorityViewList) {
		this.id = id;
		this.usuario = usuario;
		this.contrsenia = contrsenia;
		this.enabled = enabled;
		this.authorityViewList = authorityViewList;
	}

	public UsuarioView() {
		this(null, null, null, null, null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrsenia() {
		return contrsenia;
	}

	public void setContrsenia(String contrsenia) {
		this.contrsenia = contrsenia;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<AuthorityView> getAuthorityViewList() {
		return authorityViewList;
	}

	public void setAuthorityViewList(List<AuthorityView> authorityViewList) {
		this.authorityViewList = authorityViewList;
	}
}
