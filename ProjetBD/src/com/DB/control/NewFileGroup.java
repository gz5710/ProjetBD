package com.DB.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.dao.ViewDao;
import com.DB.dao.SQLServer.ViewDaoImpl;

/**
 * Servlet implementation class NewFileGroup
 */
public class NewFileGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFileGroup() {
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
		//1. get all parameters(viewname)
		String viewName = request.getParameter("viewname");
		//2. create a new file group
		ViewDao viewDao = new ViewDaoImpl(Util.adminLogin);
		if(viewDao.createView(viewName)){
			//3. back to file group list page
			response.sendRedirect("jsp/admin/Group of files.jsp");
		}else{
			Util.dispatcherToErrorPage("Something wrong in creating file group.", request, response, this);
		}
	}

}
