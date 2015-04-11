package hu.neuron.java.refactory.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import hu.neuron.java.refactory.type.PriorityType;

public class PriorityTypeDeserializer implements JsonDeserializer<PriorityType> {

	@Override
	public PriorityType deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		return PriorityType.fromString(element.getAsString());
	}

}
