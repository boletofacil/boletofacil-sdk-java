package com.boletobancario.boletofacilsdk.model.response;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class TransferResponseTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		TransferResponse obj = new TransferResponse();

		Assert.assertNotNull(obj);
	}
}
