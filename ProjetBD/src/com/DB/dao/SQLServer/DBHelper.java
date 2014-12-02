package com.DB.dao.SQLServer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.DB.model.Login;
import com.DB.model.User;

public class DBHelper {
		
	// Declare the JDBC objects.
	private Connection conn=null;   
	private PreparedStatement prepStmt;   
    
	//Creer une connection par le constructeur
    public DBHelper(Login login) throws ClassNotFoundException, SQLException{
    	// Create a variable for the connection string.
	      String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
	         "databaseName=M2_DB;user="+login.getLoginName()+";password="+login.getLoginPwd();

	   // Establish the connection.
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         conn = DriverManager.getConnection(connectionUrl);
    }   

	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}

	//remove() resilier la connection
    public void remove(){   
        try{   
            prepStmt.close();   
            conn.close();   
        }catch(SQLException e){   
            System.out.println(e.getMessage());   
        }   
    }   
       
    //query(String s) executer la requete
    public ResultSet query(String sql) throws SQLException{   
    	if(conn!=null){
    		System.out.println(sql);
    	}
    	prepStmt=conn.prepareStatement(sql);
        ResultSet rs=prepStmt.executeQuery();   
        return rs;   
    }   
    
    //query(String s) executer la requete
    public ResultSet query(PreparedStatement ps) throws SQLException{   
    	ResultSet rs=ps.executeQuery();   
        return rs; 
    }   
    
//update(String s) executer la mise a jour
 public void execSQL(String sql) throws SQLException{   
	 if(conn!=null){
  		System.out.println(sql);
  	}
      prepStmt=conn.prepareStatement(sql);
      prepStmt.executeUpdate();  
 }
 
//update(String s) executer la mise a jour
public void execSQL(PreparedStatement ps) throws SQLException{   
	ps.executeUpdate();   
}

    //handle the String
    public static String handleStr(String s)
    {
    	return "'"+s+"'";    	
    }
}
