package com.boletobancario.boletofacilsdk.model.entities.enums;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class PaymentTypeTest extends AbstractTest {
	@Test
	public void valueOf() {
		Assert.assertEquals(PaymentType.BOLETO, PaymentType.valueOf("BOLETO"));
		Assert.assertEquals(PaymentType.CREDIT_CARD, PaymentType.valueOf("CREDIT_CARD"));
		Assert.assertEquals(PaymentType.INSTALLMENT_CREDIT_CARD, PaymentType.valueOf("INSTALLMENT_CREDIT_CARD"));
	}
}
