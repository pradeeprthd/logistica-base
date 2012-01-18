package logistica.type;

public enum UnidadMedidaEnum implements Labeled {
	BULTOS("Bultos"), KILOGRAMOS("Kilogramos"), METROS_CUBICOS("Metros cúbicos");

	private String label;

	private UnidadMedidaEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;

	}
}
