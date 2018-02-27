package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class PayeeBalanceTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		PayeeBalance obj = new PayeeBalance();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getBalance());
		Assert.assertNull(obj.getWithheldBalance());
		Assert.assertNull(obj.getTransferableBalance());

		obj.setBalance(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setWithheldBalance(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setTransferableBalance(BigDecimal.valueOf(Double.MAX_VALUE));
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getBalance());
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getWithheldBalance());
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getTransferableBalance());
	}

}
