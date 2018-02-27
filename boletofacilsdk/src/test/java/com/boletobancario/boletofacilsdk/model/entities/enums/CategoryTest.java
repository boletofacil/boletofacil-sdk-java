package com.boletobancario.boletofacilsdk.model.entities.enums;

import org.junit.Assert;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.AbstractTest;

public class CategoryTest extends AbstractTest {
	@Test
	public void valueOf() {
		Assert.assertEquals(Category.RETAIL, Category.valueOf("RETAIL"));
		Assert.assertEquals(Category.FOOD, Category.valueOf("FOOD"));
		Assert.assertEquals(Category.HEALTH_AND_BEAUTY, Category.valueOf("HEALTH_AND_BEAUTY"));
		Assert.assertEquals(Category.SERVICES, Category.valueOf("SERVICES"));
		Assert.assertEquals(Category.TECHNOLOGY, Category.valueOf("TECHNOLOGY"));
		Assert.assertEquals(Category.ENTERTAINMENT, Category.valueOf("ENTERTAINMENT"));
		Assert.assertEquals(Category.ECOMMERCE, Category.valueOf("ECOMMERCE"));
		Assert.assertEquals(Category.OTHER, Category.valueOf("OTHER"));
	}
}
