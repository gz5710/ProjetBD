/**
 * 
 */
package com.DB.dao;

import com.DB.model.User;

/**
 * @author Bruce GONG
 *
 */
public interface LogDao {
	public boolean addLog(User user);
	public int getLogsCount(int userId);
}
