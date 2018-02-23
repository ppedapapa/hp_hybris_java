package com.shaklee.rulesets.healthQuestionaire.components;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaklee.DAO.ProductPriceDAO;
import com.shaklee.entity.Product;
import com.shaklee.healthPrint.data.Bundle;
import com.shaklee.healthPrint.data.SKU;
import com.shaklee.promo.Action;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.promo.basic.PromoActionUtils;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.data.Language;

@Component
public class AddMembershipSku extends AbstractComponent<PromoRequest<Questions>>
		implements Action<PromoRequest<Questions>> {

	@NotNull
	/**
	 * Default to all bundles
	 */
	public List<Bundle> bundle = Bundle.ALL_TIERS;;

	public String category;

	@Autowired
	ProductPriceDAO productDAO;

	@Override
	public void exec(PromoRequest<Questions> r) {
		Questions q = r.request;
		if (q.country_code == null || q.language == null)
			return;

		Product p = productDAO.getMembershipSku(q.country_code, Language.valueOf(q.language));
		if( p != null)
		{
			for (Bundle b : bundle) {
				JoinSKUAction s = new JoinSKUAction();
				s.item_sku = p.code;
				s.bundle = b;
				s.category = category;
				BigDecimal sn_price = p.getPriceByPriceTier("SN");
				// TODO: Remove below code after Hybris has prices for the skus
				if (sn_price == null) sn_price = new BigDecimal("0");
				s.sn_price = new BigDecimal(sn_price.floatValue()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
				PromoActionUtils.addAction(r, ruleset, s);
			}
		}
	}

	private static class JoinSKUAction extends SKUs<Object> {
		public BigDecimal sn_price;
		public String item_sku;

		@SuppressWarnings("unchecked")
		@Override
		public void addTo(Collection skus) {
			JoinSKU item = new JoinSKU();
			item.sku = item_sku;
			item.category = this.category;
			item.sn_price = sn_price;
			skus.add(item);
		}
	}

	public static class JoinSKU extends SKU {
		public boolean membership = true;
		public BigDecimal sn_price;
	}
}
