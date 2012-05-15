package logistica.query;

import java.util.Date;

public class MovilDisponibleQuery extends BaseQuery {
	private Date fecha;
	
	public MovilDisponibleQuery(Long id, Date fecha) {
		super(id);
		this.fecha = fecha;
	}
	
	public MovilDisponibleQuery() {
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
