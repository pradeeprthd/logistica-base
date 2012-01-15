package logistica.type;

public enum UnidadMedida implements Labeled {
	BULTOS("Bultos"), KILOGRAMOS("Kilogramos"), METROS_CUBICOS("Metros cúbicos");

	private String label;

	private UnidadMedida(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;

	}
}
