package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class DiscountTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		Discount obj = new Discount();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getAmount());
		Assert.assertNull(obj.getDays());

		obj.setAmount(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setDays(Integer.MAX_VALUE);
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getAmount());
		Assert.assertEquals(Integer.MAX_VALUE, obj.getDays().intValue());
	}
}
