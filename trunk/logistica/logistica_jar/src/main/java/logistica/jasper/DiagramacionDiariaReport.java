package logistica.jasper;

import java.util.Date;

public class DiagramacionDiariaReport implements
		Comparable<DiagramacionDiariaReport> {
	private Date fecha;
	private String novedades;
	private Long numeroSucursal;
	private String nombreSucursal;
	private String movil;
	private String descripcionFlete;
	private Date horarioEntrada;
	private Date horarioSalida;
	private Date horarioPedidoFlete;
	private String nombreAgenciaFlete;
	private String codigoCoto;

	public DiagramacionDiariaReport(Date fecha, String novedades,
			Long numeroSucursal, String nombreSucursal, String movil,
			String descripcionFlete, Date horarioEntrada, Date horarioSalida,
			Date horarioPedidoFlete, String nombreAgenciaFlete,
			String codigoCoto) {
		super();
		this.fecha = fecha;
		this.novedades = novedades;
		this.numeroSucursal = numeroSucursal;
		this.nombreSucursal = nombreSucursal;
		this.movil = movil;
		this.descripcionFlete = descripcionFlete;
		this.horarioEntrada = horarioEntrada;
		this.horarioSalida = horarioSalida;
		this.horarioPedidoFlete = horarioPedidoFlete;
		this.nombreAgenciaFlete = nombreAgenciaFlete;
		this.codigoCoto = codigoCoto;
	}

	public DiagramacionDiariaReport() {
		this(null, null, null, null, null, null, null, null, null, null, null);
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNovedades() {
		return novedades;
	}

	public void setNovedades(String novedades) {
		this.novedades = novedades;
	}

	public Long getNumeroSucursal() {
		return numeroSucursal;
	}

	public void setNumeroSucursal(Long numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getDescripcionFlete() {
		return descripcionFlete;
	}

	public void setDescripcionFlete(String descripcionFlete) {
		this.descripcionFlete = descripcionFlete;
	}

	public Date getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(Date horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public Date getHorarioSalida() {
		return horarioSalida;
	}

	public void setHorarioSalida(Date horarioSalida) {
		this.horarioSalida = horarioSalida;
	}

	public Date getHorarioPedidoFlete() {
		return horarioPedidoFlete;
	}

	public void setHorarioPedidoFlete(Date horarioPedidoFlete) {
		this.horarioPedidoFlete = horarioPedidoFlete;
	}

	public String getNombreAgenciaFlete() {
		return nombreAgenciaFlete;
	}

	public void setNombreAgenciaFlete(String nombreAgenciaFlete) {
		this.nombreAgenciaFlete = nombreAgenciaFlete;
	}

	public String getCodigoCoto() {
		return codigoCoto;
	}

	public void setCodigoCoto(String codigoCoto) {
		this.codigoCoto = codigoCoto;
	}

	public int compareTo(DiagramacionDiariaReport o) {
		int ret = 0;
		if (numeroSucursal.intValue() == o.getNumeroSucursal().intValue()) {
			ret = 0;
		} else if (numeroSucursal.intValue() > o.getNumeroSucursal().intValue()) {
			ret = 1;
		} else {
			ret = -1;
		}

		return ret;
	}
}
