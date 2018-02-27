package com.boletobancario.boletofacilsdk.model.entities;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.entities.enums.BankAccountType;

public class BankAccountTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		BankAccount obj = new BankAccount();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getBankNumber());
		Assert.assertNull(obj.getAgencyNumber());
		Assert.assertNull(obj.getAccountNumber());
		Assert.assertNull(obj.getBankAccountType());
		Assert.assertNull(obj.getAccountComplementNumber());

		obj.setBankNumber("237");
		obj.setAgencyNumber("123");
		obj.setAccountNumber("4567");
		obj.setBankAccountType(BankAccountType.SAVINGS);
		obj.setAccountComplementNumber(Integer.MAX_VALUE);
		Assert.assertEquals("237", obj.getBankNumber());
		Assert.assertEquals("123", obj.getAgencyNumber());
		Assert.assertEquals("4567", obj.getAccountNumber());
		Assert.assertEquals(BankAccountType.SAVINGS, obj.getBankAccountType());
		Assert.assertEquals(Integer.MAX_VALUE, obj.getAccountComplementNumber().intValue());
	}
}
