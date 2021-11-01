package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {

	boolean createUser(User user);
	
	List<User> getAllEmployees();
	List<User> getAllUsers();
	
	User getUserById(int userId);
	User getUserByEmail(String userEmail);
	User getUserByUsername(String username);
	
	boolean updateUser(User user);
	
	boolean deleteUser(User user);
	
}
