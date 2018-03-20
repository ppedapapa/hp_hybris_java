package com.shaklee.healthPrint.data;

import java.util.List;

import com.shaklee.promo.LocalizedData;

/**
 * 
 * @author ekoca
 *
 * @param <Q>
 * @param <BundleType>
 * @param <ItemListType>
 */
public class HealthPrintContentRequest<Q, BundleType, ItemListType> extends HPRequest<Q, BundleType, ItemListType> {

	public List<Action> content;
	public HPScore score;

	public HealthPrintContentRequest(Q questions, LocalizedData promoGroupIdentifier, Long time) {
		super(questions, promoGroupIdentifier, time);
		// Lazy loading the score object for request
		this.score = new HPScore();
	}

	/**
	 * @deprecated don't use this
	 * 
	 * @param request
	 * @return
	 */
	public static HPScore getScore(HealthPrintContentRequest<?, ?, ?> request) {
		// Lazy loading
		if (request.score == null) {
			request.score = new HPScore();
		}
		return request.score;
	}
}