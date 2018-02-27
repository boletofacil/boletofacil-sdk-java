package com.boletobancario.boletofacilsdk.model.response;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;
import com.boletobancario.boletofacilsdk.model.entities.Charge;
import com.boletobancario.boletofacilsdk.model.entities.ChargeList;

public class ListChargesResponseTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		ListChargesResponse obj = new ListChargesResponse();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getData());

		obj.setData(new ChargeList());
		Assert.assertNotNull(obj.getData());

		obj.getData().setCharges(new ArrayList<Charge>());
		Assert.assertNotNull(obj.getData().getCharges());

		obj.getData().getCharges().add(new Charge());
		Assert.assertNotNull(obj.toJson());
	}
}
