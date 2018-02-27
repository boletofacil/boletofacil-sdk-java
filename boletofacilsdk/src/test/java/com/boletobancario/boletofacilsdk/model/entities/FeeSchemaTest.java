package com.boletobancario.boletofacilsdk.model.entities;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class FeeSchemaTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		FeeSchema obj = new FeeSchema();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getId());
		Assert.assertNull(obj.getFeeSchemaToken());

		obj.setId(Long.MAX_VALUE);
		obj.setFeeSchemaToken("TOKEN12345");
		Assert.assertEquals(Long.MAX_VALUE, obj.getId().longValue());
		Assert.assertEquals("TOKEN12345", obj.getFeeSchemaToken());
	}

}
