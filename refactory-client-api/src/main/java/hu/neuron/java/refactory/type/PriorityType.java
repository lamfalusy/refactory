package hu.neuron.java.refactory.type;

public enum PriorityType {
	TRIVIAL("Trivial"), MINOR("Minor"), MAJOR("Major"), CRITICAL("Critical"), BLOCKER("Blocker");

	private final String name;

	private PriorityType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static PriorityType fromString(String value) {
		for (PriorityType type : PriorityType.values()) {
			if (value.equalsIgnoreCase(type.getName())) {
				return type;
			}
		}
		return null;
	}
}
