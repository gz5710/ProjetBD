/**
 * 
 */
package com.DB.dao;

import java.util.List;

import com.DB.model.Role;
import com.DB.model.User;

/**
 * @author Bruce GONG
 *
 */
public interface UserDao {
	public boolean addUser(User user);
	public boolean deleteUser(int id);
	public boolean updateUser(User user);
	public User getUserById(int id);
	public boolean checkDuplication(String username);
	public List<User> getInvalidUsers();
	public List<User> getValidUsers();
	public User getUserByName(String name);
	public void Login(User user);
	public boolean createUser4Login(String username);
	public boolean verifyUser(String username);
	public boolean removeUser4Login(String username);
}
