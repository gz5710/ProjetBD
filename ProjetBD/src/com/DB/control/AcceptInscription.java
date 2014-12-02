package com.DB.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.dao.LoginDao;
import com.DB.dao.UserDao;
import com.DB.dao.SQLServer.LoginDaoImpl;
import com.DB.dao.SQLServer.UserDaoImpl;
import com.DB.model.Login;
import com.DB.model.User;

/**
 * Servlet implementation class AcceptInscription
 */
public class AcceptInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptInscription() {
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
		System.out.println("Accept the new inscription - Username = "+request.getParameter("Username"));
		//1.receive all the parameters(User's Id)
		int Id = Integer.parseInt(request.getParameter("Id"));
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		//2.create a corresponding login in SQLServer with admin's authentication
		//3.change User's SiPermis in table Users
		LoginDao loginDao = new LoginDaoImpl(Util.adminLogin);
		UserDao userDao = null;
		try {
			userDao = new UserDaoImpl(Util.adminLogin);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.dispatcherToErrorPage("You haven't right to action.", request, response, this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.dispatcherToErrorPage("You haven't right to action.", request, response, this);
		}
		if(loginDao.createLogin(new Login(username, password))
				&& userDao.createUser4Login(username)
				&& userDao.verifyUser(username)){
			//4.return to UserChangeGroup.jsp
			response.sendRedirect("jsp/admin/UserChangeGroup.jsp?username="+username);
		}else{
			//5.otherwise, go to error page with Error message
			Util.dispatcherToErrorPage("Something wrong in validation of this new user", request, response, this);
		}
	}

}
