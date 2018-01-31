package com.shaklee.healthPrint.data;

import java.util.List;

import com.shaklee.promo.LocalizedData;

public class HealthPrintContentRequest<Q, BundleType, ItemListType> extends HPRequest<Q, BundleType, ItemListType> {

	public List<Action> content;

	public HealthPrintContentRequest(Q questions, LocalizedData promoGroupIdentifier, Long time) {
		super(questions, promoGroupIdentifier, time);
	}
}