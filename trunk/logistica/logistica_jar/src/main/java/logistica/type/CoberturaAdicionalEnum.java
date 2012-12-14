package logistica.type;

public enum CoberturaAdicionalEnum implements Labeled {

	NINGUNO("Ninguno"), GNC("GNC"), EQUIPO_DE_FRIO("Equipo de frio"), GNC_Y_EQUIPO_DE_FRIO(
			"GNC y Equipo de frio"), LLANTAS_ESPECIALES("LLantas especiales");

	private String label;

	private CoberturaAdicionalEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;

	}
}
