package logistica.query;

public class MovilQuery extends BaseQuery {
	private String patente;
	private Long numeroMovil;
	private String numeroMotor;
	private String numeroChasis;
	private String comunicacion;

	public MovilQuery(Long id, String patente, Long numeroMovil,
			String numeroMotor, String numeroChasis, String comunicacion) {
		super(id);
		this.patente = patente;
		this.numeroMovil = numeroMovil;
		this.numeroMotor = numeroMotor;
		this.numeroChasis = numeroChasis;
		this.comunicacion = comunicacion;
	}

	public MovilQuery(Long id, String patente, Long numeroMovil) {
		super(id);
		this.patente = patente;
		this.numeroMovil = numeroMovil;
	}

	public MovilQuery() {
		this(null, null, null, null, null, null);
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

	public String getNumeroMotor() {
		return numeroMotor;
	}

	public void setNumeroMotor(String numeroMotor) {
		this.numeroMotor = numeroMotor;
	}

	public String getNumeroChasis() {
		return numeroChasis;
	}

	public void setNumeroChasis(String numeroChasis) {
		this.numeroChasis = numeroChasis;
	}

	public String getComunicacion() {
		return comunicacion;
	}

	public void setComunicacion(String comunicacion) {
		this.comunicacion = comunicacion;
	}

}
