/**
 * 
 */
package com.DB.dao.SQLServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DB.dao.ViewDao;
import com.DB.model.Login;
import com.DB.model.Role;
import com.DB.model.View;

/**
 * @author Bruce GONG
 *
 */
public class ViewDaoImpl implements ViewDao {
	private Login login = null;
	private DBHelper dbhelper = null;
	
	public ViewDaoImpl(Login login) {
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
	 * @see com.DB.dao.ViewDao#getRolesWithView(com.DB.model.View)
	 */
	@Override
	public List<Role> getRolesWithView(View view) {
		// TODO Auto-generated method stub
		String sql = "Select *\n"
				+ "From (SELECT\n"
				+ "    CASE database_permissions.class_desc\n"
				+ "        WHEN 'SCHEMA' THEN schema_name(major_id)\n"
				+ "        WHEN 'OBJECT_OR_COLUMN' THEN\n"
				+ "            CASE WHEN minor_id = 0 THEN object_name(major_id) COLLATE Latin1_General_CI_AS_KS_WS\n"
				+ "            ELSE (SELECT object_name(object_id) + ' ('+ name + ')'\n"
				+ "                  FROM sys.columns\n"
				+ "                  WHERE object_id = database_permissions.major_id\n"
				+ "                  AND column_id = database_permissions.minor_id) end\n"
				+ "        ELSE 'other'\n"
				+ "    END as 'v'\n"
				+ "    , database_principals.name COLLATE Latin1_General_CI_AS_KS_WS as 'r'\n"
				+ "FROM sys.database_permissions\n"
				+ "JOIN sys.database_principals\n"
				+ "ON database_permissions.grantee_principal_id = database_principals.principal_id\n"
				+ "LEFT JOIN sys.objects -- consider schemas\n"
				+ "ON objects.object_id = database_permissions.major_id\n"
				+ "WHERE database_permissions.major_id > 0\n"
				+ "AND permission_name in ('SELECT','INSERT','UPDATE','DELETE')) as T\n"
				+ "Where T.v = ? ;";
		System.out.println(sql);
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, view.getViewName());
			ResultSet rs = dbhelper.query(ps);
			List<Role> roles = new ArrayList<Role>();
			while(rs.next())
			{
				String rolename = rs.getString("r");
				Role role = new Role(rolename);
				roles.add(role);
			}
			return roles;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<View> getAllViews() {
		// TODO Auto-generated method stub
		String sql = "select * from sys.views;";
		try {
			ResultSet rs = dbhelper.query(sql);
			List<View> views = new ArrayList<View>();
			while(rs.next())
			{
				View view = new View(rs.getString("name"));
				views.add(view);
			}
			return views;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean deleteView(String viewname) {
		// TODO Auto-generated method stub
		String sql = "DROP VIEW " + viewname;
		try {
			dbhelper.execSQL(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean createView(String viewname) {
		// TODO Auto-generated method stub
		String sql = "create view "+viewname+"\n"
				+ "as\n"
				+ "select *\n"
				+ "from Files\n"
				+ "where Tag='"+viewname+"';";
		try {
			dbhelper.execSQL(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
