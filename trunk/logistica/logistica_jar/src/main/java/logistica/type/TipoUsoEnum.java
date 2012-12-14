package logistica.type;

public enum TipoUsoEnum implements Labeled {

	COMERCIAL("Comercial"), PARTICULAR("Particular");

	private String label;

	private TipoUsoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;

	}
}
