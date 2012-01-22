package logistica.query;

import java.util.Date;

public class DiagramacionDiariaQuery extends BaseQuery {
	private Date fecha;

	public DiagramacionDiariaQuery(Long id, Date fecha) {
		super(id);
		this.fecha = fecha;
	}

	public DiagramacionDiariaQuery() {
		this(null, null);
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
