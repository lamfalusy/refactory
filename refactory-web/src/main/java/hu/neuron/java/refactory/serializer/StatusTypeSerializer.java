package hu.neuron.java.refactory.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import hu.neuron.java.refactory.type.StatusType;

public class StatusTypeSerializer implements JsonSerializer<StatusType> {

	@Override
	public JsonElement serialize(StatusType statusType, Type type,
			JsonSerializationContext context) {
		return new JsonPrimitive(statusType.getName());
	}

}
