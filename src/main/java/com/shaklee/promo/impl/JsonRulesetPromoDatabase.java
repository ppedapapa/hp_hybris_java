package com.shaklee.promo.impl;

import static com.shaklee.common.util.CollectionFunctions.filter;
import static com.shaklee.common.util.CollectionFunctions.group;
import static com.shaklee.common.util.CollectionFunctions.map;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.shaklee.common.util.CollectionFunctions.Function;
import com.shaklee.common.util.CollectionFunctions.Predicate;
import com.shaklee.common.util.CollectionUtils;
import com.shaklee.common.util.validation.BeanValidator;
import com.shaklee.common.util.PropertiesObject;
import com.shaklee.promo.Arbitrator;
import com.shaklee.promo.LocalizedData;
import com.shaklee.promo.Promo;
import com.shaklee.promo.PromoDatabase;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.RuleSet;
import com.shaklee.promo.impl.json.PromoComponentDeserializer;
import com.shaklee.shared.validation.InputValidationException;

@Component
@PropertySource(value = "classpath:props/JsonRulesetPromoDatabase.properties")
public class JsonRulesetPromoDatabase<T> implements PromoDatabase<T> {

	private static final Logger logger = LoggerFactory.getLogger(JsonRulesetPromoDatabase.class);

	@Value("${SELECT_RULESETS}")
	private  String SELECT_RULESETS;

	private static final BeanPropertyRowMapper<RuleSetResult> ruleSetResultMapper = BeanPropertyRowMapper
			.newInstance(RuleSetResult.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	private PromoComponentDeserializer deserializer;

	@Autowired
	public JsonRulesetPromoDatabase(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Autowired
	DefaultPromoDatabase<T> db;

	@Override
	public List<Promo<PromoRequest<T>>> loadPromoRules(LocalizedData request, Timestamp ts) {
		return loadPromos(request, null);
	}

	@Override
	public Arbitrator<PromoRequest<T>> loadArbitrator(LocalizedData request) {
		return db.loadArbitrator(request);
	}

	public List<Promo<PromoRequest<T>>> loadPromos(LocalizedData request, Timestamp now) {
		List<RuleSetResult> r = jdbcTemplate.query(SELECT_RULESETS, ruleSetResultMapper, request.getCountryCode(),
				request.getRulesetGroup(), now, now);

		Map<Integer, List<RuleSetResult>> promoResults = group(r, new Function<RuleSetResult, Integer>() {
			@Override
			public Integer apply(RuleSetResult ruleset) {
				// the turns the ID of the PROMO
				return ruleset.promo_id;
			}
		});

		List<Promo<PromoRequest<T>>> promos = map(promoResults.values(),
				new Function<List<RuleSetResult>, Promo<PromoRequest<T>>>() {

					@Override
					public Promo<PromoRequest<T>> apply(List<RuleSetResult> promoRules) {
						try {
							return createPromo(promoRules);
						} catch (Exception e) {
							final RuleSetResult promo = promoRules.get(0);
							logger.error("Failed loading promo " + promo.code + " " + promo.promo_id, e);
							return null;
						}
					}
				});

		// TODO: Sort

		return promos;
	}

	private <P> Promo<P> createPromo(List<RuleSetResult> pr) throws IOException, InputValidationException {

		// aggregate list of all shared components
		final List<Object> sharedComponents = createSharedComponents(pr);

		List<RuleSetResult> rulesetComponents = filter(pr, new Predicate<RuleSetResult>() {
			@Override
			public boolean accept(RuleSetResult t) {
				return !t.shared;
			}
		});

		if (rulesetComponents.isEmpty()) {
			RuleSetResult p = pr.get(0);
			throw new LoaderException("Promo " + p.code + " " + p.promo_id + " has no rulsets");
		}

		List<RuleSet<P>> actualRules = new ArrayList<RuleSet<P>>(rulesetComponents.size());
		for (RuleSetResult rs : rulesetComponents) {
			try {
				actualRules.add((RuleSet<P>) createRuleset(rs, sharedComponents));
			} catch (IOException e) {
				logger.error("Error creating promo components in promo " + rs.code + " ruleset " + rs.ruleset_id
						+ " json " + rs.jsonSerialized, e);
				// throw e;
				// TODO: Remove comment
			}
		}

		// pick the first result as the returned value
		Promo<P> promo = pr.get(0).toPromo();
		promo.rulesets = actualRules;
		
		Collections.sort(promo.rulesets, PromoLoader.rulesOrder);
		
		return promo;
	}

	private List<Object> createSharedComponents(List<RuleSetResult> all) throws IOException, InputValidationException {
		List<RuleSetResult> sharedRulesets = filter(all, new Predicate<RuleSetResult>() {
			@Override
			public boolean accept(RuleSetResult t) {
				return t.shared;
			}
		});

		ArrayList<Object> components = new ArrayList<Object>();

		for (RuleSetResult ruleSetResult : sharedRulesets)
			components.addAll(createSharedRules(ruleSetResult));

		return components;
	}

	private List<?> createSharedRules(RuleSetResult rules) throws IOException, InputValidationException {
		if (rules.jsonSerialized == null || rules.jsonSerialized.length() < 10)
			throw new IllegalArgumentException(
					"Promo " + rules.code + " ruleset " + rules.ruleset_id + " has no json components");

		RuleSet<?> shared = new RuleSet<Object>();
		shared.promo = rules.toPromo();
		shared.id = rules.ruleset_id;

		final List<Object> components = deserializeComponents(rules.jsonSerialized, shared);

		return components;
	}

	private <P> RuleSet<P> createRuleset(RuleSetResult ruleset, List<Object> sharedComponents)
			throws IOException, InputValidationException {
		RuleSet<P> r = new RuleSet<P>();
		r.id = ruleset.ruleset_id;
		r.promo = ruleset.toPromo();

		List<Object> components = deserializeComponents(ruleset.jsonSerialized, r);

		// insert shared in the beginning
		components = CollectionUtils.combine(sharedComponents, components);

		return PromoLoader.createRuleset(r, components);
	}

	private <P> List<Object> deserializeComponents(String json, RuleSet<P> r)
			throws JsonProcessingException, IOException, InputValidationException {
		List<Object> components = deserializer.deserializeList(json, r);
		for (Object comp : components)
			BeanValidator.INSTANCE.assertValid(comp);

		return components;
	}

	public static class RuleSetResult {
		boolean shared;
		String jsonSerialized;
		int ruleset_id;
		public String code; // promo code
		public int promo_id, priority;
		public Integer groupId;

		public boolean isShared() {
			return shared;
		}

		public void setShared(boolean shared) {
			this.shared = shared;
		}

		public void setJsonSerialized(String jsonSerialized) {
			this.jsonSerialized = jsonSerialized;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public void setPromoId(int id) {
			this.promo_id = id;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public void setRuleset_id(int ruleset_id) {
			this.ruleset_id = ruleset_id;
		}

		public void setGroupId(Integer groupId) {
			this.groupId = groupId;
		}

		<P> Promo<P> toPromo() {
			Promo<P> p = new Promo<P>();
			p.code = code;
			p.id = promo_id;
			p.priority = priority;
			p.eligibilityGroupID = groupId;
			return p;
		}
	}
}
