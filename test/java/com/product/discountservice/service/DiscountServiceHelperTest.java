package com.product.discountservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.product.discountservice.data.model.Item;
import com.product.discountservice.data.model.ItemType;
import com.product.discountservice.data.model.Order;

public class DiscountServiceHelperTest {
	@Test
	void testCalculateDiscount() {
		assertEquals(45.0, DiscountServiceHelper.calculateDiscount(50.0, 10.0));
		assertEquals(252.875, DiscountServiceHelper.calculateDiscount(297.5, 15.0));

	}

	@Test
	void testCheckOrderForMultipleItemsWithSameItemID() {
		Item shirt1 = Item.builder().id(123L).name("shirts").cost(50.0).type(ItemType.CLOTHES).build();
		Item shirt2 = Item.builder().id(123L).name("shirts").cost(50.0).type(ItemType.CLOTHES).build();
		Item shirt3 = Item.builder().id(123L).name("shirts").cost(50.0).type(ItemType.CLOTHES).build();
		List<Item> items = List.of(shirt1, shirt2, shirt3);
		Order myOrder = Order.builder().id(UUID.randomUUID().toString()).items(items).numOfItems(items.size())
				.totalCost(String.valueOf(items.stream().mapToDouble(Item::getCost).sum())).build();
		assertTrue(DiscountServiceHelper.checkOrderForMultipleItemsWithSameItemID(myOrder));
	}
}
