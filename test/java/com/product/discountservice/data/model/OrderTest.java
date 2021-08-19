package com.product.discountservice.data.model;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.jupiter.api.Test;

public class OrderTest {
	
	@Test
	void getterSetter() {
		assertPojoMethodsFor(Order.class).areWellImplemented();

	}

}
