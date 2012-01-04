package logistica.query;

public class LocalidadQuery extends BaseQuery {

	private String descripcion;
	private Long provinciaID;

	public LocalidadQuery(Long id, String descripcion, Long provinciaID) {
		super(id);
		this.descripcion = descripcion;
		this.provinciaID = provinciaID;
	}

	public LocalidadQuery() {
		this(null, null, null);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getProvinciaID() {
		return provinciaID;
	}

	public void setProvinciaID(Long provinciaID) {
		this.provinciaID = provinciaID;
	}
}
