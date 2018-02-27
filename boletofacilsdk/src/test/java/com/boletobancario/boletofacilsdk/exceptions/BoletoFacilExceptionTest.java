package com.boletobancario.boletofacilsdk.exceptions;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.security.InvalidParameterException;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class BoletoFacilExceptionTest extends AbstractTest {
	@Test
	public void constructor1() {
		try {
			throw new BoletoFacilException();
		} catch (Exception ex) {
			Assert.assertThat(ex, instanceOf(BoletoFacilException.class));
			Assert.assertNull(ex.getMessage());
			Assert.assertNull(ex.getCause());
		}
	}

	@Test
	public void constructor2() {
		String message = "Teste de exceção";
		try {
			throw new BoletoFacilException(message);
		} catch (Exception ex) {
			Assert.assertThat(ex, instanceOf(BoletoFacilException.class));
			Assert.assertEquals(message, ex.getMessage());
			Assert.assertNull(ex.getCause());
		}
	}

	@Test
	public void constructor3() {
		String message = "Teste de exceção";
		try {
			try {
				throw new InvalidParameterException("Exceção interna");
			} catch (Exception inner) {
				throw new BoletoFacilException(message, inner);
			}
		} catch (Exception ex) {
			Assert.assertThat(ex, instanceOf(BoletoFacilException.class));
			Assert.assertEquals(message, ex.getMessage());
			Assert.assertNotNull(ex.getCause());
			Assert.assertThat(ex.getCause(), instanceOf(InvalidParameterException.class));
		}
	}
}
