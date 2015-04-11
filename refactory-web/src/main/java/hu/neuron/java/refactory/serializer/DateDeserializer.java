package hu.neuron.java.refactory.serializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateDeserializer implements JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy.MM.dd").parse(element.getAsString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(date);
		
		return date;
	}

}
