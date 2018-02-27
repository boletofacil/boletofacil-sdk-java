package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.entities.enums.PaymentStatus;
import com.boletobancario.boletofacilsdk.model.entities.enums.PaymentType;

public class PaymentTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		Payment obj = new Payment();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getId());
		Assert.assertNull(obj.getAmount());
		Assert.assertNull(obj.getDate());
		Assert.assertNull(obj.getFee());
		Assert.assertNull(obj.getType());
		Assert.assertNull(obj.getStatus());

		obj.setId(Long.MAX_VALUE);
		obj.setAmount(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setDate(getTodayDate());
		obj.setFee(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setType(PaymentType.CREDIT_CARD);
		obj.setStatus(PaymentStatus.DECLINED);
		Assert.assertEquals(Long.MAX_VALUE, obj.getId().longValue());
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getAmount());
		Assert.assertEquals(getTodayDate(), obj.getDate());
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getFee());
		Assert.assertEquals(PaymentType.CREDIT_CARD, obj.getType());
		Assert.assertEquals(PaymentStatus.DECLINED, obj.getStatus());
	}
}
