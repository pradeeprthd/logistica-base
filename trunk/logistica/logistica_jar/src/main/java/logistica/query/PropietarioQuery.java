package logistica.query;

public class PropietarioQuery extends BaseQuery {
	private String nombre;
	private String dni;
	private String cuit;

	public PropietarioQuery(Long id, String nombre) {
		super(id);
		this.nombre = nombre;
	}

	public PropietarioQuery(Long id, String nombre, String dni, String cuit) {
		super(id);
		this.nombre = nombre;
		this.dni = dni;
		this.cuit = cuit;
	}

	public PropietarioQuery() {
		this(null, null, null, null);
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

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

}
