package com.boletobancario.boletofacilsdk.exceptions;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.security.InvalidParameterException;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.response.ErrorResponse;

public class BoletoFacilRequestExceptionTest extends AbstractTest {
	@Test
	public void constructor1() {
		String message = "Teste de exceção";
		try {
			throw new BoletoFacilRequestException(message);
		} catch (Exception ex) {
			Assert.assertThat(ex, instanceOf(BoletoFacilRequestException.class));
			Assert.assertEquals(message, ex.getMessage());
			Assert.assertNull(ex.getCause());
		}
	}

	@Test
	public void constructor2() {
		int httpStatusCode = 400;
		String message = "Teste de exceção";
		try {
			throw new BoletoFacilRequestException(httpStatusCode, message);
		} catch (Exception ex) {
			Assert.assertThat(ex, instanceOf(BoletoFacilRequestException.class));
			Assert.assertEquals(400, ((BoletoFacilRequestException) ex).getHttpStatusCode());
			Assert.assertNull(ex.getCause());
		}
	}

	@Test
	public void constructor3() {
		int httpStatusCode = 400;
		ErrorResponse response = new ErrorResponse();
		try {
			try {
				throw new InvalidParameterException("Exceção interna");
			} catch (Exception inner) {
				throw new BoletoFacilRequestException(httpStatusCode, response);
			}
		} catch (Exception ex) {
			Assert.assertThat(ex, instanceOf(BoletoFacilRequestException.class));
			Assert.assertEquals(400, ((BoletoFacilRequestException) ex).getHttpStatusCode());
			Assert.assertEquals(response, ((BoletoFacilRequestException) ex).getError());
			Assert.assertNull(ex.getCause());
		}
	}
}
