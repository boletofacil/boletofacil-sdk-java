package com.boletobancario.boletofacilsdk.model.entities;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class PayerTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		Payer obj = new Payer();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getEmail());
		Assert.assertNull(obj.getSecondaryEmail());
		Assert.assertNull(obj.getPhone());

		obj.setEmail("email@email.com");
		obj.setSecondaryEmail("email2@email.com");
		obj.setPhone("(11) 99876-5432");
		Assert.assertEquals("email@email.com", obj.getEmail());
		Assert.assertEquals("email2@email.com", obj.getSecondaryEmail());
		Assert.assertEquals("(11) 99876-5432", obj.getPhone());
	}
}
