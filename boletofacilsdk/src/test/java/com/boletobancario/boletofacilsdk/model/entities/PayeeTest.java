package com.boletobancario.boletofacilsdk.model.entities;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.entities.enums.Category;
import com.boletobancario.boletofacilsdk.model.entities.enums.CompanyType;

public class PayeeTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		Payee obj = new Payee();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getNotificationUrl());
		Assert.assertNull(obj.getEmail());
		Assert.assertNull(obj.getPassword());
		Assert.assertNull(obj.getPhone());
		Assert.assertNull(obj.getLinesOfBusiness());
		Assert.assertNull(obj.getTradingName());
		Assert.assertNull(obj.getRepr());
		Assert.assertNull(obj.getAccountHolder());
		Assert.assertNull(obj.getBankAccount());
		Assert.assertNull(obj.getCategory());
		Assert.assertNull(obj.getCompanyType());
		Assert.assertNull(obj.getAddress());
		Assert.assertNull(obj.getBusinessAreaId());
		Assert.assertNull(obj.getEmailOptOut());
		Assert.assertNull(obj.getAutoApprove());
		Assert.assertNull(obj.getToken());
		Assert.assertNull(obj.getStatus());

		obj.setNotificationUrl("http://www.notification.br/url");
		obj.setEmail("email@email.com");
		obj.setPassword("Pass1word!");
		obj.setPhone("(11) 99876-5432");
		obj.setLinesOfBusiness("Areas de atuacao da empresa");
		obj.setTradingName("Nome fantasia");
		obj.setRepr(new Person());
		obj.setAccountHolder(new Person());
		obj.setBankAccount(new BankAccount());
		obj.setCategory(Category.OTHER);
		obj.setCompanyType(CompanyType.LTDA);
		obj.setAddress(new Address());
		obj.setBusinessAreaId(Integer.MAX_VALUE);
		obj.setEmailOptOut(true);
		obj.setAutoApprove(false);
		obj.setToken("TOKEN12345");
		obj.setStatus("Approved");

		Assert.assertEquals("http://www.notification.br/url", obj.getNotificationUrl());
		Assert.assertEquals("email@email.com", obj.getEmail());
		Assert.assertEquals("Pass1word!", obj.getPassword());
		Assert.assertEquals("(11) 99876-5432", obj.getPhone());
		Assert.assertEquals("Areas de atuacao da empresa", obj.getLinesOfBusiness());
		Assert.assertEquals("Nome fantasia", obj.getTradingName());
		Assert.assertNotNull(obj.getRepr());
		Assert.assertNotNull(obj.getAccountHolder());
		Assert.assertNotNull(obj.getBankAccount());
		Assert.assertEquals(Category.OTHER, obj.getCategory());
		Assert.assertEquals(CompanyType.LTDA, obj.getCompanyType());
		Assert.assertNotNull(obj.getAddress());
		Assert.assertEquals(Integer.MAX_VALUE, obj.getBusinessAreaId().intValue());
		Assert.assertNotNull(obj.getEmailOptOut());
		Assert.assertTrue(obj.getEmailOptOut());
		Assert.assertNotNull(obj.getAutoApprove());
		Assert.assertFalse(obj.getAutoApprove());
		Assert.assertEquals("TOKEN12345", obj.getToken());
		Assert.assertEquals("Approved", obj.getStatus());
	}
}
