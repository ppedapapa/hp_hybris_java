package com.shaklee.promo.impl;

import static com.shaklee.common.util.CollectionFunctions.group;
import static com.shaklee.common.util.CollectionFunctions.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.validation.ConstraintViolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.shaklee.common.util.CollectionFunctions.Function;
import com.shaklee.common.util.CollectionFunctions.Get;
import com.shaklee.common.util.MultiValueMap;
import com.shaklee.common.util.validation.BeanValidator;
import com.shaklee.promo.Action;
import com.shaklee.promo.Condition;
import com.shaklee.promo.Promo;
import com.shaklee.promo.PromoCollections;
import com.shaklee.promo.PromoComponent;
import com.shaklee.promo.RuleSet;
import com.shaklee.promo.basic.Message;
import com.shaklee.promo.basic.MessageAction;
import com.shaklee.promo.impl.DefaultPromoDatabase.ArbitratorConfig;
import com.shaklee.promo.util.PromoJsonLoader;
import com.shaklee.shared.validation.InputValidationException;

/**
 * Loads the promo engine data object from flat data table, which is normally
 * how the promo rules come from the DB. This object dose not connect to DB,
 * just parse the results.
 * 
 * @author Elli Albek
 */
@Component
public class PromoLoader<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

	private static final String defaultComponentPackage = Message.class.getPackage().getName() + '.';

	@Autowired
	PromoJsonLoader jsonLoader;

	/** Comparator for rulesets order in a promo (by ID) **/
	public static final Comparator<RuleSet<?>> rulesOrder = new Comparator<RuleSet<?>>() {

		@Override
		public int compare(RuleSet<?> o2, RuleSet<?> o1) {
			return o2.id - o1.id;
		}
	};

	@Autowired
	public ApplicationContext ctx;

	protected List<ArbitratorConfig> createArbitratorConfig(List<Map<String, Object>> results) {
		final List<ArbitratorConfig> arbitratorsConfigs = new ArrayList<ArbitratorConfig>();

		for (Map<String, Object> data : results) {
			final ArbitratorConfig c = new ArbitratorConfig();
			c.id = getIngeter(data.get("ID"));
			c.implementingClass = (String) data.get("IMPLEMENTING_CLASS");
			c.jsonSerialized = (String) data.get("JSON_SERIALIZED");
			arbitratorsConfigs.add(c);
		}
		return arbitratorsConfigs;
	}

	protected List<Promo<T>> createPromos(List<Map<String, Object>> r) {
		Map<Object, List<Map<String, Object>>> promosMap = group(r, new Get<Object>("PROMO_ID"));

		Map<Object, Promo<T>> promos = map(promosMap,
				new Function<Map.Entry<Object, List<Map<String, Object>>>, Promo<T>>() {

					@Override
					public Promo<T> apply(Entry<Object, List<Map<String, Object>>> input) {
						return createPromo(input.getValue());
					}
				});

		final ArrayList<Promo<T>> l = new ArrayList<Promo<T>>(promos.values());

		// sort by priority
		// TODO: Remove this, it is now part of PromoEngineLoader
		Collections.sort(l, PromoCollections.promoPriorityComparator);

		return l;
	}

	protected Promo<T> createPromo(List<Map<String, Object>> data) {
		final Map<String, Object> row = data.get(0);
		final Promo<T> c = new Promo<T>();
		c.priority = getIngeter(row.get("PRIORITY"));
		c.id = getIngeter(row.get("PROMO_ID"));
		c.eligibilityGroupID = getIngeter(row.get("GROUP_ID"));
		c.code = (String) row.get("CODE");

		Map<Object, List<Map<String, Object>>> rulessMap = group(data, new Get<Object>("RULESET_ID"));

		Collection<RuleSet<T>> rules = map(rulessMap,
				new Function<Map.Entry<Object, List<Map<String, Object>>>, RuleSet<T>>() {

					@Override
					public RuleSet<T> apply(Entry<Object, List<Map<String, Object>>> input) {
						try {
							return createRuleset(c, input.getValue());
						} catch (ComponentException e) {
							String msg = "Promo " + c.code + ": Error loading ruleset " + input.getKey() + " component "
									+ e.componentId;
							if (e.type != null)
								msg = msg + " (" + e.type + ')';

							LOGGER.error(msg, e.getCause());
							return null;
						} catch (Exception e) {
							LOGGER.error("Promo " + c.code + ": Error loading ruleset " + input.getKey(), e);
							return null;
						}
					}

				}).values();

		c.rulesets = new ArrayList<RuleSet<T>>(rules);

		Collections.sort(c.rulesets, rulesOrder);

		return c;
	}

	@SuppressWarnings("unchecked")
	RuleSet<T> createRuleset(Promo<T> c, List<Map<String, Object>> data) throws LoaderException {

		final RuleSet<T> ruleset = new RuleSet<T>();
		ruleset.promo = c;
		ruleset.id = getIngeter(data.get(0).get("RULESET_ID"));

		Map<Object, List<Map<String, Object>>> componentMap = group(data, new Get<Object>("COMP_ID"));

		if (componentMap.size() > 1) { // force sort order
			Map<Object, List<Map<String, Object>>> unsorted = componentMap;

			componentMap = new TreeMap<Object, List<Map<String, Object>>>(intComparator);

			componentMap.putAll(unsorted);
		}

		Collection<Object> components = map(componentMap,
				new Function<Map.Entry<Object, List<Map<String, Object>>>, Object>() {

					@Override
					public Object apply(Entry<Object, List<Map<String, Object>>> input) {
						try {
							return createComponent(ruleset, input.getValue());
						} catch (Exception e) {
							String type = input.getValue().get(0).get("IMPLEMENTING_CLASS").toString();
							// wrapper exception adds component ID for logging.
							throw new ComponentException(e, input.getKey(), type);
						}
					}
				}).values();

		return createRuleset(ruleset, components);
	}

	// TODO: Replace T with ?

	static <T> RuleSet<T> createRuleset(final RuleSet<T> ruleset, Collection<Object> components) {
		// now when all parts are loaded, initialize the promo with them
		// promo.condition
		{
			List<Condition<T>> conditions = map(components, new Function<Object, Condition<T>>() {
				@SuppressWarnings("unchecked")
				@Override
				public Condition<T> apply(Object input) {
					if (input instanceof Condition<?>)
						return (Condition<T>) input;
					else
						return null;
				}
			});

			ruleset.condition = createCondition(conditions, ruleset);
		}
		{
			final List<Action<T>> messages = map(components, new Function<Object, Action<T>>() {
				@SuppressWarnings("unchecked")
				@Override
				public Action<T> apply(Object input) {
					if (input instanceof MessageAction)
						return (Action<T>) input;
					else
						return null;
				}
			});
			if (!messages.isEmpty())
				ruleset.messages = messages;
		}
		{
			final List<Action<T>> actions = map(components, new Function<Object, Action<T>>() {
				@SuppressWarnings("unchecked")
				@Override
				public Action<T> apply(Object t) {
					if (t instanceof Action && !(t instanceof MessageAction))
						return (Action<T>) t;
					else
						return null;
				}
			});
			if (!actions.isEmpty())
				ruleset.actions = actions;
		}
		if (ruleset.actions == null && ruleset.messages == null)
			throw new LoaderException(
					"Ruleset " + ruleset.id + " in promo " + ruleset.promo.code + " has no actions and no messages");
		return ruleset;
	}

	Object createComponent(RuleSet<T> rules, List<Map<String, Object>> data)
			throws ClassNotFoundException, InputValidationException, IOException {
		final Map<String, Object> dataMap = data.get(0);
		final String json = (String) dataMap.get("JSON_SERIALIZED");

		final Object comp = loadPromoComponent(rules, data);

		if (json != null) {
			try {
				jsonLoader.deserialize(comp, json);
			} catch (JsonProcessingException e) {
				throw new InputValidationException("Bad json " + json + ":\n" + e.getLocalizedMessage());
			}
		} else {
			MultiValueMap<String, String> params = new MultiValueMap<String, String>(data.size());
			for (Map<String, Object> param : data) {
				final String paramName = (String) param.get("PARAM");
				if (paramName != null)
					params.add(paramName, (String) param.get("VAL"));
			}

			if (!params.isEmpty()) {
				jsonLoader.deserialize(comp, params);
			}
		}

		validate(comp);
		return comp;
	}

	protected String componentError(final Map<String, Object> dataMap) {
		return dataMap.get("IMPLEMENTING_CLASS") + " #" + dataMap.get("COMP_ID");
	}

	public <B> B loadSimpleBean(String className, String json) throws InputValidationException, JsonProcessingException,
			IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		@SuppressWarnings("unchecked")
		B bean = (B) Class.forName(className).newInstance();
		if (json != null)
			jsonLoader.deserialize(bean, json);
		validate(bean);
		return bean;
	}

	protected void validate(final Object comp) throws InputValidationException {
		final Set<ConstraintViolation<Object>> errors = BeanValidator.INSTANCE.validate(comp);
		if (!errors.isEmpty())
			// Got form validation errors
			BeanValidator.throwInputValidationException(comp.getClass().getSimpleName(), errors);
	}

	public static <C> Condition<C> createCondition(Collection<Condition<C>> conditions, RuleSet<C> ruleset) {
		switch (conditions.size()) {
		case 0:
			String message = "Ruleset has no conditions";
			if (ruleset.promo != null)
				message = message + ", promo " + ruleset.promo.code + " " + ruleset.promo.id;
			throw new LoaderException(message);
		case 1:
			return conditions.iterator().next();
		default:
			return new AndCondition<C>(conditions, ruleset);
		}
	}

	protected Object loadPromoComponent(RuleSet<T> rules, List<Map<String, Object>> data)
			throws ClassNotFoundException {
		String name = data.get(0).get("IMPLEMENTING_CLASS").toString();
		Object comp = get(name);
		if (comp instanceof PromoComponent) {
			@SuppressWarnings("unchecked")
			PromoComponent<T> pc = (PromoComponent<T>) comp;
			comp = pc.clone(rules);
		}
		return comp;
	}

	public Object get(String name) throws ClassNotFoundException {
		if (name.indexOf('.') <= 0)
			// change to fully qualified name
			name = defaultComponentPackage + name;
		return ctx.getBean(Class.forName(name));
	}

	public static Integer getIngeter(Object o) {
		if (o == null)
			return null;
		return ((Number) o).intValue();
	}

	/**
	 * A comparator that sorts primary keys in accending order
	 */
	private static final Comparator<Object> intComparator = new Comparator<Object>() {

		@Override
		public int compare(Object o1, Object o2) {
			return (int) ((Integer) o1 - (Integer) o2);
		}

	};

	// ----------- Internal error handling and wrapping ----------------------

	private static class ComponentException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		Object componentId;
		String type;

		ComponentException(Throwable cause, Object componentId, String type) {
			super(cause);
			this.componentId = componentId;
			this.type = type;
		}
	}
}
