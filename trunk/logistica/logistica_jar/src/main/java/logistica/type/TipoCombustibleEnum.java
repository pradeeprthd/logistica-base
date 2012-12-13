package logistica.type;

public enum TipoCombustibleEnum implements Labeled {
	DIESEL("Diesel"), GNC("GNC"), NAFTA("Nafta");

	private String label;

	private TipoCombustibleEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
