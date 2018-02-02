package com.shaklee.rulesets.healthQuestionaire.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.healthPrint.data.HPRequest;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.healthPrint.data.SKUList;
import com.shaklee.promo.Action;

/**
 * 
 * @author Elli Albek
 */
@Component
public class LoadSubSkus implements Action<HPRequest<?, ?, SKU>> {

	//@Autowired
	//ProductPriceDAO dao;

	/** required for json serializer **/
	public boolean blah;

	@Override
	public void exec(HPRequest<?, ?, SKU> request) {
		if (request.bundles == null)
			return;

		// final Map<String, List<String>> packs = dao.getPacks();
		final Map<String, List<String>> packs = null;
		for (SKUList<?, SKU> skList : request.bundles) {
			for (SKU pack : skList.skus) {
				List<String> defaults = packs.get(pack.sku);
				// clone
				pack.sub_sku = cloneList(defaults);
			}
		}
	}

	static List<String> cloneList(List<String> l) {
		if (l == null || l.isEmpty())
			return null;

		return new ArrayList<String>(l);
	}
}