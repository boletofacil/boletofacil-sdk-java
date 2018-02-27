package com.boletobancario.boletofacilsdk.model.entities.enums;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class BankAccountTypeTest extends AbstractTest {
	@Test
	public void valueOf() {
		Assert.assertEquals(BankAccountType.CHECKING, BankAccountType.valueOf("CHECKING"));
		Assert.assertEquals(BankAccountType.SAVINGS, BankAccountType.valueOf("SAVINGS"));
	}
}
