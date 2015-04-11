package hu.neuron.java.refactory.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import hu.neuron.java.refactory.type.TicketType;

public class TicketTypeSerializer implements JsonSerializer<TicketType> {

	@Override
	public JsonElement serialize(TicketType ticketType, Type type,
			JsonSerializationContext context) {
		return new JsonPrimitive(ticketType.getName());
	}

}
