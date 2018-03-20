package com.shaklee.healthPrint.data;

import java.util.Collection;

import com.shaklee.promo.LocalizedData;
import com.shaklee.promo.PromoRequest;

public class HPRequest<Q, BundleType, ItemListType> extends PromoRequest<Q> {

	public Collection<SKUList<BundleType, ItemListType>> bundles;
	public BundleType recommended;

	public HPRequest(Q questions, LocalizedData promoGroupIdentifier, Long time) {
		super(questions, promoGroupIdentifier, time);
	}
}