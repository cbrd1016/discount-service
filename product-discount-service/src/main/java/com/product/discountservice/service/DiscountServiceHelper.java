package com.product.discountservice.service;

import java.util.List;
import java.util.UUID;

import com.product.discountservice.data.model.Item;
import com.product.discountservice.data.model.Order;

public class DiscountServiceHelper {
	public static boolean checkOrderForMultipleItemsWithSameItemID(Order order) {

		int counter = 0;
		for (int i = 0; i < order.getItems().size(); i++) {
			if (order.getItems().get(i).getId() == 123) {
				counter++;
			}

		}
		if (counter > 1) {
			return true;

		}
		return false;
	}

	public static double calculateDiscount(Double totalCost, Double percentage) {
		Double discountPercentage = (totalCost * percentage) / 100;
		return totalCost - discountPercentage;

	}

	public static double calculateDiscountPercentage(Double totalCost, Double percentage) {
		return (totalCost * percentage) / 100;
	}

	public static Order buildOrder(List<Item> items) {
		return Order.builder().id(UUID.randomUUID().toString())
				.totalCost(String.valueOf(items.stream().mapToDouble(Item::getCost).sum())).items(items)
				.numOfItems(items.size()).build();
	}

}
