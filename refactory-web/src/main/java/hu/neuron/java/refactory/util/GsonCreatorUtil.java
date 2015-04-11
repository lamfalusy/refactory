package hu.neuron.java.refactory.util;

import hu.neuron.java.refactory.serializer.DateDeserializer;
import hu.neuron.java.refactory.serializer.DateSerializer;
import hu.neuron.java.refactory.serializer.PriorityTypeDeserializer;
import hu.neuron.java.refactory.serializer.PriorityTypeSerializer;
import hu.neuron.java.refactory.serializer.StatusTypeDeserializer;
import hu.neuron.java.refactory.serializer.StatusTypeSerializer;
import hu.neuron.java.refactory.serializer.TicketTypeDeserializer;
import hu.neuron.java.refactory.serializer.TicketTypeSerializer;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.neuron.java.refactory.type.PriorityType;
import hu.neuron.java.refactory.type.StatusType;
import hu.neuron.java.refactory.type.TicketType;

public class GsonCreatorUtil {

	public static Gson createGson() {
		return new GsonBuilder()
				.registerTypeAdapter(StatusType.class,
						new StatusTypeDeserializer())
				.registerTypeAdapter(StatusType.class,
						new StatusTypeSerializer())
				.registerTypeAdapter(PriorityType.class,
						new PriorityTypeDeserializer())
				.registerTypeAdapter(PriorityType.class,
						new PriorityTypeSerializer())
				.registerTypeAdapter(TicketType.class,
						new TicketTypeDeserializer())
				.registerTypeAdapter(TicketType.class,
						new TicketTypeSerializer())
				.registerTypeAdapter(Date.class, new DateDeserializer())
				.registerTypeAdapter(Date.class, new DateSerializer()).create();
	}
}
