package com.shaklee.promo.impl.json;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.deser.BeanDeserializer;
import org.codehaus.jackson.map.deser.BeanDeserializerFactory;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.module.SimpleDeserializers;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.type.JavaType;

import com.shaklee.common.util.CollectionFunctions.Predicate;
import com.shaklee.common.util.cache.Loader;

/**
 * A custom factory that provides custom instantiation of objects, normally by
 * an ioc container.
 * 
 * In newer jackson this should be implemented as ValueInstantiators. Older
 * versions do not have this interface.
 * 
 * @author Elli Albek
 */
public class CustomFactoryJacksonModule extends SimpleModule {

	private final Predicate<Class<?>> filter;
	private final Loader<Class<?>, ?> factory;

	public CustomFactoryJacksonModule(String name, Version version, Predicate<Class<?>> filter,
			Loader<Class<?>, ?> factory) {
		super(name, version);
		_deserializers = new CustomFactoryDeserializers();
		this.filter = filter;
		this.factory = factory;
	}

	private class CustomFactoryDeserializers extends SimpleDeserializers {

		public ConcurrentHashMap<Class<?>, FactoryDeserializer> cache = new ConcurrentHashMap<Class<?>, FactoryDeserializer>();

		public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config,
				DeserializerProvider provider, BeanDescription beanDesc, BeanProperty property)
				throws JsonMappingException {
			Class<?> clazz = type.getRawClass();

			if (filter.accept(clazz))
				return getFactoryDeserializer(type, config, beanDesc, property, clazz);
			else
				// not for us
				return null;
		}

		private JsonDeserializer<?> getFactoryDeserializer(JavaType type, DeserializationConfig config,
				BeanDescription beanDesc, BeanProperty property, Class<?> clazz) throws JsonMappingException {
			FactoryDeserializer deserializer = cache.get(clazz);
			if (deserializer == null) {
				deserializer = createFactoryDeserializer(type, config, beanDesc, property);
				cache.put(clazz, deserializer);
			}
			return deserializer;
		}

		private FactoryDeserializer createFactoryDeserializer(JavaType type, DeserializationConfig config,
				BeanDescription beanDesc, BeanProperty property) throws JsonMappingException {
			final JsonDeserializer<Object> beanDeserializer = BeanDeserializerFactory.instance
					.buildBeanDeserializer(config, type, (BasicBeanDescription) beanDesc, property);
			return new FactoryDeserializer((BeanDeserializer) beanDeserializer);
		}
	}

	private class FactoryDeserializer extends BeanDeserializer {

		protected FactoryDeserializer(BeanDeserializer src) {
			super(src);
		}

		// This is the correct way for jackson 2.x
		// protected Object constructDefaultInstance() {
		// return factory.get(getBeanClass());
		// }

		/**
		 * This is a workaround for Jackson 1.x
		 */
		@Override
		public Object deserializeFromObject(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			Object bean = factory.get(getBeanClass());
			return super.deserialize(jp, ctxt, bean);
		}
	}
}
