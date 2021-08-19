package com.product.discountservice.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Schema(name = "id", description = "Unique identifier assigned to each order", example = "123", hidden = true)
	private String id;
	@Schema(name = "totalcost", description = "Total Cost of the Order after applying discount", example = "$40.0")
	private String totalCost;
	@Schema(name = "number of items", description = "Number of items in the Order", example = "3")
	private int numOfItems;
	@Default
	private List<Item> items = new ArrayList<>();
	@Schema(name = "discount", description = "discount amount applied", example = "$10.0")
	private String discount;

}
