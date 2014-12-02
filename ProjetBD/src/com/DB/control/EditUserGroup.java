package com.DB.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.dao.RoleDao;
import com.DB.dao.SQLServer.RoleDaoImpl;
import com.DB.model.Role;
import com.DB.model.View;

/**
 * Servlet implementation class EditUserGroup
 */
public class EditUserGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserGroup() {
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
		//1. get all the parameters(rolename, views)
		String roleName = request.getParameter("rolename");
		String[] views = request.getParameterValues("views");
		//2. update views of this role
		Role role = new Role(roleName);
		RoleDao roleDao = new RoleDaoImpl(Util.adminLogin);
		for(String v : views)
		{
			View view = new View(v);
			role.addView(view);
		}
		if(roleDao.updateViews4Role(role))
		{
			//3. return to user group list
			response.sendRedirect("jsp/admin/Group of users.jsp");
		}else{
			Util.dispatcherToErrorPage("Something wrong in updating file groups of this user group.", request, response, this);
		}
	}

}
