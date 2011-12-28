package logistica.query;

public class LocalidadQuery extends BaseQuery {

	private String descripcion;

	public LocalidadQuery(Long id, String descripcion) {
		super(id);
		this.descripcion = descripcion;
	}

	public LocalidadQuery() {
		this(null, null);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
