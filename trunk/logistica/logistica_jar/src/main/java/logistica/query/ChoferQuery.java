package logistica.query;

public class ChoferQuery extends BaseQuery {
	private String nombre;

	public ChoferQuery(Long id, String nombre) {
		super(id);
		this.nombre = nombre;
	}

	public ChoferQuery() {
		this(null, null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
