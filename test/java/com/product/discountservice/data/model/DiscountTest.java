package com.product.discountservice.data.model;

import org.junit.jupiter.api.Test;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class DiscountTest {

	@Test
	void getterSetter() {
		assertPojoMethodsFor(Discount.class).areWellImplemented();

	}

}
