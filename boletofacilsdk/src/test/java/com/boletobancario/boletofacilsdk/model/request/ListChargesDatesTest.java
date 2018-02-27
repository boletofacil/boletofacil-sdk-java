package com.boletobancario.boletofacilsdk.model.request;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class ListChargesDatesTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		ListChargesDates obj = new ListChargesDates();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getBeginDueDate());
		Assert.assertNull(obj.getEndDueDate());
		Assert.assertNull(obj.getBeginPaymentDate());
		Assert.assertNull(obj.getEndPaymentDate());

		obj.setBeginDueDate(getMinDate());
		obj.setEndDueDate(getMaxDate());
		obj.setBeginPaymentDate(getMinDate());
		obj.setEndPaymentDate(getMaxDate());
		Assert.assertEquals(getMinDate(), obj.getBeginDueDate());
		Assert.assertEquals(getMaxDate(), obj.getEndDueDate());
		Assert.assertEquals(getMinDate(), obj.getBeginPaymentDate());
		Assert.assertEquals(getMaxDate(), obj.getEndPaymentDate());
	}
}
