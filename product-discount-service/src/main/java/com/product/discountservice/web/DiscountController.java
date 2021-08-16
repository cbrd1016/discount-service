package com.product.discountservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.discountservice.data.model.Discount;
import com.product.discountservice.data.model.Item;
import com.product.discountservice.data.model.ItemType;
import com.product.discountservice.data.model.Order;
import com.product.discountservice.service.DiscountService;
import com.product.discountservice.service.DiscountServiceHelper;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class DiscountController {
	@Autowired
	DiscountService discountService;

	@PostMapping(path = "/discount/apply", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(description = "The endpoint will expose a HTTP POST method for applying discount"
			+ " given order containg selected items", summary = "Return the Order details after applying discount")
	public Order applyDiscount(@RequestBody List<Item> items) {
		items.stream().forEach(e -> {
			e.setType(ItemType.getByValue(e.getName()));

		});

		return discountService.applyDiscount(DiscountServiceHelper.buildOrder(items));

	}

	@GetMapping("/discounts")
	@Operation(description = "The endpoint will expose a HTTP GET method fetch all discounts", summary = "Return list of all discounts")
	public List<Discount> getAllDiscounts() {
		return discountService.getDiscounts();

	}

	@PutMapping(path = "/discount")
	@Operation(description = "The endpoint will expose a HTTP PUT method to add Discounts", summary = "Return added discount")
	public Discount addDiscount(@RequestBody Discount discount) {
		Discount addedDiscount = discountService.addDiscount(discount);
		return addedDiscount;

	}

	@DeleteMapping("/discount/{code}")
	@Operation(description = "The endpoint will expose a HTTP Delete method delete an existing Discount")
	public void removeDiscount(@PathVariable String code) throws Exception {
		discountService.removeDiscount(code);

	}

}
