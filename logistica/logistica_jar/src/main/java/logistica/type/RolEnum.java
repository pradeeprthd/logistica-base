package logistica.type;

public enum RolEnum implements Labeled {
	ROLE_USER("Usuario"), ROLE_ADMIN("Administrador");

	private String label;

	private RolEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
