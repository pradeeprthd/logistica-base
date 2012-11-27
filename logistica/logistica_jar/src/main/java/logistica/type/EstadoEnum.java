package logistica.type;

public enum EstadoEnum implements Labeled {

	ACTIVO("Activo"), INACTIVO("Inactivo");

	private String label;

	private EstadoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;

	}
}
