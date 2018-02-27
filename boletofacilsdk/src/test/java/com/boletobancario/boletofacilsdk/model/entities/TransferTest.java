package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class TransferTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		Transfer obj = new Transfer();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getAmount());

		obj.setAmount(BigDecimal.valueOf(Double.MAX_VALUE));
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getAmount());
	}

}
