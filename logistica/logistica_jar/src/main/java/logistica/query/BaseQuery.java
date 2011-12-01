package logistica.query;

public class BaseQuery {

	private Long id;

	public BaseQuery(Long id) {
		this.id = id;
	}

	public BaseQuery() {
		this(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
