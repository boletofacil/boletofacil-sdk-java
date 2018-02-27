package com.boletobancario.boletofacilsdk.enums;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class BoletoFacilEnvironmentTest extends AbstractTest {
	@Test
	public void valueOf() {
		Assert.assertEquals(BoletoFacilEnvironment.PRODUCTION, BoletoFacilEnvironment.valueOf("PRODUCTION"));
		Assert.assertEquals(BoletoFacilEnvironment.SANDBOX, BoletoFacilEnvironment.valueOf("SANDBOX"));
		Assert.assertEquals(BoletoFacilEnvironment.UNIT_TESTS, BoletoFacilEnvironment.valueOf("UNIT_TESTS"));
	}
}
