package logistica.query;

public class SucursalQuery extends BaseQuery {
	private String nombre;
	private Long numeroSucursal;

	public SucursalQuery(Long id, String nombre, Long numeroSucursal) {
		super(id);
		this.nombre = nombre;
		this.numeroSucursal = numeroSucursal;
	}

	public SucursalQuery() {
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
