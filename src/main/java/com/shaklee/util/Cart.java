package com.shaklee.util;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.shaklee.promo.AbstractLocalizedData;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Data object for US/CAN promo request, which is mostly a shopping cart.
 * 
 * @author Elli Albek
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Cart extends AbstractLocalizedData {

	/** can be null during sponsor flow **/
	// @ShakleeID
	// public String user_id;

	// @NotNull
	// @OneOf(values = { "US", "CA" }, message = "Only US and CA")
	// public String country_code;

	/** iso_639_1 two letter language code **/
	@Size(min = 2, max = 2, message = "Only ISO 639-1 two letter language code")
	public String language;

	/** Order total in the currency of the country in region */
	public BigDecimal cart_total;
	public String pws_domain;
	public Integer pws_theme_id;

	public OrderSource order_source;

	@NotNull
	public Client client;
	public DeliveryMethod delivery_method;
	public User user;

	public PageType page_type;
	public String page_type_id;
	public String join_type;
	public Boolean is_dropship;

	public List<Item> authship_cart;
	public List<Item> cart;

	public String referrer_type;
	public PriceTier price_tier;

	@Size(min = 1)
	public List<String> opt_out_promos;
	public String referrer_code;
	public String email;

	/**
	 * Request the promo engine to add a specific promotion. The engine may
	 * reject it based on other conditions (such as the user can only get the
	 * promo once).
	 */
	@Size(min = 1)
	public List<RequestPromo> request_promos;

	/**
	 * Health Profile ID
	 */
	public String hp_profile_id;

	/**
	 * If this flag is true, get the SKU prices from DB. There is a bug in PHP
	 * where user gets %10 autoship discount but they should not. This is work
	 * around to tell the promo engine that don't use line item prices in the
	 * given request because they are NOT correct prices. This is NOT a solution
	 * but PHP will not fix the root cause of issue so this is a work around of
	 * it.
	 */
	public boolean revert_autoship_discount;

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class SubItem {
		@NotNull
		public String sku;
		@NotNull
		public int quantity;
		
		public BigDecimal item_price;
		public int frequency;
		public Boolean is_discounted_item;
		public String selected_promo_code;
		
		public Float pv, ipv, qpv, spv;
	}

	/**
	 * Work around the lack of recursion in Jackson
	 **/
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class Item extends SubItem {
		public List<SubItem> sub_items;
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class User {
		// public String lead_source;
		// public String join_date;
		@NotNull
		public Boolean is_bl;
		@NotNull
		public Integer rank_id;
	}

	public static class RequestPromo {
		@NotNull
		public String promo_code;
		// this really need to be an object, but since current promos always use
		// a price, we can hard code decimal now and relax later.
		public BigDecimal extra_1;
	}

	public static enum PriceTier {
		SN, DN, MN, SRP;
	}

	public static enum OrderSource {
		AUTOSHIP(7200), JOIN(7201), SHOPPING(7202), UPGRADE(7205), PICKUP(7206);

		public final int db_status_code;

		private OrderSource(int db_status_code) {
			this.db_status_code = db_status_code;
		}
	}

	public static enum DeliveryMethod {
		STANDARD, PRIORITY, TWO_DAY, THREE_DAY, OVERNIGHT;
	}

	public static enum Client {
		MEMBER_CENTER, MOBILE, AUTOSHIP, SSOE, PWS, LA_CENTER;
	}

	public static enum PageType {
		PRODUCT, CATEGORY, CART, REVIEW, CONFIRMATION, JOIN_OPTIONS, UPGRADE_CART, UPGRADE_OPTIONS, MANAGE_AUTOSHIP, BILLING;
	}
}