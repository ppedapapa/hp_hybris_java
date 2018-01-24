package com.shaklee.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.springframework.stereotype.Component;

import com.shaklee.itrack.common.util.UTCDateUtils;

/**
 * Loads object configuration from json. It is assumed that the objects come
 * from IOC container, already containing bindings to other objects.
 * 
 * @author Elli Albek
 */
//@Component
public class JsonLoader {

	// private SchemaCache schemas;
	private final ObjectMapper mapper;
	private final ObjectReader reader;

	// private ObjectWriter writer;

	public JsonLoader() {
		mapper = createDefaultMapper();
		//
		// // load runtime class (reader)
		reader = mapper.reader();
		// // writer = mapper.defaultPrettyPrintingWriter();
		// schemas = new SchemaCache(mapper);
	}

	public static ObjectMapper createDefaultMapper() {
		ObjectMapper mapper = new ObjectMapper();
		// // configure mapper
		SimpleDateFormat parser = new SimpleDateFormat(UTCDateUtils.DATETIME_FORMAT);
		parser.setTimeZone(UTCDateUtils.UTC_TIME_ZONE);
		mapper.setDateFormat(parser);
		return mapper;
	}

	/**
	 * Populate an existing object with values from json
	 */
	public void deserialize(Object obj, String json) throws JsonProcessingException, IOException {
		reader.withValueToUpdate(obj).readValue(json);
	}

	// public JsonNode serlialize(Object obj) throws JsonProcessingException,
	// IOException {
	// return mapper.valueToTree(obj);
	// }

	// public JsonNode deserialize(String json) throws JsonProcessingException {
	// try {
	// return mapper.readTree(json);
	// } catch (IOException e) {
	// throw new RuntimeException(
	// "Unexpected IO error from simple string (which does not require IO)",
	// e);
	// }
	// }

	/**
	 * Deserialize from db values
	 * 
	 * @throws InputValidationException
	 */
	// public void deserialize(Object obj, Map<String, List<String>> data)
	// throws JsonProcessingException, IOException,
	// InputValidationException {
	// // build a json tree
	// final ObjectNode schema = schemas.get(obj.getClass());
	// final ObjectNode json = mapper.createObjectNode();
	// for (Map.Entry<String, List<String>> e : data.entrySet()) {
	// final String name = e.getKey();
	// final List<String> value = e.getValue();
	//
	// if (schema == null) {
	// // special case for maps which do not have a schema with all the
	// // fields
	// // json.put(name, value.get(0));
	// if (value.size() == 1)
	// json.put(name, value.get(0));
	// else {
	// ArrayNode a = json.putArray(name);
	// for (int i = 0; i < value.size(); i++)
	// a.add(value.get(i));
	// }
	// } else {
	// JsonNode node = schema.get(name);
	// if (node == null) {
	// String msg = "Database parameter '" + name
	// + "' does not exist in class "
	// + obj.getClass().getSimpleName();
	// throw InputValidationException
	// .oneFieldError(msg, name, msg);
	// }
	//
	// if (value.isEmpty()) {
	// // some kind of internal error
	// String msg = "Value for bean field " + name + " is empty";
	// throw InputValidationException
	// .oneFieldError(msg, name, msg);
	// }
	// // the target type is an array, create a json array for it
	// if (node.get("type").getValueAsText().equals("array")) {
	// ArrayNode array = json.putArray(name);
	// for (String s : value)
	// array.add(s);
	// } else
	// json.put(name, value.get(0));
	// }
	// }
	// // if (logger.isDebugEnabled())
	// // logger.debug("Populating " + obj.getClass().getSimpleName()
	// // + " with " + json.toString());
	// reader.withValueToUpdate(obj).readValue(json);
	// }

	// public static class SchemaCache {
	//
	// private SchemaCache(ObjectMapper mapper) {
	// this.mapper = mapper;
	// }
	//
	// private final HashMap<Class<?>, ObjectNode> cache = new HashMap<Class<?>,
	// ObjectNode>();
	// private final ObjectMapper mapper;
	//
	// public synchronized ObjectNode get(Class<?> clazz)
	// throws JsonMappingException {
	//
	// ObjectNode schema = cache.get(clazz);
	// if (schema == null) {
	// schema = (ObjectNode) mapper.generateJsonSchema(clazz)
	// .getSchemaNode().get("properties");
	// cache.put(clazz, schema);
	// }
	// return schema;
	// }
	// }
}
