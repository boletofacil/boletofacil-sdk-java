package com.boletobancario.boletofacilsdk.model.entities;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class AddressTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		Address obj = new Address();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getStreet());
		Assert.assertNull(obj.getNumber());
		Assert.assertNull(obj.getComplement());
		Assert.assertNull(obj.getNeighborhood());
		Assert.assertNull(obj.getCity());
		Assert.assertNull(obj.getState());
		Assert.assertNull(obj.getPostcode());

		obj.setStreet("Rua Teste");
		obj.setNumber("123");
		obj.setComplement("apto 11");
		obj.setNeighborhood("Centro");
		obj.setCity("Sao Paulo");
		obj.setState("SP");
		obj.setPostcode("01010-100");
		Assert.assertEquals("Rua Teste", obj.getStreet());
		Assert.assertEquals("123", obj.getNumber());
		Assert.assertEquals("apto 11", obj.getComplement());
		Assert.assertEquals("Centro", obj.getNeighborhood());
		Assert.assertEquals("Sao Paulo", obj.getCity());
		Assert.assertEquals("SP", obj.getState());
		Assert.assertEquals("01010-100", obj.getPostcode());
	}
}
