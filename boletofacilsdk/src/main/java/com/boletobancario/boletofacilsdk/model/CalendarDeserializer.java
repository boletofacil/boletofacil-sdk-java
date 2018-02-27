package com.boletobancario.boletofacilsdk.model;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CalendarDeserializer implements JsonDeserializer<Calendar>, JsonSerializer<Calendar> {

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	        throws JsonParseException {
		String dateAsString = json.getAsString();
		try {
			Date date = formatter.parse(dateAsString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(formatter.format(src.getTime()));
	}

}
