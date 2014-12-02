/**
 * 
 */
package com.DB.dao.SQLServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DB.dao.FileDao;
import com.DB.model.File;
import com.DB.model.Login;
import com.DB.model.MultiCritere;
import com.DB.model.View;

/**
 * @author Bruce GONG
 *
 */
public class FileDaoImpl implements FileDao {

	private Login login = null;
	private DBHelper dbhelper = null;
	
	public FileDaoImpl(Login login) {
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
	 * @see com.DB.dao.FileDao#uploadFile(com.DB.model.File, java.lang.String)
	 */
	@Override
	public boolean addFileRecord(File file) {
		// TODO Auto-generated method stub
		String sql = "insert into Files(Nom, Chemin, Autheur, Date_Pub, Descriptions, Tag, Vu, Type_File)"
				+ " values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, file.getNom());
			ps.setString(2, file.getChemin());
			ps.setString(3, file.getAutheur());
			ps.setDate(4, file.getDate_Pub());
			ps.setString(5, file.getDescription());
			ps.setString(6, file.getTag_View().getViewName());
			ps.setInt(7, file.getVu());
			ps.setString(8, file.getType_File());
			dbhelper.execSQL(ps);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

	/* (non-Javadoc)
	 * @see com.DB.dao.FileDao#updateFile(com.DB.model.File)
	 */
	@Override
	public boolean updateFile(File file) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Files "
				+ "SET Nom= ? ,"
				+ "Autheur= ? ,"
				+ "Descriptions= ? ,"
				+ "Tag= ? , "
				+ "Type_File= ? "
				+ "WHERE Id= ? ;";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setString(1, file.getNom());
			ps.setString(2, file.getAutheur());
			ps.setString(3, file.getDescription());
			ps.setString(4, file.getTag_View().getViewName());
			ps.setString(5, file.getType_File());
			ps.setInt(6, file.getId());
			dbhelper.execSQL(ps);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.FileDao#deleteFile(com.DB.model.File)
	 */
	@Override
	public boolean deleteFile(String chemin) {
		// TODO Auto-generated method stub
		java.io.File deleteFile=new java.io.File(chemin);
		if (deleteFile.isFile()) {
			deleteFile.delete();
			return true;
		}else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.FileDao#getFileById(int)
	 */
	@Override
	public File getFileById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from Files where Id= ? ;";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = dbhelper.query(ps);
			while(rs.next())
			{
				File file = new File();
				file.setId(rs.getInt("Id"));
				file.setNom(rs.getString("Nom"));
				file.setChemin(rs.getString("Chemin"));
				file.setAutheur(rs.getString("Autheur"));
				file.setDate_Pub(rs.getDate("Date_Pub"));
				file.setDescription(rs.getString("Descriptions"));
				file.setTag_View(new View(rs.getString("Tag")));
				file.setVu(rs.getInt("Vu"));
				file.setType_File(rs.getString("Type_File"));
				return file;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.DB.dao.FileDao#getAllFiles()
	 */
	@Override
	public List<File> getAllFiles() {
		// TODO Auto-generated method stub
		String sql = "Select * From Files;";
		try {
			ResultSet rs = dbhelper.query(sql);
			List<File> files = new ArrayList<File>();
			while(rs.next()){
				File file = new File();
				file.setId(rs.getInt("Id"));
				file.setNom(rs.getString("Nom"));
				file.setChemin(rs.getString("Chemin"));
				file.setAutheur(rs.getString("Autheur"));
				file.setDate_Pub(rs.getDate("Date_Pub"));
				file.setDescription(rs.getString("Descriptions"));
				file.setTag_View(new View(rs.getString("Tag")));
				file.setVu(rs.getInt("Vu"));
				file.setType_File(rs.getString("Type_File"));
				files.add(file);
			}
			return files;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<String> getAllFileTypes() {
		// TODO Auto-generated method stub
		String sql = "Select * from File_Type;";
		try {
			ResultSet rs = dbhelper.query(sql);
			List<String> fileTypes = new ArrayList<String>();
			while(rs.next()){
				fileTypes.add(rs.getString("Type"));
			}
			return fileTypes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean removeFileRecord(int id) {
		// TODO Auto-generated method stub
		String sql = "Delete Files Where Id = ? ";
		PreparedStatement ps = null;
		try {
			ps = dbhelper.getConn().prepareStatement(sql);
			ps.setInt(1, id);
			dbhelper.execSQL(ps);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<String> getAllAuthors() {
		// TODO Auto-generated method stub
		String sql = "Select DISTINCT(Autheur) From Files;";
		try {
			ResultSet rs = dbhelper.query(sql);
			List<String> authors = new ArrayList<String>();
			while(rs.next()){
				authors.add(rs.getString("Autheur"));
			}
			return authors;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<File> searchFilesByMultiCritere(MultiCritere mc) {
		// TODO Auto-generated method stub
		String sql = "Select * From Files " + mc.buildSQLWhere();
		try {
			ResultSet rs = dbhelper.query(sql);
			List<File> files = new ArrayList<File>();
			while(rs.next()){
				File file = new File();
				file.setId(rs.getInt("Id"));
				file.setNom(rs.getString("Nom"));
				file.setChemin(rs.getString("Chemin"));
				file.setAutheur(rs.getString("Autheur"));
				file.setDate_Pub(rs.getDate("Date_Pub"));
				file.setDescription(rs.getString("Descriptions"));
				file.setTag_View(new View(rs.getString("Tag")));
				file.setVu(rs.getInt("Vu"));
				file.setType_File(rs.getString("Type_File"));
				files.add(file);
			}
			return files;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public File getFileInViews(List<View> views, int id) {
		// TODO Auto-generated method stub
		String sql = "";
		for(int i = 0; i < views.size(); i++)
		{
			View v = views.get(i);
			if(i==0)
			{
				sql += " Select * from " + v.getViewName() + "  WHERE Id = " + id + " \n";
			}else{
				sql += " Union Select * from " + v.getViewName() + "  WHERE Id = " + id + " \n";
			}
		}
		if(sql.equals("")){
			return null;
		}else{
			try {
				ResultSet rs = dbhelper.query(sql);
				File file = new File();
				while(rs.next()){
					file.setId(rs.getInt("Id"));
					file.setNom(rs.getString("Nom"));
					file.setChemin(rs.getString("Chemin"));
					file.setAutheur(rs.getString("Autheur"));
					file.setDate_Pub(rs.getDate("Date_Pub"));
					file.setDescription(rs.getString("Descriptions"));
					file.setTag_View(new View(rs.getString("Tag")));
					file.setVu(rs.getInt("Vu"));
					file.setType_File(rs.getString("Type_File"));
					return file;
				}
				return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}

}
