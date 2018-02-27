package com.boletobancario.boletofacilsdk.enums;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class RequestTypeTest extends AbstractTest {
	@Test
	public void valueOf() {
		Assert.assertEquals(RequestType.ISSUE_CHARGE, RequestType.valueOf("ISSUE_CHARGE"));
		Assert.assertEquals(RequestType.REQUEST_TRANSFER, RequestType.valueOf("REQUEST_TRANSFER"));
		Assert.assertEquals(RequestType.LIST_CHARGES, RequestType.valueOf("LIST_CHARGES"));
		Assert.assertEquals(RequestType.FETCH_BALANCE, RequestType.valueOf("FETCH_BALANCE"));
		Assert.assertEquals(RequestType.CANCEL_CHARGE, RequestType.valueOf("CANCEL_CHARGE"));
		Assert.assertEquals(RequestType.CREATE_PAYEE, RequestType.valueOf("CREATE_PAYEE"));
		Assert.assertEquals(RequestType.CREATE_PAYEE_FEE_SCHEMA, RequestType.valueOf("CREATE_PAYEE_FEE_SCHEMA"));
		Assert.assertEquals(RequestType.GET_PAYEE_STATUS, RequestType.valueOf("GET_PAYEE_STATUS"));
	}
}
