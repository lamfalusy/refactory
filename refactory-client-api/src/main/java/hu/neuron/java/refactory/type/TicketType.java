package hu.neuron.java.refactory.type;

public enum TicketType {
	BUG("Bug"), TASK("Task"), NEW_FEATURE("New Feature"), IMPROVEMENT("Improvement"), SUPPORT("Support");

	private final String name;

	private TicketType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static TicketType fromString(String value) {
		for (TicketType type : TicketType.values()) {
			if (value.equalsIgnoreCase(type.getName())) {
				return type;
			}
		}
		return null;
	}
}
