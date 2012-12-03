package logistica.type;

public enum ParentezcoEnum implements Labeled {
	NINGUNO("Ninguno"), PADRE_MADRE("Padre/Madre"), HIJO_HIJA("Hijo/Hija"), SOBRINO_SOBRINA(
			"Sobrino/Sobrina"), TIO_TIA("Tio/Tia"), ESPOSO_ESPOSA(
			"Esposo/Esposa"), HERMANO_HERMANA("Hermano/Hermana"), CUÑADO_CUÑADA(
			"Cuñado/Cuñada"), PRIMO_PRIMA("Primo/Prima");

	private String label;

	private ParentezcoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;

	}
}
