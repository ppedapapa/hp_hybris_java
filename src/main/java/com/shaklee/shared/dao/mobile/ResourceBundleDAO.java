package com.shaklee.shared.dao.mobile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import com.shaklee.shared.dao.JdbcTemplateDAO;
import com.shaklee.shared.data.Country2;
import com.shaklee.shared.data.Language;

/**
 * Model class for resource bundle queries. It's main responsibility is
 * resolving defaults chain.
 * 
 * @author Elli Albek
 */
@Component
public class ResourceBundleDAO extends JdbcTemplateDAO {

	public static final Country2 DEFAULT_COUNTRY = Country2.us;
	public static final Language DEFAULT_LANGUAGE = Language.en;

	private final String GET, UPDATE;

	@Autowired
	public ResourceBundleDAO(DataSource ds) {
		super(ds);
		this.GET = props.getSql("GET");
		this.UPDATE = props.getProperty("UPDATE");
	}

	public Map<String, String> getBundle(String app, Country2 country,
			Language language) {
		// default
		Map<String, String> bundle = get(app, DEFAULT_COUNTRY, DEFAULT_LANGUAGE);

		if (country != DEFAULT_COUNTRY) {
			// last one, load country defaults
			Map<String, String> countryDefault = get(app, country, DEFAULT_LANGUAGE);
			bundle = merge(bundle, countryDefault);
		}
		
		if (language != DEFAULT_LANGUAGE) {
			Map<String, String> languageBundle = get(app, country, language);
			bundle = merge(bundle, languageBundle);
		}

		return bundle;
	}

	private Map<String, String> get(String app, Country2 country,
			Language language) {
		MapRowMapper mapper = new MapRowMapper();
		jdbcTemplate.query(GET, mapper, app, country.name().toLowerCase(),
				language.name());

		return mapper.map;
	}

	/**
	 * Returns a map merge where m2 overrides m1
	 */
	private static <K, V> Map<K, V> merge(Map<K, V> m1, Map<K, V> m2) {
		if (m1 == null || m1.isEmpty())
			return m2;
		if (m2 == null || m2.isEmpty())
			return m1;

		m1.putAll(m2);
		return m1;
	}

	private static class MapRowMapper implements RowCallbackHandler {

		HashMap<String, String> map = new HashMap<String, String>();

		@Override
		public void processRow(ResultSet rs) throws SQLException {
			String key = rs.getString(1);
			String value = rs.getString(2);
			map.put(key, value);
		}
	}

	public void update(String app, Country2 country, Language language,
			String key, String value) {
		jdbcTemplate.update(UPDATE, country.name(), language.name(), app, key,
				value);
	}
}