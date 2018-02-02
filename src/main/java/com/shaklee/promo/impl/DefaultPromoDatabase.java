package com.shaklee.promo.impl;

import static com.shaklee.common.util.CollectionFunctions.group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.shaklee.common.util.StringUtils;
import com.shaklee.common.util.CollectionFunctions.Get;
import com.shaklee.common.util.PropertiesObject;
import com.shaklee.promo.Arbitrator;
import com.shaklee.promo.LocalizedData;
import com.shaklee.promo.Promo;
import com.shaklee.promo.PromoDatabase;
import com.shaklee.promo.PromoRequest;

/**
 * Default database load implementation. This is the only class in the default
 * implementation that is actually using a cart object and not an abstract type.
 * Therefore it is the only place where object cache can live, since hte cash
 * assumes the type of request for cache keys (currently the cache key is the
 * country field of the cart object)
 * 
 * @author Elli Albek
 */
@Component
@PropertySource(value = "classpath:props/DefaultPromoDatabase.properties")
public class DefaultPromoDatabase<T> implements PromoDatabase<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPromoDatabase.class);

	@Value("${SELECT_PROMOS}")
	private  String SELECT_PROMOS;
	
	@Value("${SELECT_PROMO_USER}")
	private  String	 SELECT_PROMO_USER;
	
	@Value("${ARBITRATOR}")
	private  String ARBITRATOR;
	
	@Value("${GET_USER_ELIGIBILITY_EXTRA_DATA}")
	private  String	 GET_USER_ELIGIBILITY_EXTRA_DATA;
	
	@Value("${USER_IN_PROMO_USER_GROUP}")
	private  String USER_IN_PROMO_USER_GROUP;

	private JdbcTemplate jdbct;

	@Autowired
	public ApplicationContext ctx;

	@Autowired
	PromoLoader<PromoRequest<T>> loader;

	@Autowired
	public DefaultPromoDatabase(DataSource dataSource) {
		jdbct = new JdbcTemplate(dataSource);
	}

	public List<Promo<PromoRequest<T>>> loadPromoRules(LocalizedData request, Timestamp ts) {
		List<Map<String, Object>> r = jdbct.queryForList(SELECT_PROMOS, request.getCountryCode(),
				request.getRulesetGroup(), ts, ts);
		return loader.createPromos(r);
	}

	public List<ArbitratorConfig> loadArbitratorRules(LocalizedData request) {
		List<Map<String, Object>> results = jdbct.queryForList(ARBITRATOR, request.getCountryCode(),
				request.getRulesetGroup());

		return loader.createArbitratorConfig(results);
	}

	// ----------- arbitrator ----------------------------------

	public Arbitrator<PromoRequest<T>> loadArbitrator(LocalizedData request) {
		List<ArbitratorConfig> arbitratorsConfigs = loadArbitratorRules(request);

		if (CollectionUtils.isEmpty(arbitratorsConfigs))
			return null;

		final List<Arbitrator<PromoRequest<T>>> arbitrators = new ArrayList<Arbitrator<PromoRequest<T>>>(
				arbitratorsConfigs.size());

		for (ArbitratorConfig config : arbitratorsConfigs) {
			Arbitrator<PromoRequest<T>> arbitrator = loadArbitrator(config);
			arbitrators.add(arbitrator);
		}

		return new ArbitratorsWrapper<PromoRequest<T>>(arbitrators);
	}

	private <R> Arbitrator<R> loadArbitrator(ArbitratorConfig cfg) {
		try {
			return loader.loadSimpleBean(cfg.implementingClass, cfg.jsonSerialized);
		} catch (Exception e) {
			LOGGER.error(
					"Falied to load arbitrator id=" + cfg.id
							+ ". Using FirstMatchArbitrator instead. This is only a temporary replacement and MUST BE FIXED.",
					e);
			return new FirstMatchArbitrator<R>();
		}
	}

	// ------------------ PromoDatabase -----------------------
	// TODO: Separate this method to another interface.
	public List<Integer> loadEligibilityMatches(List<Integer> userGroupIDs, LocalizedData request) {
		// avoid query if this request has no user.
		if (request.getUserId() == null)
			return Collections.emptyList();

		String sql = SELECT_PROMO_USER.replace("??", StringUtils.join(userGroupIDs, ','));

		return jdbct.queryForList(sql, Integer.class, request.getUserId());
	}

	// ----------- eligibility extra data ----------------------------------

	public String[] getUserEligibilityExtraData(String shakleeID, int groupID) {
		try {
			return jdbct.queryForObject(GET_USER_ELIGIBILITY_EXTRA_DATA, stringArrayMapper, shakleeID, groupID);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String getUserEligibilityExtraData1(String shakleeID, int groupID) {
		try {
			return jdbct.queryForObject(GET_USER_ELIGIBILITY_EXTRA_DATA, String.class, shakleeID, groupID);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	private static final RowMapper<String[]> stringArrayMapper = new RowMapper<String[]>() {

		@Override
		public String[] mapRow(ResultSet rs, int index) throws SQLException {
			int len = rs.getMetaData().getColumnCount();
			String[] s = new String[len];

			for (int i = 0; i < len; i++)
				s[i] = rs.getString(i + 1);

			return s;
		}
	};

	public boolean userInPromoGroup(String shakleeID, Integer groupID) {
		try {
			jdbct.queryForObject(USER_IN_PROMO_USER_GROUP, Integer.class, shakleeID, groupID);
		} catch (EmptyResultDataAccessException ex) {
			return false;
		}
		return true;
	}

	private static final BeanPropertyRowMapper<ArbitratorConfig> arbitratorConfigMapper = BeanPropertyRowMapper
			.newInstance(ArbitratorConfig.class);

	public static class ArbitratorConfig {

		public int id;
		public String implementingClass, jsonSerialized, type;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getImplementingClass() {
			return implementingClass;
		}

		public void setImplementingClass(String implementingClass) {
			this.implementingClass = implementingClass;
		}

		public String getJsonSerialized() {
			return jsonSerialized;
		}

		public void setJsonSerialized(String jsonSerialized) {
			this.jsonSerialized = jsonSerialized;
		}
	}

}