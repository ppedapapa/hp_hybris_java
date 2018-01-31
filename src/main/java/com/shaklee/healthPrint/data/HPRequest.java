package com.shaklee.healthPrint.data;

import java.util.Collection;

import com.shaklee.promo.LocalizedData;
import com.shaklee.promo.PromoRequest;

public class HPRequest<Q, BundleType, ItemListType> extends PromoRequest<Q> {

	public Collection<SKUList<BundleType, ItemListType>> bundles;
	public BundleType recommended;
	public HPScore score;

	public HPRequest(Q questions, LocalizedData promoGroupIdentifier, Long time) {
		super(questions, promoGroupIdentifier, time);
	}

	public static HPScore getScore(HPRequest<?, ?, ?> request) {
		// Lazy loading
		if (request.score == null) {
			request.score = new HPScore();
		}
		return request.score;
	}
}