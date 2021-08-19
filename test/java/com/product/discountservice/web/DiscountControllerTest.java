package com.product.discountservice.web;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.product.discountservice.data.model.Discount;
import com.product.discountservice.data.model.Item;
import com.product.discountservice.data.model.ItemType;
import com.product.discountservice.data.model.Order;
import com.product.discountservice.service.DiscountService;

@WebMvcTest(DiscountController.class)
@Execution(ExecutionMode.SAME_THREAD)
public class DiscountControllerTest {

	private static final String VALID_ORDER_INPUT = "[\r\n" + "  {\r\n" + "    \"id\": 123,\r\n"
			+ "    \"cost\": 50,\r\n" + "    \"name\": \"shirt\"\r\n" + "  }\r\n" + "]";

	private static final String VALID_DISCOUNT_INPUT = "{\r\n" + "  \"percentage\": 10,\r\n"
			+ "  \"discountCode\": \"ABC\"\r\n" + "}";
	@Autowired
	MockMvc mvc;
	@MockBean
	DiscountService discountService;

	@Test
	void testApplyDiscountIsSuccess() throws Exception {

		Item shirt = Item.builder().id(123L).name("shirts").cost(45.0).type(ItemType.CLOTHES).build();
		List<Item> items = new ArrayList<>();
		items.add(shirt);
		Order mockOrder = Order.builder().id(UUID.randomUUID().toString())
				.totalCost(String.valueOf(items.stream().mapToDouble(Item::getCost).sum())).items(items).build();
		when(discountService.applyDiscount(any())).thenReturn(mockOrder);

		mvc.perform(post("/discount/apply").contentType(MediaType.APPLICATION_JSON).content(VALID_ORDER_INPUT))
				.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.totalCost", is("45.0")));

	}

	@Test
	void testAddDiscountIsSuccess() throws Exception {

		Discount mockDiscount = new Discount("ABC", 12.0);
		when(discountService.addDiscount(any())).thenReturn(mockDiscount);

		mvc.perform(put("/discount").contentType(MediaType.APPLICATION_JSON).content(VALID_DISCOUNT_INPUT))
				.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.percentage", is(12.0)))
				.andExpect(jsonPath("$.discountCode", is("ABC")));

	}

	@Test
	void testRemoveDiscountIsSuccess() throws Exception {
		doNothing().when(discountService).removeDiscount(anyString());
		mvc.perform(delete("/discount/ABC")).andExpect(status().isOk());

	}

}
