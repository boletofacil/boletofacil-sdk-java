package com.boletobancario.boletofacilsdk.model.response;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.entities.PayeeBalance;

public class FetchBalanceResponseTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		FetchBalanceResponse obj = new FetchBalanceResponse();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getData());

		obj.setData(new PayeeBalance());
		Assert.assertNotNull(obj.getData());
	}
}
