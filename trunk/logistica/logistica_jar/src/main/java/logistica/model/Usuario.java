package logistica.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import logistica.common.BaseModel;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuario extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "UsuarioSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Column(length = 200, unique = true)
	private String usuario;

	@Column(length = 200)
	private String contrasenia;

	@Column(name = "enable", columnDefinition = "BOOLEAN")
	private boolean enable;

	@OneToMany
	private List<Authority> authorityList;

	public Usuario(Long id, String usuario, String contrasenia, boolean enable,
			List<Authority> authorityList) {
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.enable = enable;
		this.authorityList = authorityList;
	}

	public Usuario() {
		this(null, null, null, false, null);
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}
}
