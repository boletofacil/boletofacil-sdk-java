package com.boletobancario.boletofacilsdk.model.entities.enums;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class CompanyTypeTest extends AbstractTest {
	@Test
	public void valueOf() {
		Assert.assertEquals(CompanyType.MEI, CompanyType.valueOf("MEI"));
		Assert.assertEquals(CompanyType.EI, CompanyType.valueOf("EI"));
		Assert.assertEquals(CompanyType.EIRELI, CompanyType.valueOf("EIRELI"));
		Assert.assertEquals(CompanyType.LTDA, CompanyType.valueOf("LTDA"));
		Assert.assertEquals(CompanyType.INSTITUTION_NGO_ASSOCIATION,
		        CompanyType.valueOf("INSTITUTION_NGO_ASSOCIATION"));
	}
}
