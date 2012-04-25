package logistica.type;

public enum EstadoHojaRutaEnum implements Labeled {
	PENDIENTE("Pendiente"),
	EN_TRANSITO("En transito"),
	FINALIZADO("Finalizado");

	private String label;
	
	private EstadoHojaRutaEnum(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
