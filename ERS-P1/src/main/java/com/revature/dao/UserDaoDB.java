package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDaoDB implements UserDao {

	private ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_users";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int userId = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String email = rs.getString(6);
				int userRoleId = rs.getInt(7);		
				
				User u = new User(userId, username, password, firstName, lastName, email, userRoleId);
				users.add(u);
			}
			
			return users;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<User> getAllEmployees() {
		List<User> employees = new ArrayList<>();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_users where user_role_id = 1";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int userId = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String email = rs.getString(6);
				int userRoleId = rs.getInt(7);
				
				User u = new User(userId, username, password, firstName, lastName, email, userRoleId);
				employees.add(u);
			}
			
			return employees;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createUser(User user) {
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "insert into ers_users (ers_username, ers_password," 
				  	   + " user_first_name, user_last_name, user_email," 
					   + " user_role_id) values (?,?,?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getRoleId());
			
			ps.execute();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public User getUserById(int userId) {
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_users where ers_users_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			User u = new User();
			while(rs.next()) {
				String username = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String email = rs.getString(6);
				int userRoleId = rs.getInt(7);	
				
				u = new User(userId, username, password, firstName, lastName, email, userRoleId);
			}
			return u;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_users where user_email = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, userEmail);
			
			ResultSet rs = ps.executeQuery();
			
			User u = new User();
			while(rs.next()) {
				int userId = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				int userRoleId = rs.getInt(7);	
				
				u = new User(userId, username, password, firstName, lastName, 
						userEmail, userRoleId);
			}
			return u;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "select * from ers_users where ers_username = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			User u = new User();
			while(rs.next()) {
				int userId = rs.getInt(1);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String email = rs.getString(6);
				int userRoleId = rs.getInt(7);	
				
				u = new User(userId, username, password, firstName, lastName, 
						email, userRoleId);
			}
			return u;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "update ers_users set ers_username=?, ers_password=?," 
					   + " user_first_name=?, user_last_name=?, user_email=?," 
					   + " user_role_id=? where ers_users_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getRoleId());
			ps.setInt(7, user.getId());
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "delete from ers_users where ers_users_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, user.getId());
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
