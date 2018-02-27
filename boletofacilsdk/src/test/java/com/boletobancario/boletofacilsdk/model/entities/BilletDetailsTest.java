package com.boletobancario.boletofacilsdk.model.entities;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class BilletDetailsTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		BilletDetails obj = new BilletDetails();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getBankAccount());
		Assert.assertNull(obj.getOurNumber());
		Assert.assertNull(obj.getBarcodeNumber());
		Assert.assertNull(obj.getPortfolio());

		obj.setBankAccount("1234-0 / 9438905");
		obj.setOurNumber("000010083241 5");
		obj.setBarcodeNumber("03393744100000176459694818900001008324150102");
		obj.setPortfolio("CARTEIRA DE COB.");
		Assert.assertEquals("1234-0 / 9438905", obj.getBankAccount());
		Assert.assertEquals("000010083241 5", obj.getOurNumber());
		Assert.assertEquals("03393744100000176459694818900001008324150102", obj.getBarcodeNumber());
		Assert.assertEquals("CARTEIRA DE COB.", obj.getPortfolio());
	}
}
