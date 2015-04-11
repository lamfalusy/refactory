package hu.neuron.java.refactory.type;

public enum RoleType {
	USER("User"), ADMIN("Admin");

	private final String name;

	private RoleType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static RoleType fromString(String value) {
		for (RoleType type : RoleType.values()) {
			if (value.equalsIgnoreCase(type.getName())) {
				return type;
			}
		}
		return null;
	}
}
