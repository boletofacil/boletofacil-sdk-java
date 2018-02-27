package com.boletobancario.boletofacilsdk.model.response;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class ErrorResponseTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		ErrorResponse obj = new ErrorResponse();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getErrorMessage());
		Assert.assertNull(obj.getSuccess());

		obj.setSuccess(false);
		obj.setErrorMessage("erro");
		Assert.assertEquals("erro", obj.getErrorMessage());
		Assert.assertFalse(obj.getSuccess());
	}
}
