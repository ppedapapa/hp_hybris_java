package com.shaklee.shared.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.shaklee.itrack.common.util.PropertiesObject;
import com.shaklee.shared.dao.json.JsonSqlServiceModel;

/**
 * User preallocation itrack related data.
 * 
 * @author Elli Albek
 */
@Component
public class ActivityLoggingDAO {

	// private static final Logger logger = Logger
	// .getLogger(ActivityLoggingDAO.class);

	public static final String[] CLIENT_APPS = { "MEMBER_CENTER", "DISTRIBUTOR_MOBILE", "180_MOBILE", "AUTOSHIP_BATCH",
			"SSOE", "SBOSS", "SDS", "API_SERVER", "RNT", "JAVA_BATCH", "PWS", "LA_CENTER", "PHP_UNIT", "JUNIT",
			"UNKNOWN" };

	public static final String[] COMPONENTS = { "AUTOSHIP", "WALLET", "PROFILE", "PWS", "HEARSAY", "SPONSOR", "HQ",
			"CAPTCHA", "OPENID_CONNECT", "COMMUNICATION_PREFERENCES" };

	JsonSqlServiceModel model;

	private static final PropertiesObject prop = new PropertiesObject(ActivityLoggingDAO.class);

	private static final String SELECT_ACTIVITIES = prop.getProperty("SELECT_ACTIVITIES"),
			INSERT = prop.getProperty("INSERT");

	final JdbcTemplate jdbct;

	@Autowired
	ActivityLoggingDAO(DataSource dataSource) {
		jdbct = new JdbcTemplate(dataSource);
		model = new JsonSqlServiceModel(getClass(), dataSource);
	}

	public void insert(String shakleeID, String orderID, Integer autoshipID, String client_app, String component,
			String event, String site, String message, String jsonAttachement) {
		insert(INSERT, shakleeID, component, site, event, client_app, message, jsonAttachement, autoshipID, orderID);
	}

	private void insert(String sql, Object... values) {
		final int updates = jdbct.update(sql, values);

		if (updates != 1)
			throw new IncorrectUpdateSemanticsDataAccessException("Insert statement changed " + updates + " rows");
	}

	public List<JSONObject> select(String shakleeID, Long orderID, Integer autoshipID) throws JSONException {
		List<Object> whereEquals = whereEquals("SHAKLEE_ID", shakleeID, "ORDER_ID", orderID, "AUTOSHIP_ID", autoshipID);
		String where = whereEquals.remove(0).toString();
		String sql = SELECT_ACTIVITIES.replace("???", where);
		return jdbct.query(sql, JsonSqlServiceModel.JSON_ROW_MAPPER, whereEquals.toArray());
	}

	static List<Object> whereEquals(Object... conditions) {
		StringBuilder where = new StringBuilder();
		ArrayList<Object> values = new ArrayList<Object>();
		for (int i = 0; i < conditions.length;) {
			final String name = conditions[i++].toString();
			final Object value = conditions[i++];
			if (value != null) {
				if (where.length() > 0)
					where.append(" and ");
				where.append(name).append("=?");
				values.add(value);
			}
		}
		if (where.length() > 0)
			where.insert(0, "where ");

		values.add(0, where.toString());
		return values;
	}
}