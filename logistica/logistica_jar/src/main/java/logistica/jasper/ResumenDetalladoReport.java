package logistica.jasper;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ResumenDetalladoReport implements Serializable {

	private String asignadoA;
	private List<ResumenDetalladoDetalleReport> resumenDetalladoDetalleReportList;

	public ResumenDetalladoReport() {

	}

	public ResumenDetalladoReport(
			String asignadoA,
			List<ResumenDetalladoDetalleReport> resumenDetalladoDetalleReportList) {
		super();
		this.asignadoA = asignadoA;
		this.resumenDetalladoDetalleReportList = resumenDetalladoDetalleReportList;
	}

	public String getAsignadoA() {
		return asignadoA;
	}

	public void setAsignadoA(String asignadoA) {
		this.asignadoA = asignadoA;
	}

	public List<ResumenDetalladoDetalleReport> getResumenDetalladoDetalleReportList() {
		return resumenDetalladoDetalleReportList;
	}

	public void setResumenDetalladoDetalleReportList(
			List<ResumenDetalladoDetalleReport> resumenDetalladoDetalleReportList) {
		this.resumenDetalladoDetalleReportList = resumenDetalladoDetalleReportList;
	}
}
