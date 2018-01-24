package com.shaklee.shared.events;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import com.shaklee.common.util.cache.CachingSingletonLoader;
import com.shaklee.common.util.cache.Loader;
import com.shaklee.shared.dao.JdbcTemplateDAO;
import com.shaklee.shared.data.event.EventStreamManagerSubscription;

/**
 * @author Elli Albek
 */
@Component
public class SalesExecDAO extends JdbcTemplateDAO {

	final String GET_MANAGERS = props.getProperty("GET_MANAGERS");

	@Autowired
	public SalesExecDAO(DataSource dataSource) {
		super(dataSource);
	}

	// ------------------ Regional Manager subscriptions
	private static final ParameterizedBeanPropertyRowMapper<EventStreamManagerSubscription> managerMapper = ParameterizedBeanPropertyRowMapper
			.newInstance(EventStreamManagerSubscription.class);

	CachingSingletonLoader<Map<String, List<EventStreamManagerSubscription>>> regManagersCache = new CachingSingletonLoader<Map<String, List<EventStreamManagerSubscription>>>(
			new Loader<Object, Map<String, List<EventStreamManagerSubscription>>>() {
				@Override
				public Map<String, List<EventStreamManagerSubscription>> get(Object key) {
					List<EventStreamManagerSubscription> mlist = getManagers();
					LinkedMultiValueMap<String, EventStreamManagerSubscription> managers = new LinkedMultiValueMap<String, EventStreamManagerSubscription>();
					for (EventStreamManagerSubscription manager : mlist)
						managers.add(manager.shakleeId, manager);
					return managers;
				}
			}, 3600); // one hour

	public List<EventStreamManagerSubscription> getManagers() {
		return jdbcTemplate.query(GET_MANAGERS, managerMapper);
	}

	public List<EventStreamManagerSubscription> getRegionalManager(String userId) {
		return regManagersCache.get().get(userId);
	}
}
