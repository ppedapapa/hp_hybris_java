package com.shaklee.shared.model.rb;

import java.sql.Date;
import java.util.Map;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import com.shaklee.common.util.cache.CachingLoader;
import com.shaklee.common.util.cache.Loader;
import com.shaklee.shared.dao.mobile.ResourceBundleDAO;
import com.shaklee.shared.data.Country2;
import com.shaklee.shared.data.Language;

/**
 * Cache wrapper. This is because we cannot use the cache annotations due to old
 * spring version.
 * 
 * @author Elli Albek
 */
@Component
public class ResoruceBundleCache {

	@Autowired
	ResourceBundleDAO dao;

	private final CachingLoader<RBKey, Map<String, String>> resourceCache = new CachingLoader<RBKey, Map<String, String>>(
			new Loader<RBKey, Map<String, String>>() {
				@Override
				public Map<String, String> get(RBKey key) {
					return dao.getBundle(key.app, key.country, key.language);
				}
			}, 600);

	private static final Mustache.Formatter mustacheFormatter = new Mustache.Formatter() {
		private final FastDateFormat df = FastDateFormat.getInstance("M/d/yyyy");

		public String format(Object value) {
			if (value == null)
				return null;

			if (value instanceof Date)
				return df.format((Date) value);

			return String.valueOf(value);
		}
	};

	/**
	 * Cache templates. This can essentially be cached forever.
	 */
	public final CachingLoader<String, Template> templateCache = new CachingLoader<String, Template>(
			new Loader<String, Template>() {
				@Override
				public Template get(String text) {
					return Mustache.compiler().withFormatter(mustacheFormatter).compile(text);
				}
				// 24 hours
			}, 86400);

	public Map<String, String> getBundle(String app, Country2 country, Language language) {
		return resourceCache.get(new RBKey(app, country, language));
	}

	/**
	 * This should come out from another framework
	 */
	static class RBKey {
		final String app;
		final Country2 country;
		final Language language;

		private RBKey(String app, Country2 country, Language language) {
			this.app = app;
			this.country = country;
			this.language = language;
		}

		@Override
		public boolean equals(Object obj) {
			RBKey other = (RBKey) obj;
			return language == other.language && country == other.country && app.equals(other.app);
		}

		@Override
		public int hashCode() {
			// good enough and fast
			return country.hashCode() ^ language.hashCode();
		}

		@Override
		public String toString() {
			return app + ':' + country + '-' + language;
		}
	}

	// static final class ResourceKey extends RBKey {
	//
	// final String key;
	//
	// private ResourceKey(String app, Country2 country, Language language,
	// String key) {
	// super(app, country, language);
	// this.key = key;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// ResourceKey other = (ResourceKey) obj;
	// return key.equals(other.key) && super.equals(obj);
	// }
	//
	// @Override
	// public int hashCode() {
	// return key.hashCode();
	// }
	// }
}