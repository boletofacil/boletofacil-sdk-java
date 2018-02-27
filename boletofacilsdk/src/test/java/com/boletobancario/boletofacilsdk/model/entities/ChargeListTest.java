package com.boletobancario.boletofacilsdk.model.entities;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class ChargeListTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		ChargeList obj = new ChargeList();

		Assert.assertNotNull(obj);
		Assert.assertNull(obj.getCharges());

		obj.setCharges(new ArrayList<Charge>());
		Assert.assertNotNull(obj.getCharges());
	}

}
