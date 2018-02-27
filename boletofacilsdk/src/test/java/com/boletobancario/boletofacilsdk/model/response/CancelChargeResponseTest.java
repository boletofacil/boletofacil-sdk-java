package com.boletobancario.boletofacilsdk.model.response;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class CancelChargeResponseTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		CancelChargeResponse obj = new CancelChargeResponse();

		Assert.assertNotNull(obj);
	}
}
