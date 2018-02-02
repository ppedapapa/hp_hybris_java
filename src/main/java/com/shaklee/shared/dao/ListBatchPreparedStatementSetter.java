package com.shaklee.shared.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Simplified interface for batch updates.
 * 
 * @author Elli Albek
 * 
 * @param <T>
 *            The type of the object we will process.
 */
public interface ListBatchPreparedStatementSetter<T> {

	/**
	 * Set values of a single object
	 */
	public void setValues(PreparedStatement ps, T object) throws SQLException;
}
