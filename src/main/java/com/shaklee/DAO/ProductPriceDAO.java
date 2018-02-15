package com.shaklee.DAO;

import static com.shaklee.shared.data.Language.en;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.shaklee.common.util.CollectionFunctions;
import com.shaklee.common.util.cache.CachingSingletonLoader;
import com.shaklee.common.util.cache.Loader;
import com.shaklee.common.util.cache.MultiCachingLoader;
import com.shaklee.common.util.collections.AbstractCollectionTransformDecorator;
import com.shaklee.entity.Product;
import com.shaklee.resources.HybrisProductService;
import com.shaklee.rulesets.healthQuestionaire.ProductSkuKey;
import com.shaklee.shared.dao.JdbcTemplateDAO;
import com.shaklee.shared.data.Country2;
import com.shaklee.shared.data.Language;

@Component
public class ProductPriceDAO {
	
	Logger logger = LoggerFactory.getLogger(ProductPriceDAO.class);

	
	@Autowired
	HybrisProductService hybrisProductModel;


	/** Cache two hours **/
	private static final int CACHE_EXPIRATION_SEC = (int) TimeUnit.HOURS.toSeconds(2);

	private final MultiCachingLoader<ProductSkuKey, Product> productCache;
	private final CachingSingletonLoader<Map<String, List<String>>> packCache;
	private final CachingSingletonLoader<Map<String, Product>> membershipSkuCache;
	private final CachingSingletonLoader<Set<String>> joinKitsCache;
	private final CachingSingletonLoader<Map<String, Product>> distributorKitSkuCache;

	@Autowired
	public ProductPriceDAO() {
		
		/**
		 * This is an inner class instead of implementing the interface to hide
		 * this method from external callers.
		 */
		productCache = new MultiCachingLoader<ProductSkuKey, Product>(
				new Loader<Collection<ProductSkuKey>, Map<ProductSkuKey, Product>>() {
					@Override
					public Map<ProductSkuKey, Product> get(Collection<ProductSkuKey> keys) {
						return getProducts(keys);
					}
				}, CACHE_EXPIRATION_SEC);

		packCache = new CachingSingletonLoader<Map<String, List<String>>>(
				new Loader<Object, Map<String, List<String>>>() {
					@Override
					public Map<String, List<String>> get(Object key) {
						return _getPacks();
					}
				}, CACHE_EXPIRATION_SEC, true);

		membershipSkuCache = new CachingSingletonLoader<Map<String, Product>>(
				new Loader<Object, Map<String, Product>>() {
					@Override
					public Map<String, Product> get(Object key) {
						return getMembershipSkus();
					}
				}, CACHE_EXPIRATION_SEC, true);

		joinKitsCache = new CachingSingletonLoader<Set<String>>(new Loader<Object, Set<String>>() {
			@Override
			public Set<String> get(Object key) {
				return _getJoinKits();
			}
		}, CACHE_EXPIRATION_SEC, true);
		
		distributorKitSkuCache = new CachingSingletonLoader<Map<String, Product>>(
				new Loader<Object, Map<String, Product>>() {
					@Override
					public Map<String, Product> get(Object key) {
						return getDistributorKitSkus();
					}
				}, CACHE_EXPIRATION_SEC, true);
	}

	private static final BeanPropertyRowMapper<Product> productMapper = BeanPropertyRowMapper
			.newInstance(Product.class);

	public Map<String, Product> get(final Country2 country, Collection<String> skus) {
		final CollectionFunctions.Function<String, ProductSkuKey> skuToKey = new CollectionFunctions.Function<String, ProductSkuKey>() {
			@Override
			public ProductSkuKey apply(String sku) {
				return new ProductSkuKey(country, sku);
			}
		};

		Collection<ProductSkuKey> keys = CollectionFunctions.map(skus, skuToKey);
		Map<ProductSkuKey, Product> productsByKey = get(keys);
		HashMap<String, Product> productsBySku = new HashMap<String, Product>(productsByKey.size());
		for (Map.Entry<ProductSkuKey, Product> e : productsByKey.entrySet()) {
			productsBySku.put(e.getKey().code, e.getValue());
		}

		return productsBySku;
	}

	public Map<ProductSkuKey, Product> get(Collection<ProductSkuKey> skus) {
		return productCache.get(skus);
	}

