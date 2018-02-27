package com.boletobancario.boletofacilsdk.enums;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class ResponseTypeTest extends AbstractTest {
	@Test
	public void valueOf() {
		Assert.assertEquals(ResponseType.JSON, ResponseType.valueOf("JSON"));
		Assert.assertEquals(ResponseType.XML, ResponseType.valueOf("XML"));
	}
}
