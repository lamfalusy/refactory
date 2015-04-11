package hu.neuron.java.refactory.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import hu.neuron.java.refactory.type.TicketType;

public class TicketTypeDeserializer implements JsonDeserializer<TicketType> {

	@Override
	public TicketType deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		return TicketType.fromString(element.getAsString());
	}

}
