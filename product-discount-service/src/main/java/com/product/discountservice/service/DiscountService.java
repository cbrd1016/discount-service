package com.product.discountservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.product.discountservice.data.model.Discount;
import com.product.discountservice.data.model.Item;
import com.product.discountservice.data.model.ItemType;
import com.product.discountservice.data.model.Order;

@Service
public class DiscountService {

	private static final String ABC = "ABC";
	private static final String FGH = "FGH";
	private static final String CDE = "CDE";
	public static Map<String, Discount> discounts;
	static {

		discounts = new HashMap<>();
		discounts.put(ABC, new Discount(ABC, 10.0));
		discounts.put(CDE, new Discount(CDE, 15.0));
		discounts.put(FGH, new Discount(FGH, 20.0));
	}

	public Order applyDiscount(Order order) {

		Double totalCost = Double.parseDouble(order.getTotalCost());
		Double totalCostAfteDiscount = totalCost;
		Double discountAmount = 0.0;
		if (totalCost > 100) {
			if (discounts.containsKey(CDE)) {
				totalCostAfteDiscount = DiscountServiceHelper.calculateDiscount(totalCost,
						discounts.get(CDE).getPercentage());
				discountAmount = DiscountServiceHelper.calculateDiscountPercentage(totalCost,
						discounts.get(CDE).getPercentage());
			}
		} else if (DiscountServiceHelper.checkOrderForMultipleItemsWithSameItemID(order)) {
			if (discounts.containsKey(FGH)) {
				totalCostAfteDiscount = DiscountServiceHelper.calculateDiscount(totalCost,
						discounts.get(FGH).getPercentage());
				discountAmount = DiscountServiceHelper.calculateDiscountPercentage(totalCost,
						discounts.get(FGH).getPercentage());
			}
		} else if (order.getItems().stream().map(Item::getType).allMatch(e -> e.equals(ItemType.CLOTHES))) {
			if (discounts.containsKey(ABC)) {
				totalCostAfteDiscount = DiscountServiceHelper.calculateDiscount(totalCost,
						discounts.get(ABC).getPercentage());
				discountAmount = DiscountServiceHelper.calculateDiscountPercentage(totalCost,
						discounts.get(ABC).getPercentage());
			}
		}
		order.setTotalCost("$" + totalCostAfteDiscount);
		order.setDiscount("$" + discountAmount);
		return order;

	}

	public Discount addDiscount(Discount discount) {
		return discounts.put(discount.getCode(), discount);

	}

	public void removeDiscount(String code) throws Exception {
		if (discounts.containsKey(code))
			discounts.remove(code);
		else
			throw new Exception("No discount found");

	}

	public List<Discount> getDiscounts() {
		return discounts.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
	}
}
