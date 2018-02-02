package com.shaklee.healthPrint.components;

import java.util.Collection;
import java.util.Collections;

import com.shaklee.healthPrint.data.SKUList;

public class BundleUtils {

	public static <BundleType, ItemListType> SKUList<BundleType, ItemListType> getBundle(
			Collection<SKUList<BundleType, ItemListType>> bundles, BundleType bundle) {
		for (SKUList<BundleType, ItemListType> skuList : bundles) {
			if (skuList.getBundle() == bundle)
				return skuList;
		}

		return null;
	}

	public static <BundleType, ItemListType> Collection<ItemListType> getSkus(
			Collection<SKUList<BundleType, ItemListType>> bundles, BundleType bundle) {
		SKUList<BundleType, ItemListType> skus = getBundle(bundles, bundle);
		if (skus != null && skus.skus != null)
			return skus.skus;
		return Collections.emptyList();
	}
}