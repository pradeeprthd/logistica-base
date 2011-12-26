package logistica.query;

public class ProveedorQuery extends BaseQuery {
	private String nombre;

	public ProveedorQuery(Long id, String nombre) {
		super(id);
		this.nombre = nombre;
	}

	public ProveedorQuery() {
		this(null, null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
