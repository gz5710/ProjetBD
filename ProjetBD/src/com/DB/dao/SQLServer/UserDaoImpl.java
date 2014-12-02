package com.DB.dao.SQLServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DB.dao.LogDao;
import com.DB.dao.UserDao;
import com.DB.model.Login;
import com.DB.model.Role;
import com.DB.model.User;

public class UserDaoImpl implements UserDao {

	private Login login = null;
	private DBHelper dbhelper = null;
	
	public UserDaoImpl(Login login) throws ClassNotFoundException, SQLException {
		super();
		this.login = login;
		this.dbhelper = new DBHelper(login);
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into Users(Username , Password , Titre, Email, DOB, Date_Inscription , Date_Dernier_Acces , SiPermis)"
				+ " values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getTitre());
			ps.setString(4, user.getEmail());
			ps.setDate(5, user.getDOB());
			ps.setDate(6, user.getDate_Inscription());
			ps.setDate(7, user.getDate_Dernier_Acces());
			ps.setInt(8, 0);
			dbhelper.execSQL(ps);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	

	@Override
	public boolean checkDuplication(String username) {
		// TODO Auto-generated method stub
		String sql = "select * from Users where Username = ?";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = dbhelper.query(ps);
			return rs.next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		String sql = "delete Users where Id = " + id;
		try {
			dbhelper.execSQL(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from Users where Username = ? and SiPermis = 1";
		PreparedStatement ps = null;
		User user = new User();
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = dbhelper.query(ps);
			while(rs.next())
			{
				user.setId(rs.getInt("Id"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setTitre(rs.getString("Titre"));
				user.setEmail(rs.getString("Email"));
				user.setDOB(rs.getDate("DOB"));
				user.setLogin(login);
				user.setDate_Inscription(rs.getDate("Date_Inscription"));
				user.setDate_Dernier_Acces(rs.getDate("Date_Dernier_Acces"));
				user.setSiPermis(rs.getBoolean("SiPermis"));
			}
			//return a valid user object.
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//otherwise, return null (probably non-registered, non-valid)
			return null;
		}
	}

	
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from Users where Id = ? and SiPermis = 1";
		PreparedStatement ps = null;
		User user = new User();
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = dbhelper.query(ps);
			while(rs.next())
			{
				user.setId(rs.getInt("Id"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setTitre(rs.getString("Titre"));
				user.setEmail(rs.getString("Email"));
				user.setDOB(rs.getDate("DOB"));
				user.setLogin(login);
				user.setDate_Inscription(rs.getDate("Date_Inscription"));
				user.setDate_Dernier_Acces(rs.getDate("Date_Dernier_Acces"));
				user.setSiPermis(rs.getBoolean("SiPermis"));
			}
			//return a valid user object.
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//otherwise, return null (probably non-registered, non-valid)
			return null;
		}
	}
	
	@Override
	public List<User> getInvalidUsers() {
		// TODO Auto-generated method stub
		String sql = "Select * from Users where SiPermis = 0";
		try {
			ResultSet rs = dbhelper.query(sql);
			List<User> users = new ArrayList<User>();
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("Id"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setTitre(rs.getString("Titre"));
				user.setEmail(rs.getString("Email"));
				user.setDOB(rs.getDate("DOB"));
				user.setLogin(login);
				user.setDate_Inscription(rs.getDate("Date_Inscription"));
				user.setDate_Dernier_Acces(rs.getDate("Date_Dernier_Acces"));
				user.setSiPermis(rs.getBoolean("SiPermis"));
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
	public void Login(User user) {
		// TODO Auto-generated method stub
		//1.update last access
		String sql = "update Users set Date_Dernier_Acces = ? where Id = ? ;";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setDate(1, user.getDate_Dernier_Acces());
			ps.setInt(2, user.getId());
			dbhelper.execSQL(ps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.store in log
		LogDao logDao = new LogDaoImpl(this.login);
		logDao.addLog(user);
	}

	@Override
	public List<User> getValidUsers() {
		// TODO Auto-generated method stub
		String sql = "Select * from Users where SiPermis = 1";
		try {
			ResultSet rs = dbhelper.query(sql);
			List<User> users = new ArrayList<User>();
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("Id"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setTitre(rs.getString("Titre"));
				user.setEmail(rs.getString("Email"));
				user.setDOB(rs.getDate("DOB"));
				user.setLogin(login);
				user.setDate_Inscription(rs.getDate("Date_Inscription"));
				user.setDate_Dernier_Acces(rs.getDate("Date_Dernier_Acces"));
				user.setSiPermis(rs.getBoolean("SiPermis"));
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
	public boolean createUser4Login(String username) {
		// TODO Auto-generated method stub
		String sql = "USE M2_DB; \n"
				+ "CREATE USER " + username + " FOR LOGIN " + username + "; \n";
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
	public boolean verifyUser(String username) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Users SET SiPermis = 1 WHERE Username = '" + username + "';\n";
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
	public boolean removeUser4Login(String username) {
		// TODO Auto-generated method stub
		String sql = "USE M2_DB; DROP USER " + username;
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
