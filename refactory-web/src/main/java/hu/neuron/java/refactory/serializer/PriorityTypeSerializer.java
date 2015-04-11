package hu.neuron.java.refactory.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import hu.neuron.java.refactory.type.PriorityType;

public class PriorityTypeSerializer implements JsonSerializer<PriorityType> {

	@Override
	public JsonElement serialize(PriorityType priorityType, Type type,
			JsonSerializationContext context) {
		return new JsonPrimitive(priorityType.getName());
	}

}
