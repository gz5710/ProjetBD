package com.DB.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.dao.RoleDao;
import com.DB.dao.SQLServer.RoleDaoImpl;
import com.DB.model.Role;

/**
 * Servlet implementation class DeleteUserGroup
 */
public class DeleteUserGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserGroup() {
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
		//1. get all the parameters(rolename)
		String roleName = request.getParameter("rolename");
		//2. check if it has members
		Role role = new Role(roleName);
		RoleDao roleDao = new RoleDaoImpl(Util.adminLogin);
		if(roleDao.getUsersInRole(role).size()==0){
			if(roleDao.deleteRole(roleName))
			{
				//3. if not, drop this role, and back to user list page.
				response.sendRedirect("jsp/admin/Group of users.jsp");
			}else{
				Util.dispatcherToErrorPage("Something wrong in deleting this user group.", request, response, this);
			}
		}else{
			//4. if yes, remind administrator that a role with member(s) can't be deleted.
			Util.dispatcherToErrorPage("Sorry, you aren't allowed to delete a user group with user(s)."
					+ " Please ensure you have transfered all the user(s) before deleting.",
					request, response, this);
		}
	}

}
