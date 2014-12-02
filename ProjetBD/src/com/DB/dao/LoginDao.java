/**
 * 
 */
package com.DB.dao;

import com.DB.model.Login;

/**
 * @author Bruce GONG
 *
 */
public interface LoginDao {
	public boolean createLogin(Login login);
	public boolean removeLogin(String username);
}
