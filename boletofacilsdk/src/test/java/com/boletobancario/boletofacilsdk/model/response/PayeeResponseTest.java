package com.boletobancario.boletofacilsdk.model.response;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.entities.Payee;

public class PayeeResponseTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		PayeeResponse obj = new PayeeResponse();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getData());

		obj.setData(new Payee());
		Assert.assertNotNull(obj.getData());
		Assert.assertNotNull(obj.toJson());
	}
}
