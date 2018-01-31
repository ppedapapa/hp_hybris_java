package com.shaklee.promo.impl.json;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.shaklee.common.util.CollectionFunctions.Predicate;
import com.shaklee.common.util.JsonLoader;
import com.shaklee.common.util.cache.Loader;
import com.shaklee.promo.PromoComponent;
import com.shaklee.promo.RuleSet;

/**
 * Implementation must be synchronized since it is not possible to pass any type
 * of context (request specific) to Jackson.
 * 
 * @author Elli Albek
 */

@Component
public class PromoComponentDeserializer {
	@Autowired
	ApplicationContext ctx;

	private final ObjectReader reader;

	private RuleSet<?> ruleset;

	public PromoComponentDeserializer() {
		final Loader<Class<?>, Object> factory = new Loader<Class<?>, Object>() {
			@Override
			@SuppressWarnings("unchecked")
			public Object get(Class<?> clazz) {
				final Object bean = createNewObject(clazz);
				if (bean instanceof PromoComponent<?>) {
					return ((PromoComponent) bean).clone(ruleset);
				}
				return bean;
			}

			private Object createNewObject(Class<?> clazz) {
				if (clazz.isAnnotationPresent(Component.class))
					return ctx.getBean(clazz);
				else
					try {
						return clazz.newInstance();
					} catch (Exception e) {
						throw new RuntimeException(
								"Cannot create instance of " + clazz.getSimpleName() + " using empty constructor", e);
					}
			}
		};

		final CustomFactoryJacksonModule module = new CustomFactoryJacksonModule("SpringInjectionModule",
				new Version(1, 0, 0, ""), new Predicate<Class<?>>() {
					@Override
					public boolean accept(Class<?> t) {
						return t.isAnnotationPresent(Component.class) || PromoComponent.class.isAssignableFrom(t);
					}
				}, factory);

		ObjectMapper mapper = JsonLoader.createDefaultMapper();
		mapper.withModule(module);
		reader = mapper.reader(ListWrapper.class);
	}

	private synchronized Object deserializedRuleset(String json, RuleSet<?> ruleset)
			throws JsonProcessingException, IOException {
		this.ruleset = ruleset;
		try {
			return reader.readValue(json);
		} finally {
			// clean up always
			this.ruleset = null;
		}
	}

	/**
	 * Due to the way it works jackson will not call our deserializers if we get
	 * a reader for a list. So we need to wrap it in a plain object that
	 * contains the list, and add that wrapper to the json.
	 */

	public List<Object> deserializeList(String json, RuleSet<?> ruleset) throws JsonProcessingException, IOException {
		json = "{\"list\":" + json + '}';
		ListWrapper w = (ListWrapper) deserializedRuleset(json, ruleset);
		return w.list;

	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public static class ListWrapper {
		@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
		public List<Object> list;
	}
}
