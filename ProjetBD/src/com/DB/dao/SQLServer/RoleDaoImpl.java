/**
 * 
 */
package com.DB.dao.SQLServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DB.dao.RoleDao;
import com.DB.model.Login;
import com.DB.model.Role;
import com.DB.model.User;
import com.DB.model.View;

/**
 * @author Bruce GONG
 *
 */
public class RoleDaoImpl implements RoleDao {

	private DBHelper dbhelper = null;
	private Login login = null;
	
	public RoleDaoImpl(Login login) {
		super();
		// TODO Auto-generated constructor stub
		try {
			dbhelper = new DBHelper(login);
			this.login = login;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.RoleDao#getRole(int)
	 */
	@Override
	public Role getRole(String username) {
		// TODO Auto-generated method stub
		String sql = "select r.name as 'RoleName', r.createdate 'CreateDate', u.name as 'UserName' "
				+ "from sysmembers m, sysusers r, sysusers u "
				+ "where r.uid = m.groupuid "
				+ "		and u.uid = m.memberuid "
				+ "		and u.name = ? ";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = dbhelper.query(ps);
			Role role = null;
			while(rs.next())
			{
				role = new Role(rs.getString("RoleName"));
				role.setCreateTime(rs.getDate("CreateDate"));
			}
			return role;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.RoleDao#getAllRoles()
	 */
	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		String sql = "Select * From sysusers "
				+ "Where issqlrole = 1 "
				+ "		And createdate > '2014-11-24' "
				+ "Order by createdate desc;";
		try {
			ResultSet rs = dbhelper.query(sql);
			List<Role> roles = new ArrayList<Role>();
			while(rs.next())
			{
				Role role = new Role(rs.getString("name"));
				role.setCreateTime(rs.getDate("createdate"));
				roles.add(role);
			}
			return roles;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.RoleDao#addMember(com.DB.model.User)
	 */
	@Override
	public boolean addMember(Role role, String username) {
		// TODO Auto-generated method stub
		String sql = "sp_addrolemember ? , ?;";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, role.getRole());
			ps.setString(2, username);
			dbhelper.execSQL(ps);
			return true;			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.RoleDao#removeMember(com.DB.model.User)
	 */
	@Override
	public boolean removeMember(Role role, String username) {
		// TODO Auto-generated method stub
		String sql = "sp_droprolemember ? , ?;";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, role.getRole());
			ps.setString(2, username);
			dbhelper.execSQL(ps);
			return true;			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.RoleDao#getUsersInRole(com.DB.model.Role)
	 */
	@Override
	public List<User> getUsersInRole(Role role) {
		// TODO Auto-generated method stub
		String sql = "select u.name as 'UserName' "
				+ "from sysmembers m, sysusers r, sysusers u "
				+ "where r.uid=m.groupuid"
				+ " 	and u.uid=m.memberuid"
				+ "		and r.name= ? ;";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, role.getRole());
			ResultSet rs = dbhelper.query(ps);
			List<User> users = new ArrayList<User>();
			while(rs.next())
			{
				String username = rs.getString("UserName");
				User user = new UserDaoImpl(login).getUserByName(username);
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<View> getViewsWithRole(Role role) {
		// TODO Auto-generated method stub
		String sql = "SELECT \n"
				+ "		CASE database_permissions.class_desc\n"
				+ " 		WHEN 'SCHEMA' THEN schema_name(major_id)\n"
				+ " 		WHEN 'OBJECT_OR_COLUMN' THEN\n"
				+ "            CASE WHEN minor_id = 0 THEN object_name(major_id) COLLATE Latin1_General_CI_AS_KS_WS\n"
				+ "            ELSE (SELECT object_name(object_id) + ' ('+ name + ')'\n"
				+ "                  FROM sys.columns\n"
				+ "                  WHERE object_id = database_permissions.major_id\n"
				+ "                  AND column_id = database_permissions.minor_id) end\n"
				+ "        ELSE 'other'\n"
				+ "    END  as 'View'\n"
				+ "    , database_principals.name COLLATE Latin1_General_CI_AS_KS_WS as 'Role'\n"
				+ "FROM sys.database_permissions\n"
				+ "JOIN sys.database_principals\n"
				+ "ON database_permissions.grantee_principal_id = database_principals.principal_id\n"
				+ "LEFT JOIN sys.objects -- consider schemas\n"
				+ "ON objects.object_id = database_permissions.major_id\n"
				+ "WHERE database_permissions.major_id > 0\n"
				+ "AND permission_name in ('SELECT','INSERT','UPDATE','DELETE')\n"
				+ "AND database_principals.name = ? ;";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, role.getRole());
			ResultSet rs = dbhelper.query(ps);
			List<View> views = new ArrayList<View>();
			while(rs.next())
			{
				String viewname = rs.getString("View");
				View view = new View(viewname);
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
	public boolean createRole(String rolename) {
		// TODO Auto-generated method stub
		String sql = "CREATE ROLE " + rolename;
		try {
			dbhelper.execSQL(sql);
			return true;			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateViews4Role(Role role) {
		// TODO Auto-generated method stub
		String sql = "";
		//delete all the permission of this role.
		if(removeViewsOfRole(role))
		{
			for(View v : role.getViews())
			{
				sql += "GRANT SELECT ON " + v.getViewName() + " TO " + role.getRole() + ";\n";
			}
			if(!sql.equals(""))
			{
				try {
					dbhelper.execSQL(sql);
					return true;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}else{
				return true;
			}
		}else{
			return false;
		}
	}

	@Override
	public boolean removeViewsOfRole(Role role) {
		// TODO Auto-generated method stub
		List<View> views = getViewsWithRole(role);
		String sql = "";
		for(View v : views)
		{
			sql += "REVOKE SELECT ON " + v.getViewName() + " TO " + role.getRole() + ";\n";
		}
		if(!sql.equals(""))
		{
			try {
				dbhelper.execSQL(sql);
				return true;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}else{
			return true;
		}
	}

	@Override
	public boolean deleteRole(String rolename) {
		// TODO Auto-generated method stub
		String sql = "DROP ROLE " + rolename;
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
