package com.DB.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.dao.LoginDao;
import com.DB.dao.RoleDao;
import com.DB.dao.UserDao;
import com.DB.dao.SQLServer.LoginDaoImpl;
import com.DB.dao.SQLServer.RoleDaoImpl;
import com.DB.dao.SQLServer.UserDaoImpl;
import com.DB.model.Role;

/**
 * Servlet implementation class DeleteUser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.get all the parameters(Id)
		int Id = Integer.parseInt(request.getParameter("Id"));
		String username = request.getParameter("Username");
		System.out.println(Id);
		System.out.println(username);
		//2.delete user in table Users
		try{
			UserDao userDao = new UserDaoImpl(Util.adminLogin);
			RoleDao roleDao = new RoleDaoImpl(Util.adminLogin);
			LoginDao loginDao = new LoginDaoImpl(Util.adminLogin);
			if(userDao.deleteUser(Id)){
				Role role = roleDao.getRole(username);
				//3.drop member in role
				if(roleDao.removeMember(role, username)){
					//4.drop user in database
					if(userDao.removeUser4Login(username)){
						//5.drop login in DBMS
						if(loginDao.removeLogin(username)){
							//6.return userlist page
							response.sendRedirect("jsp/admin/Users.jsp");
						}
					}
				}
			}
		}catch(Exception e)
		{
			Util.dispatcherToErrorPage("Something wrong in deleting this user : "+e.getMessage(), request, response, this);
		}
	}

}
