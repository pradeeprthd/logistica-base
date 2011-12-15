package logistica.query;

public class ClienteQuery extends BaseQuery {
	private String nombre;

	public ClienteQuery(Long id, String nombre) {
		super(id);
		this.nombre = nombre;
	}

	public ClienteQuery() {
		this(null, null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
