package logistica.query;

public class UsuarioQuery extends BaseQuery {

	private String usuario;

	public UsuarioQuery(Long id, String usuario) {
		super(id);
		this.usuario = usuario;
	}

	public UsuarioQuery() {
		this(null, null);
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
