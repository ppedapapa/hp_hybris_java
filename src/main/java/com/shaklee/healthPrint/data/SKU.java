package com.shaklee.healthPrint.data;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SKU {
	public String sku;
	public String category;
	public List<String> sub_sku;
	public List<String> goal;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SKU)
			return ((SKU) obj).sku.equals(sku);

		return false;
	}

	@Override
	public int hashCode() {
		return sku.hashCode();
	}

	@Override
	public String toString() {
		return sku;
	}
}