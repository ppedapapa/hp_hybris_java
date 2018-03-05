package com.shaklee.DAO;

import java.util.List;

import com.shaklee.entity.User;

public interface UserDAO {
	public User findUser(String userId);

	public String getIdForEmail(String email);

	public User findByEmail(String email);
}
