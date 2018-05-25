package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.entities.enums.PaymentStatus;
import com.boletobancario.boletofacilsdk.model.entities.enums.PaymentType;

public class ChargeTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		Charge obj = new Charge();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getDescription());
		Assert.assertNull(obj.getReference());
		Assert.assertNull(obj.getAmount());
		Assert.assertNull(obj.getDueDate());
		Assert.assertNull(obj.getInstallments());
		Assert.assertNull(obj.getMaxOverdueDays());
		Assert.assertNull(obj.getFine());
		Assert.assertNull(obj.getInterest());
		Assert.assertNull(obj.getDiscount());
		Assert.assertNull(obj.getPayer());
		Assert.assertNull(obj.getBillingAddress());
		Assert.assertNull(obj.getNotifyPayer());
		Assert.assertNull(obj.getNotificationUrl());
		Assert.assertNull(obj.getReferralToken());
		Assert.assertNull(obj.getFeeSchemaToken());
		Assert.assertNull(obj.getSplitRecipient());
		Assert.assertNull(obj.getCreditCard());
		Assert.assertNull(obj.getPaymentTypes());
		Assert.assertNull(obj.getPaymentTypesAsString());
		Assert.assertNull(obj.getPaymentAdvance());
		Assert.assertNull(obj.getCode());
		Assert.assertNull(obj.getLink());
		Assert.assertNull(obj.getPayNumber());
		Assert.assertNull(obj.getCheckoutUrl());
		Assert.assertNull(obj.getBilletDetails());
		Assert.assertNull(obj.getPayments());
		Assert.assertNull(obj.getCreditCardHash());

		obj.setDescription("Charge description");
		obj.setReference("Reference number");
		obj.setAmount(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setDueDate(getTodayDate());
		obj.setInstallments(Integer.MAX_VALUE);
		obj.setMaxOverdueDays(Integer.MAX_VALUE);
		obj.setFine(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setInterest(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setDiscount(new Discount());
		obj.setPayer(new Payer());
		obj.setBillingAddress(new Address());
		obj.setNotifyPayer(true);
		obj.setNotificationUrl("http://www.notification.br/url");
		obj.setReferralToken("ABC123");
		obj.setFeeSchemaToken("XPTO7890");
		obj.setSplitRecipient("123.456.789-00");
		obj.setCreditCard(new CreditCard());
		obj.setPaymentTypes("BOLETO,CREDIT_CARD");
		obj.setPaymentAdvance(true);
		obj.setCode("11223344");
		obj.setLink("https://www.boletobancario.com/link");
		obj.setPayNumber("23700.123456.789123.546543.79810000012345");
		obj.setCheckoutUrl("https://www.boletobancario.com/checkout");
		obj.setBilletDetails(new BilletDetails());
		obj.setPayments(new ArrayList<Payment>());
		obj.setCreditCardHash("HASH11223344");

		Assert.assertEquals("Charge description", obj.getDescription());
		Assert.assertEquals("Reference number", obj.getReference());
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getAmount());
		Assert.assertEquals(getTodayDate(), obj.getDueDate());
		Assert.assertEquals(Integer.MAX_VALUE, obj.getInstallments().intValue());
		Assert.assertEquals(Integer.MAX_VALUE, obj.getMaxOverdueDays().intValue());
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getFine());
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getInterest());
		Assert.assertNotNull(obj.getDiscount());
		Assert.assertNotNull(obj.getPayer());
		Assert.assertNotNull(obj.getBillingAddress());
		Assert.assertNotNull(obj.getNotifyPayer());
		Assert.assertTrue(obj.getNotifyPayer());
		Assert.assertEquals("http://www.notification.br/url", obj.getNotificationUrl());
		Assert.assertEquals("ABC123", obj.getReferralToken());
		Assert.assertEquals("XPTO7890", obj.getFeeSchemaToken());
		Assert.assertEquals("123.456.789-00", obj.getSplitRecipient());
		Assert.assertNotNull(obj.getCreditCard());
		Assert.assertEquals(getPaymentList(), obj.getPaymentTypes());
		Assert.assertEquals("BOLETO,CREDIT_CARD", obj.getPaymentTypesAsString());
		Assert.assertTrue(obj.getPaymentAdvance());
		Assert.assertEquals("11223344", obj.getCode());
		Assert.assertEquals("https://www.boletobancario.com/link", obj.getLink());
		Assert.assertEquals("23700.123456.789123.546543.79810000012345", obj.getPayNumber());
		Assert.assertEquals("https://www.boletobancario.com/checkout", obj.getCheckoutUrl());
		Assert.assertNotNull(obj.getBilletDetails());
		Assert.assertNotNull(obj.getPayments());
		Assert.assertEquals("HASH11223344", obj.getCreditCardHash());
	}

	@Test
	public void toStringTest() {
		Charge obj = new Charge();

		obj.setDescription("Charge description");
		obj.setReference("Reference number");
		obj.setAmount(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setDueDate(getDate(2018, 2, 17));
		obj.setInstallments(Integer.MAX_VALUE);
		obj.setMaxOverdueDays(Integer.MAX_VALUE);
		obj.setFine(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setInterest(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setDiscount(new Discount());
		obj.setPayer(new Payer());
		obj.setBillingAddress(new Address());
		obj.setNotifyPayer(true);
		obj.setNotificationUrl("http://www.notification.br/url");
		obj.setReferralToken("ABC123");
		obj.setFeeSchemaToken("XPTO7890");
		obj.setSplitRecipient("123.456.789-00");
		obj.setCreditCard(new CreditCard());
		obj.setPaymentTypes("BOLETO,CREDIT_CARD");
		obj.setPaymentAdvance(true);
		obj.setCode("11223344");
		obj.setLink("https://www.boletobancario.com/link");
		obj.setPayNumber("23700.123456.789123.546543.79810000012345");
		obj.setCheckoutUrl("https://www.boletobancario.com/checkout");
		obj.setCreditCardHash("HASH11223344");

		BilletDetails billetDetails = new BilletDetails();
		billetDetails.setBankAccount("1234-0 / 9438905");
		billetDetails.setOurNumber("000010083241 5");
		billetDetails.setBarcodeNumber("03393744100000176459694818900001008324150102");
		billetDetails.setPortfolio("CARTEIRA DE COB.");
		obj.setBilletDetails(billetDetails);

		obj.setPayments(new ArrayList<Payment>());
		Payment payment1 = new Payment();
		payment1.setId(Long.MAX_VALUE);
		payment1.setAmount(BigDecimal.valueOf(Double.MAX_VALUE));
		payment1.setDate(getDate(2018, 2, 17));
		payment1.setFee(BigDecimal.valueOf(Double.MAX_VALUE));
		payment1.setType(PaymentType.CREDIT_CARD);
		payment1.setStatus(PaymentStatus.DECLINED);
		obj.getPayments().add(payment1);
		Payment payment2 = new Payment();
		payment2.setId(Long.MAX_VALUE);
		payment2.setAmount(BigDecimal.valueOf(Double.MAX_VALUE));
		payment2.setDate(getDate(2018, 2, 17));
		payment2.setFee(BigDecimal.valueOf(Double.MAX_VALUE));
		payment2.setType(PaymentType.BOLETO);
		payment2.setStatus(PaymentStatus.CONFIRMED);
		obj.getPayments().add(payment2);

		// @formatter:off
        Assert.assertEquals("description: Charge description" + newLine() +
	                        "reference: Reference number" + newLine() +
	                        "amount: 1.7976931348623157E+308" + newLine() +
	                        "dueDate: 17/02/2018" + newLine() +
	                        "installments: 2147483647" + newLine() +
	                        "maxOverdueDays: 2147483647" + newLine() +
	                        "fine: 1.7976931348623157E+308" + newLine() +
	                        "interest: 1.7976931348623157E+308" + newLine() +
	                        "discount: amount: " + newLine() +
	                        "days: " + newLine() + newLine() +
	                        "payer: email: " + newLine() +
	                        "secondaryEmail: " + newLine() +
	                        "phone: " + newLine() +
	                        "name: " + newLine() +
	                        "cpfCnpj: " + newLine() +
	                        "birthDate: " + newLine() + newLine() +
	                        "billingAddress: street: " + newLine() +
	                        "number: " + newLine() +
	                        "complement: " + newLine() +
	                        "neighborhood: " + newLine() +
	                        "city: " + newLine() +
	                        "state: " + newLine() +
	                        "postcode: " + newLine() + newLine() +
	                        "notifyPayer: true" + newLine() +
	                        "notificationUrl: http://www.notification.br/url" + newLine() +
	                        "referralToken: ABC123" + newLine() +
	                        "feeSchemaToken: XPTO7890" + newLine() +
	                        "splitRecipient: 123.456.789-00" + newLine() +
	                        "paymentTypes: [" + newLine() +
	                        "BOLETO," + newLine() +
	                        "CREDIT_CARD] " + newLine() +
	                        "creditCard: number: " + newLine() +
	                        "holderName: " + newLine() +
	                        "securityCode: " + newLine() +
	                        "expirationMonth: " + newLine() +
	                        "expirationYear: " + newLine() + newLine() +
	                        "paymentAdvance: true" + newLine() +
	                        "creditCardHash: HASH11223344" + newLine() +
	                        "code: 11223344" + newLine() +
	                        "link: https://www.boletobancario.com/link" + newLine() +
	                        "payNumber: 23700.123456.789123.546543.79810000012345" + newLine() +
	                        "checkoutUrl: https://www.boletobancario.com/checkout" + newLine() +
	                        "billetDetails: bankAccount: 1234-0 / 9438905" + newLine() +
	                        "ourNumber: 000010083241 5" + newLine() +
	                        "barcodeNumber: 03393744100000176459694818900001008324150102" + newLine() +
	                        "portfolio: CARTEIRA DE COB." + newLine() + newLine() +
	                        "payments: [" + newLine() +
	                        "id: 9223372036854775807" + newLine() +
	                        "amount: 1.7976931348623157E+308" + newLine() +
	                        "date: 17/02/2018" + newLine() +
	                        "fee: 1.7976931348623157E+308" + newLine() +
	                        "type: CREDIT_CARD" + newLine() +
	                        "status: DECLINED" + newLine() +
	                        "," + newLine() +
	                        "id: 9223372036854775807" + newLine() +
	                        "amount: 1.7976931348623157E+308" + newLine() +
	                        "date: 17/02/2018" + newLine() +
	                        "fee: 1.7976931348623157E+308" + newLine() +
	                        "type: BOLETO" + newLine() +
	                        "status: CONFIRMED" + newLine() +
	                        "] " + newLine(), obj.toString());
        // @formatter:on
	}

	private List<PaymentType> getPaymentList() {
		List<PaymentType> types = new ArrayList<>();
		types.add(PaymentType.BOLETO);
		types.add(PaymentType.CREDIT_CARD);
		return types;
	}
}
