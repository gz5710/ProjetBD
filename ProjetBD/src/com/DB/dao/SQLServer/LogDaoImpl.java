/**
 * 
 */
package com.DB.dao.SQLServer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.DB.dao.LogDao;
import com.DB.model.Login;
import com.DB.model.User;

/**
 * @author Bruce GONG
 *
 */
public class LogDaoImpl implements LogDao {

	private Login login = null;
	private DBHelper dbhelper = null;
	
	public LogDaoImpl(Login login) {
		super();
		this.login = login;
		try {
			this.dbhelper = new DBHelper(login);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.LogDao#addLog(com.DB.model.User)
	 */
	@Override
	public boolean addLog(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into Log(Time_Login, Id_User) values( ? , ? );";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setDate(1, user.getDate_Dernier_Acces());
			ps.setInt(2, user.getId());
			dbhelper.execSQL(ps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.LogDao#getLogs(int)
	 */
	@Override
	public int getLogsCount(int userId) {
		// TODO Auto-generated method stub
		String sql = "select COUNT(*) as Freq from Log where Id_User= ? ;";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = dbhelper.query(ps);
			int freq = 0;
			while(rs.next()){
				freq = rs.getInt("Freq");
			}
			return freq;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Map<Date, Integer> getChartLog() {
		// TODO Auto-generated method stub
		String sql = "select Time_Login, COUNT(Time_Login) as data from Log group by Time_Login";
		try {
			ResultSet rs = dbhelper.query(sql);
			Map<Date, Integer> map = new HashMap<Date, Integer>();
			while(rs.next()){
				map.put(rs.getDate("Time_Login"), rs.getInt("data"));
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
