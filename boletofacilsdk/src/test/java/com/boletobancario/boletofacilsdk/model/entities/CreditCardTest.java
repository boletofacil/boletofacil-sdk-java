package com.boletobancario.boletofacilsdk.model.entities;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class CreditCardTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		CreditCard obj = new CreditCard();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getNumber());
		Assert.assertNull(obj.getHolderName());
		Assert.assertNull(obj.getSecurityCode());
		Assert.assertNull(obj.getExpirationMonth());
		Assert.assertNull(obj.getExpirationYear());

		obj.setNumber("5480452215505109");
		obj.setHolderName("Fulano da Silva");
		obj.setSecurityCode(Integer.MAX_VALUE);
		obj.setExpirationMonth(Integer.MAX_VALUE);
		obj.setExpirationYear(Integer.MAX_VALUE);
		Assert.assertEquals("5480452215505109", obj.getNumber());
		Assert.assertEquals("Fulano da Silva", obj.getHolderName());
		Assert.assertEquals(Integer.MAX_VALUE, obj.getSecurityCode().intValue());
		Assert.assertEquals(Integer.MAX_VALUE, obj.getExpirationMonth().intValue());
		Assert.assertEquals(Integer.MAX_VALUE, obj.getExpirationYear().intValue());
	}
}
