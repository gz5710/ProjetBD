package com.DB.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.dao.UserDao;
import com.DB.dao.SQLServer.UserDaoImpl;

/**
 * Servlet implementation class RefuseInscription
 */
public class RefuseInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefuseInscription() {
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
		System.out.println("Refuse a new inscription - UserId = "+request.getParameter("Id"));
		//1.receive all the parameters(User's Id)
		int Id = Integer.parseInt(request.getParameter("Id"));
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		//2.delete this record in table Users
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
		if(userDao.deleteUser(Id)){
			//3.return to page New users.jsp
			response.sendRedirect("jsp/admin/New users.jsp");
		}else{
			//5.otherwise, go to error page with Error message
			Util.dispatcherToErrorPage("Something wrong in deleting this new user", request, response, this);
		}
	}

}