	private static final class ProductSkuCollection
			extends AbstractCollectionTransformDecorator<ProductSkuKey, String> {

		protected ProductSkuCollection(Collection<ProductSkuKey> origin) {
			super(origin);
		}

		@Override
		protected String convert(ProductSkuKey product) {
			return product.code;
		}
	}

	public Map<ProductSkuKey, Product> getProducts(Collection<ProductSkuKey> keys) {
		if (keys.isEmpty())
			return Collections.emptyMap();
		final Country2 country = keys.iterator().next().country;
		Collection<String> skus = new ProductSkuCollection(keys);
		List<Product> rs;
		try {
			rs = hybrisProductModel.getProducts( country.country3, skus);
		} catch (IOException | JSONException e) {
			
			logger.debug("error:" , e);
			
			return null;
		}
		HashMap<ProductSkuKey, Product> products = new HashMap<ProductSkuKey, Product>(rs.size());
		for (Product product : rs) {
			product.country = country;
			products.put(product, product);
		}

		return products;
	}

	/*
	public Map<String, Product> getDebug(final Country2 country, Collection<String> skus) {
		// Collection<String> skus = new ProductSkuCollection(keys);
		if (skus == null || skus.isEmpty())
			return Collections.emptyMap();

		final List<Product> rs = inQuery(getDebug, productMapper, country.country3, skus);
		HashMap<String, Product> products = new HashMap<String, Product>(rs.size());
		for (Product product : rs) {
			products.put(product.code, product);
		}

		return products;
	}*/

	public Map<String, List<String>> getPacks() {
		return packCache.get();
	}

	public Product getDistributorKitSku(String country, Language l) {
		final Map<String, Product> skus = distributorKitSkuCache.get();

		{
			String key = l.name() + '_' + country;
			Product sku = skus.get(key);
			if (sku != null)
				return sku;
		}
		if (l != Language.en) {
			// a language different than English, try English as default
			// language for North America.
			String enKey = en.name() + '_' + country;
			return skus.get(enKey);
		}
		return null;
	}

	private Map<String, List<String>> _getPacks() {
		/*final MultivaluedMapImpl packs = new MultivaluedMapImpl();
		jdbcTemplate.query(getPack, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				packs.add(rs.getString(1), rs.getString(2));
			}
		});*/
		return null;
	}

	Set<String> _getJoinKits() {
		//return new HashSet<String>(jdbcTemplate.query(getJoinKits, STRING_ROW_MAPPER));
		return null;
	}

	public Set<String> getJoinKits() {
		return joinKitsCache.get();
	}
	
	public Product getMembershipSku(String country, Language l) {
		final Map<String, Product> skus = membershipSkuCache.get();

		{
			String key = l.name() + '_' + country;
			Product sku = skus.get(key);
			if (sku != null)
				return sku;
		}
		if (l != Language.en) {
			// a language different than english, try english as default
			// language for north america.
			String enKey = en.name() + '_' + country;
			return skus.get(enKey);
		}
		return null;
	}

	Map<String, Product> getMembershipSkus() {
		final Map<String, Product> skus = new HashMap<String, Product>();
		/*
		jdbcTemplate.query(getMembershipSku, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Product p = new Product();
				p.sku = rs.getString(2);
				p.sn_price = rs.getFloat(3);
				skus.put(rs.getString(1).trim(), p);
			}
		});*/
		return skus;
	}
	
	Map<String, Product> getDistributorKitSkus() {
		final Map<String, Product> skus = new HashMap<String, Product>();
		/*
		jdbcTemplate.query(getDistributorKitSku, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Product p = new Product();
				p.sku = rs.getString(2);
				p.sn_price = rs.getFloat(3);
				skus.put(rs.getString(1).trim(), p);
			}
		});*/
		return skus;
	}
	
	public Map<String, Float> getPrices(String country, Collection<String> skus) {
		Country2 c2 = Country2.valueOf(country);
		final Map<String, ?> products = get(c2, skus);
		// dirty low memory conversion by reusing the same collection
		for (@SuppressWarnings("rawtypes") Map.Entry e : products.entrySet()) {
			Product p = (Product)e.getValue();
			e.setValue(Float.valueOf(p.getPriceByPriceTier("MP").floatValue()));
		}
		
		return (Map<String, Float>) products;
	}
	
}