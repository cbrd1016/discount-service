package com.product.discountservice.data.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	@Schema(name = "id", description = "Unique identifier assigned to each Item", example = "123")
	private Long id;
	@Schema(name = "cost", description = "Item cost", example = "50.0")
	private Double cost;
	@Schema(name = "name", description = "Item name", example = "shirt")
	private String name;
	@Schema(name = "type", description = "Item Type", example = "clothes", hidden = true)
	private ItemType type;

}
