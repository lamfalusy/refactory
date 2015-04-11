package hu.neuron.java.refactory.type;

public enum StatusType {
	NONE("None"), NEED_INFO("Need Info"), IN_PROGRESS("In Progress"), READY("Ready"), FIXED("Fixed");
	
	private final String name;
	
	private StatusType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public static StatusType fromString(String value) {
		for (StatusType type : StatusType.values()) {
			if (value.equalsIgnoreCase(type.getName())) {
				return type;
			}
		}
		return null;
	}
}
