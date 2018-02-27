package com.boletobancario.boletofacilsdk.model.entities.enums;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class PaymentStatusTest extends AbstractTest {
	@Test
	public void valueOf() {
		Assert.assertEquals(PaymentStatus.AUTHORIZED, PaymentStatus.valueOf("AUTHORIZED"));
		Assert.assertEquals(PaymentStatus.AWAITING_PROCESSING, PaymentStatus.valueOf("AWAITING_PROCESSING"));
		Assert.assertEquals(PaymentStatus.BANK_PAID_BACK, PaymentStatus.valueOf("BANK_PAID_BACK"));
		Assert.assertEquals(PaymentStatus.CONFIRMED, PaymentStatus.valueOf("CONFIRMED"));
		Assert.assertEquals(PaymentStatus.CUSTOMER_PAID_BACK, PaymentStatus.valueOf("CUSTOMER_PAID_BACK"));
		Assert.assertEquals(PaymentStatus.DECLINED, PaymentStatus.valueOf("DECLINED"));
		Assert.assertEquals(PaymentStatus.FAILED, PaymentStatus.valueOf("FAILED"));
		Assert.assertEquals(PaymentStatus.NOT_AUTHORIZED, PaymentStatus.valueOf("NOT_AUTHORIZED"));
	}
}
