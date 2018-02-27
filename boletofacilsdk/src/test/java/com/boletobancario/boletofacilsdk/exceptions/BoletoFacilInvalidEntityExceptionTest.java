package com.boletobancario.boletofacilsdk.exceptions;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.security.InvalidParameterException;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.ModelBase;
import com.boletobancario.boletofacilsdk.model.entities.Person;

public class BoletoFacilInvalidEntityExceptionTest extends AbstractTest {

	@Test
	public void constructor2() {
		ModelBase entity = new Person();
		try {
			throw new BoletoFacilInvalidEntityException(entity);
		} catch (Exception ex) {
			Assert.assertThat(ex, instanceOf(BoletoFacilInvalidEntityException.class));
			Assert.assertEquals("Person inválido.", ex.getMessage());
			Assert.assertNull(ex.getCause());
		}
	}

	@Test
	public void constructor3() {
		ModelBase entity = new Person();
		try {
			try {
				throw new InvalidParameterException("Exceção interna");
			} catch (Exception inner) {
				throw new BoletoFacilInvalidEntityException(entity, inner);
			}
		} catch (Exception ex) {
			Assert.assertThat(ex, instanceOf(BoletoFacilInvalidEntityException.class));
			Assert.assertEquals("Person inválido.", ex.getMessage());
			Assert.assertNotNull(ex.getCause());
			Assert.assertThat(ex.getCause(), instanceOf(InvalidParameterException.class));
		}
	}

}
