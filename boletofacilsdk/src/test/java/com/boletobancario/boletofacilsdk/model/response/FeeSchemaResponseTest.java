package com.boletobancario.boletofacilsdk.model.response;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.entities.FeeSchema;

public class FeeSchemaResponseTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		FeeSchemaResponse obj = new FeeSchemaResponse();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getData());

		obj.setData(new FeeSchema());
		Assert.assertNotNull(obj.getData());
		Assert.assertNotNull(obj.toJson());
	}
}
