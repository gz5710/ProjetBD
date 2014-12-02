/**
 * 
 */
package com.DB.dao.SQLServer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.DB.dao.LoginDao;
import com.DB.model.Login;

/**
 * @author Bruce GONG
 *
 */
public class LoginDaoImpl implements LoginDao {

	private DBHelper dbhelper = null;
	public LoginDaoImpl(Login login) {
		super();
		// TODO Auto-generated constructor stub
		try {
			dbhelper = new DBHelper(login);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.LoginDao#createLogin(com.DB.model.Login)
	 */
	@Override
	public boolean createLogin(Login login) {
		// TODO Auto-generated method stub
		String sql = "CREATE LOGIN "+login.getLoginName()+" WITH PASSWORD = '"+login.getLoginPwd()+"', "
				+ "DEFAULT_DATABASE = M2_DB , DEFAULT_LANGUAGE = us_english, CHECK_POLICY= OFF; \n";		
		try {
			dbhelper.execSQL(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.LoginDao#removeLogin(com.DB.model.Login)
	 */
	@Override
	public boolean removeLogin(String username) {
		// TODO Auto-generated method stub
		String sql = "DROP LOGIN " + username;
		try {
			dbhelper.execSQL(sql);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
