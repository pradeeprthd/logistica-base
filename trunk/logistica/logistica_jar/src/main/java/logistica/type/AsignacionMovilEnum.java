package logistica.type;

public enum AsignacionMovilEnum implements Labeled {
	PARTICULAR("Particular"), ENVIOS_DOMICILIO("Envíos a domicilio"), PANIFICADORA(
			"Panificadora"), INSUMOS("Insumos"), COUNTRIES("Countries"), MANTENIMIENTO(
			"Mantenimiento"), SERVICIO_TECNICO("Serv. técnico"), SERVICIO_ESPECIAL_CON_FRIO(
			"Serv. esp. c/frío"), LABORATORIO("Laboratotio"), SERVICIO_PASTAS(
			"Serv. pastas"), CORREO("Correo");

	private String label;

	private AsignacionMovilEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;

	}
}
