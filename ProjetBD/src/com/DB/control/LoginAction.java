package com.DB.control;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.dao.UserDao;
import com.DB.dao.SQLServer.UserDaoImpl;
import com.DB.model.Login;
import com.DB.model.User;

/**
 * Servlet implementation class Login
 */
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
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
		//1.get all the parameters
		String username = request.getParameter("Username").toString();
		String password = request.getParameter("Password").toString();
		//2.get an user by name
		Login login = new Login(username, password);
		UserDao userDao;
		try {
			userDao = new UserDaoImpl(login);
			//3.if not exception, login success, change admin's login to manipulate other tables
			userDao = new UserDaoImpl(Util.adminLogin);
			User user = userDao.getUserByName(username);
			//4.store in session
			user.setLogin(login);
			HttpSession session = request.getSession(true);
			session.setAttribute("curUser", user);
			if(login.equals(Util.adminLogin))
			{
				//3-1.administrator
				Util.dispatcherToPage("/jsp/admin/adminIndex.html", request, response, this);
			}else{
				//3-2.normal user
				//3-2-1.update Le dernier acces, add a log
				Date curDate = new Date(new java.util.Date().getTime());
				user.setDate_Dernier_Acces(curDate);
				userDao.Login(user);
				//3-2-2.go to FileSearch page
				response.sendRedirect("jsp/FileSearch.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//5.if null, login failed, go to error page.
			Util.dispatcherToErrorPage("Something wrong in your Username or Password", request, response, this);
		}
	}

}
