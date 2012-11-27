package logistica.type;

public enum TipoInscripcionEnum implements Labeled {
	NINGUNO("Ninguno"), MONOTRIBUTO("Monotributo"), REPONSABLE_INSCRIPTO(
			"Reponsable Inscripto");

	private String label;

	private TipoInscripcionEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
