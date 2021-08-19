package com.product.discountservice.data.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Schema(name = "discount code", description = "Unique Code assigned to individual Discount", example = "ABC")
	@JsonProperty(value = "discountCode")
	private String code;
	@Schema(name = "percentage", description = "discount percentage", example = "10.0")
	private Double percentage;

}
