package com.boletobancario.boletofacilsdk.model.entities;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class PersonTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		Person obj = new Person();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getName());
		Assert.assertNull(obj.getCpfCnpj());
		Assert.assertNull(obj.getBirthDate());

		obj.setName("Nome da pessoa");
		obj.setCpfCnpj("11.222.333/0001-99");
		obj.setBirthDate(getTodayDate());
		Assert.assertEquals("Nome da pessoa", obj.getName());
		Assert.assertEquals("11.222.333/0001-99", obj.getCpfCnpj());
		Assert.assertEquals(getTodayDate(), obj.getBirthDate());
	}
}
