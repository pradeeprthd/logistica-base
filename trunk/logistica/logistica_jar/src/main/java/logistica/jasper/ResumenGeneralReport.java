package logistica.jasper;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResumenGeneralReport implements Serializable {
	private String asignadoA;
	private Integer total;
	private Integer totalGeneral;

	public ResumenGeneralReport(String asignadoA, Integer total,
			Integer totalGeneral) {
		super();
		this.asignadoA = asignadoA;
		this.total = total;
		this.totalGeneral = totalGeneral;
	}

	public ResumenGeneralReport() {

	}

	public String getAsignadoA() {
		return asignadoA;
	}

	public void setAsignadoA(String asignadoA) {
		this.asignadoA = asignadoA;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalGeneral() {
		return totalGeneral;
	}

	public void setTotalGeneral(Integer totalGeneral) {
		this.totalGeneral = totalGeneral;
	}
}
