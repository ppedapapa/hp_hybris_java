package com.shaklee.promo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.shaklee.common.util.CollectionUtils;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.cart.Cart;
import com.shaklee.promo.basic.cart.Cart.Item;
import com.shaklee.promo.basic.cart.Cart.SubItem;

/**
 * Utility methods for objects that need to manipulate the cart, mostly SKUs.
 * 
 * @author Elli Albek
 */
public class CartUtils {

	/**
	 * Return all items in the cart that match the sku
	 */
	public static List<Cart.SubItem> getItems(Cart cart, String sku) {
		final List<Item> items = allItems(cart);
		return getItems(sku, items);
	}

	public static List<Cart.SubItem> getItems(String sku, final List<Item> items) {
		if (items == null)
			return null;

		ArrayList<Cart.SubItem> matches = new ArrayList<Cart.SubItem>(1);

		traverseItems(sku, items, matches);

		return matches.isEmpty() ? null : matches;
	}

	private static void traverseItems(String sku, final List<? extends SubItem> items,
			ArrayList<Cart.SubItem> matches) {
		final int size = items.size();
		for (int i = 0; i < size; i++) {
			final SubItem item = items.get(i);
			if (sku.equals(item.sku))
				matches.add(item);
			if (item instanceof Item) {
				Item parent = (Item) item;
				if (parent.sub_items != null)
					traverseItems(sku, parent.sub_items, matches);
			}
		}
	}

	/**
	 * Return all items in the cart that match the sku
	 */
	public static List<Cart.SubItem> getItems(Cart cart, Collection<String> skus) {
		final List<? extends Item> items = allItems(cart);
		if (items == null)
			return null;

		ArrayList<Cart.SubItem> matches = new ArrayList<Cart.SubItem>(items.size() / 2 + 1);

		traverseItems(skus, items, matches);

		return matches.isEmpty() ? null : matches;
	}

	public static List<Cart.SubItem> getItems(PromoRequest<Cart> request, Collection<String> skus) {
		if (request == null || request.request == null)
			return null;
		return getItems(request.request, skus);
	}

	public static List<Cart.SubItem> getItemWithSelectedCode(PromoRequest<Cart> request, String code) {
		if (request == null || request.request == null)
			return null;
		return getItemWithSelectedCode(request.request, code);
	}

	public static List<Cart.SubItem> getItemWithSelectedCode(Cart cart, String code) {
		final List<Item> items = allItems(cart);
		if (items == null)
			return null;

		ArrayList<Cart.SubItem> matches = new ArrayList<Cart.SubItem>(items.size() / 2 + 1);

		for (Item i : items) {
			if (i.selected_promo_code != null && code.equals(i.selected_promo_code))
				matches.add(i);
		}

		return matches.isEmpty() ? null : matches;
	}

	private static void traverseItems(Collection<String> sku, final List<? extends SubItem> items,
			ArrayList<Cart.SubItem> matches) {
		final int size = items.size();
		for (int i = 0; i < size; i++) {
			final SubItem item = items.get(i);
			if (sku.contains(item.sku))
				matches.add(item);
			if (item instanceof Item) {
				Item parent = (Item) item;
				if (parent.sub_items != null)
					traverseItems(sku, parent.sub_items, matches);
			}
		}
	}

	public static void sortByPrice(List<SubItem> items) {
		if (items != null && items.size() > 1)
			Collections.sort(items, priceComparator);
	}

	private static final Comparator<SubItem> priceComparator = new Comparator<Cart.SubItem>() {
		@Override
		public int compare(SubItem o1, SubItem o2) {
			return o1.item_price.compareTo(o2.item_price);
		}
	};

	public static List<Item> allItems(PromoRequest<Cart> request) {
		if (request.request == null)
			return null;
		return CollectionUtils.combine(request.request.cart, request.request.authship_cart);
	}

	public static List<Item> allItems(Cart cart) {
		return CollectionUtils.combine(cart.cart, cart.authship_cart);
	}

	/**
	 * Return the cart Items
	 * 
	 * @param isAutoship
	 *            tru, false or null for all items.
	 */
	public static List<Item> getItems(Cart cart, Boolean isAutoship) {
		if (isAutoship == null)
			return allItems(cart);

		if (isAutoship)
			return cart.authship_cart;
		return cart.cart;
	}

	public static float calcPVTotal(List<? extends SubItem> items) {
		float pv = 0;
		for (SubItem item : items) {
			if (item.qpv != null)
				pv += item.qpv;
		}
		return pv;
	}

	public static BigDecimal calTotalAmount(List<? extends SubItem> items) {
		BigDecimal total = new BigDecimal(0);
		for (SubItem item : items) {
			if (item.item_price != null)
				total = total.add(item.item_price.multiply(BigDecimal.valueOf(item.quantity)));
		}
		return total;
	}

	public static List<Item> getItems(PromoRequest<Cart> cart, Boolean isAutoship) {
		if (cart.request == null)
			return null;
		return getItems(cart.request, isAutoship);
	}
}