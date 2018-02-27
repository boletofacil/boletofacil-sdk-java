package com.boletobancario.boletofacilsdk.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.response.ErrorResponse;

import junit.framework.Assert;

public class ModelBaseTest extends AbstractTest {
	@Test
	public void fromJson() {
		String json = "{\"success\":false,\"errorMessage\":\"Parâmetro obrigatório 'amount' não está presente\"}";
		ErrorResponse error = ModelBase.fromJson(json, ErrorResponse.class);
		Assert.assertEquals("Parâmetro obrigatório 'amount' não está presente", error.getErrorMessage());
	}

	@Test
	public void toJson() {
		DummyModelClass testClass = new DummyModelClass();
		testClass.setId(0L);
		assertResult("{\"id\": 0}", testClass.toJson());

		testClass.setId(1L);
		testClass.setName("João da Silva");
		assertResult("{\"id\": 1, \"name\": \"João da Silva\"}", testClass.toJson());
	}

	@Test
	public void fromXml() {
		String json = "<result><success>false</success><errorMessage>Valor mínimo para cobrança é de R$ 2,30</errorMessage></result>";
		ErrorResponse error = ModelBase.fromXml(json, ErrorResponse.class);
		Assert.assertEquals("Valor mínimo para cobrança é de R$ 2,30", error.getErrorMessage());
	}

	@Test
	public void toXml() {
		DummyModelClass testClass = new DummyModelClass();
		testClass.setId(0L);
		assertResult("<?xmlversion=\"1.0\"encoding=\"UTF-8\"standalone=\"yes\"?><dummyModelClass>"
		        + "<id>0</id></dummyModelClass>", testClass.toXml());

		testClass.setId(1L);
		testClass.setName("João da Silva");
		assertResult("<?xmlversion=\"1.0\"encoding=\"UTF-8\"standalone=\"yes\"?><dummyModelClass>"
		        + "<id>1</id><name>JoãodaSilva</name></dummyModelClass>", testClass.toXml());
	}
}

@XmlRootElement
class DummyModelClass extends ModelBase {
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
