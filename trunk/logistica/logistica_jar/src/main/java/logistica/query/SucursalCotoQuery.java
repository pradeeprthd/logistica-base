package logistica.query;

public class SucursalCotoQuery extends BaseQuery {
	private String nombre;
	private Long numeroSucursal;

	public SucursalCotoQuery(Long id, String nombre, Long numeroSucursal) {
		super(id);
		this.nombre = nombre;
		this.numeroSucursal = numeroSucursal;
	}

	public SucursalCotoQuery() {
		this(null, null, null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getNumeroSucursal() {
		return numeroSucursal;
	}

	public void setNumeroSucursal(Long numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}
}
