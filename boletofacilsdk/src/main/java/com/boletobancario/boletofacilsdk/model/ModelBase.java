package com.boletobancario.boletofacilsdk.model;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.boletobancario.boletofacilsdk.exceptions.BoletoFacilException;
import com.boletobancario.boletofacilsdk.exceptions.BoletoFacilInvalidEntityException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ModelBase {
	public String toJson() {
		try {
			return gson.toJson(this);
		} catch (Exception ex) {
			throw new BoletoFacilInvalidEntityException(this, ex);
		}
	}

	public static <T extends ModelBase> T fromJson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	public String toXml() {
		try (StringWriter writer = new StringWriter()) {
			JAXBContext jaxbContext = JAXBContext.newInstance(getClass());

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(this, writer);
			return writer.toString();
		} catch (JAXBException | IOException ex) {
			throw new BoletoFacilInvalidEntityException(this, ex);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends ModelBase> T fromXml(String xml, Class<T> clazz) {
		try (StringReader reader = new StringReader(xml)) {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			return (T) unmarshaller.unmarshal(reader);
		} catch (JAXBException ex) {
			throw new BoletoFacilException(ex.getMessage(), ex);
		}
	}

	protected static Gson gson = new GsonBuilder()
	        .registerTypeHierarchyAdapter(Calendar.class, new CalendarDeserializer()).setPrettyPrinting()
	        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).setDateFormat("dd/MM/yyyy").create();
}
