package logistica.type;

public enum EstadoMovilEnum implements Labeled {
	FRANCO("Franco"), NO_OPERATIVO("No operativo");

	private String label;

	private EstadoMovilEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
