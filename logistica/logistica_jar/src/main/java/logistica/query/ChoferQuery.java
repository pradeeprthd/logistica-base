package logistica.query;

public class ChoferQuery extends BaseQuery {
	private String nombre;
	private String dni;

	public ChoferQuery(Long id, String nombre, String dni) {
		super(id);
		this.nombre = nombre;
		this.dni = dni;
	}

	public ChoferQuery(Long id, String nombre) {
		super(id);
		this.nombre = nombre;
	}

	public ChoferQuery() {
		this(null, null, null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
