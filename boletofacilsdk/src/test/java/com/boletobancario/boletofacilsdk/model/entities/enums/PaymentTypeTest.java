package com.boletobancario.boletofacilsdk.model.entities.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class PaymentTypeTest extends AbstractTest {
	private List<PaymentType> boleto;
	private List<PaymentType> all;

	@Before
	public void setup() {
		boleto = new ArrayList<>();
		boleto.add(PaymentType.BOLETO);

		all = new ArrayList<>();
		all.addAll(Arrays.asList(PaymentType.values()));
	}

	@Test
	public void valueOf() {
		Assert.assertEquals(PaymentType.BOLETO, PaymentType.valueOf("BOLETO"));
		Assert.assertEquals(PaymentType.CREDIT_CARD, PaymentType.valueOf("CREDIT_CARD"));
		Assert.assertEquals(PaymentType.INSTALLMENT_CREDIT_CARD, PaymentType.valueOf("INSTALLMENT_CREDIT_CARD"));
	}

	@Test
	public void stringToPaymentTypeList() {
		Assert.assertEquals(boleto, PaymentType.stringToPaymentTypeList("BOLETO"));
		Assert.assertEquals(all, PaymentType.stringToPaymentTypeList("BOLETO,CREDIT_CARD,INSTALLMENT_CREDIT_CARD"));
		Assert.assertEquals(new ArrayList<>(), PaymentType.stringToPaymentTypeList(""));
		Assert.assertEquals(null, PaymentType.stringToPaymentTypeList(null));
	}

	@Test
	public void paymentTypeListToString() {
		Assert.assertEquals("BOLETO", PaymentType.paymentTypeListToString(boleto));
		Assert.assertTrue(PaymentType.paymentTypeListToString(all).contains("BOLETO")
				&& PaymentType.paymentTypeListToString(all).contains("CREDIT_CARD")
				&& PaymentType.paymentTypeListToString(all).contains("INSTALLMENT_CREDIT_CARD"));
		Assert.assertEquals(null, PaymentType.paymentTypeListToString(new ArrayList<PaymentType>()));
		Assert.assertEquals(null, PaymentType.paymentTypeListToString(null));
	}
}
