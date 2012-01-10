package logistica.query;

public class MovilQuery extends BaseQuery {
	private String patente;
	private Long numeroMovil;

	public MovilQuery(Long id, String patente, Long numeroMovil) {
		super(id);
		this.patente = patente;
		this.numeroMovil = numeroMovil;
	}

	public MovilQuery() {
		this(null, null, null);
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Long getNumeroMovil() {
		return numeroMovil;
	}

	public void setNumeroMovil(Long numeroMovil) {
		this.numeroMovil = numeroMovil;
	}

}
