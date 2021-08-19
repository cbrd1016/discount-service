package com.product.discountservice.data.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ItemType {

	CLOTHES(List.of("shirt", "trousers")), ELECTRONICS(List.of("tv", "mobile"));

	protected static Map<String, ItemType> enumMap = new HashMap<>();
	static {
		Arrays.stream(ItemType.values()).forEach(e -> {
			e.getCodes().stream().forEach(c -> {
				enumMap.put(c, e);
			});
		});
	}
	private List<String> types;

	public List<String> getCodes() {
		return types;
	}

	public static final ItemType getByValue(String name) {

		return enumMap.get(name);
	}

	ItemType(List<String> types) {
		this.types = types;
	}

}
