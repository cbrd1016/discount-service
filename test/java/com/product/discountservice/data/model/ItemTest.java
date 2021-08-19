package com.product.discountservice.data.model;

import org.junit.jupiter.api.Test;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class ItemTest {

	@Test
	void getterSetter() {
		assertPojoMethodsFor(Item.class).areWellImplemented();

	}
}
