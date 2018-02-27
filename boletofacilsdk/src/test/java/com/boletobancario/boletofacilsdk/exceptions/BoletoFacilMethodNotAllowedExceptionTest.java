package com.boletobancario.boletofacilsdk.exceptions;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class BoletoFacilMethodNotAllowedExceptionTest extends AbstractTest {
	@Test
	public void constructor() {
		String message = "Teste de exceção";
		try {
			throw new BoletoFacilMethodNotAllowedException(message);
		} catch (Exception ex) {
			Assert.assertThat(ex, instanceOf(BoletoFacilMethodNotAllowedException.class));
			Assert.assertEquals(message, ex.getMessage());
			Assert.assertEquals(405, ((BoletoFacilMethodNotAllowedException) ex).getHttpStatusCode());
			Assert.assertNull(ex.getCause());
		}
	}
}
