/**
 * 
 */
package com.DB.dao;

import java.sql.Date;
import java.util.Map;

import com.DB.model.User;

/**
 * @author Bruce GONG
 *
 */
public interface LogDao {
	public boolean addLog(User user);
	public int getLogsCount(int userId);
	public Map<Date, Integer> getChartLog();
}
