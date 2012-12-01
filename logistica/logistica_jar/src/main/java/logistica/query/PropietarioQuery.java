package logistica.query;

public class PropietarioQuery extends BaseQuery {
	private String nombre;

	public PropietarioQuery(Long id, String nombre) {
		super(id);
		this.nombre = nombre;
	}

	public PropietarioQuery() {
		this(null, null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
