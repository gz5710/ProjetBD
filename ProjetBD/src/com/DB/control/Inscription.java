package com.DB.control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.dao.UserDao;
import com.DB.dao.SQLServer.UserDaoImpl;
import com.DB.model.User;

/**
 * Servlet implementation class Inscription
 */
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
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
		System.out.println("Inscription...");
		
		//get all the parameters
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		String titre = request.getParameter("Titre");
		String email = request.getParameter("Email");
		System.out.println(request.getParameter("DOB"));
		Date DOB = Date.valueOf(request.getParameter("DOB"));
		System.out.println(DOB.toString());
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setTitre(titre);
		user.setEmail(email);
		user.setDOB(DOB);
		user.setDate_Inscription(Util.getCurDate());
		user.setDate_Dernier_Acces(Util.getCurDate());
		//System.out.println(user.toString());
		
		//first enroll using admin acount.
		user.setLogin(Util.adminLogin);
		UserDao userDao = null;
		try {
			userDao = new UserDaoImpl(user.getLogin());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.dispatcherToErrorPage("You haven't right to action.", request, response, this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.dispatcherToErrorPage("You haven't right to action.", request, response, this);
		}
		//check the possible duplication
		if(!userDao.checkDuplication(user.getUsername()))
		{
			//persist in database
			userDao.addUser(user);			
			//persist in session
			HttpSession session = request.getSession(true);
			session.setAttribute("User", user);
			Util.dispatcherToPage("/jsp/SuccessInscription.jsp", request, response, this);
		}else{
			Util.dispatcherToErrorPage("Sorry, this username is used !", request, response, this);
			System.out.println("Test");
		}
		
	}

}
