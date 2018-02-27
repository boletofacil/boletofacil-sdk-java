package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class SplitTest extends AbstractTest {
	@Test
	public void constructorAndAttributes() {
		Split obj = new Split();

		Assert.assertNotNull(obj);
		Assert.assertEquals(BigDecimal.ZERO, obj.getSplitFixed());
		Assert.assertEquals(BigDecimal.ZERO, obj.getSplitVariable());
		Assert.assertEquals(BigDecimal.ZERO, obj.getSplitTriggerAmount());

		obj.setSplitFixed(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setSplitVariable(BigDecimal.valueOf(Double.MAX_VALUE));
		obj.setSplitTriggerAmount(BigDecimal.valueOf(Double.MAX_VALUE));
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getSplitFixed());
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getSplitVariable());
		Assert.assertEquals(BigDecimal.valueOf(Double.MAX_VALUE), obj.getSplitTriggerAmount());
	}
}
