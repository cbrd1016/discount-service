package com.product.discountservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.product.discountservice.data.model.Item;
import com.product.discountservice.data.model.ItemType;
import com.product.discountservice.data.model.Order;

@ExtendWith(MockitoExtension.class)
@Execution(ExecutionMode.SAME_THREAD)
public class DiscountServiceTest {

	@InjectMocks
	DiscountService discountService;

	@Test
	public void testApply10PercentDiscount_When_ItemIsShirt_AndTotalCost_IsLessThan_Hundered() throws Exception {
		Item shirt = Item.builder().id(123L).name("shirts").cost(50.0).type(ItemType.CLOTHES).build();
		List<Item> items = List.of(shirt);

		Order order = Order.builder().id(UUID.randomUUID().toString())
				.totalCost(String.valueOf(items.stream().mapToDouble(Item::getCost).sum())).items(items).build();

		assertEquals("$45.0", discountService.applyDiscount(order).getTotalCost());

	}

	@Test
	void testApply15PercentDiscount_When_TotalCost_IsGreaterThan_Hundered() {
		List<Item> items = new ArrayList<>();
		Item shirt = Item.builder().id(123L).name("shirts").cost(50.0).type(ItemType.CLOTHES).build();
		Item mobile = Item.builder().id(456L).name("mobile").cost(300.0).type(ItemType.ELECTRONICS).build();
		items.add(shirt);
		items.add(mobile);
		Order order = Order.builder().id(UUID.randomUUID().toString())
				.totalCost(String.valueOf(items.stream().mapToDouble(Item::getCost).sum())).items(items).build();
		assertEquals("$297.5", discountService.applyDiscount(order).getTotalCost());

	}

	@Test
	void testApply20PercentDiscount_When_ItemIdIsEqualFor2orMoreItemsAndTotalCost_IsLessThan_Hundered() {
		List<Item> items = new ArrayList<>();
		Item shirt1 = Item.builder().id(123L).name("shirts").cost(50.0).type(ItemType.CLOTHES).build();
		Item shirt2 = Item.builder().id(123L).name("shirts").cost(50.0).type(ItemType.CLOTHES).build();
		items.add(shirt1);
		items.add(shirt2);
		Order order = Order.builder().id(UUID.randomUUID().toString())
				.totalCost(String.valueOf(items.stream().mapToDouble(Item::getCost).sum())).items(items).build();
		assertEquals("$90.0", discountService.applyDiscount(order).getTotalCost());
	}

}
