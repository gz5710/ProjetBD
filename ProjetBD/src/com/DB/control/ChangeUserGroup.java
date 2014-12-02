package com.DB.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.dao.RoleDao;
import com.DB.dao.UserDao;
import com.DB.dao.SQLServer.RoleDaoImpl;
import com.DB.dao.SQLServer.UserDaoImpl;
import com.DB.model.Role;
import com.DB.model.User;

/**
 * Servlet implementation class ChangeUserGroup
 */
public class ChangeUserGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserGroup() {
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
		//1.get all the parameters(UserName, FromGroup, ToGroup)
		String username = request.getParameter("UserName");
		String fromGroup = request.getParameter("FromGroup");
		String toGroup = request.getParameter("ToGroup");	//option
		
		if(fromGroup != toGroup)	//if changed
		{
			Role preRole = new Role(fromGroup);
			Role nextRole = new Role(toGroup);
			RoleDao roleDao = new RoleDaoImpl(Util.adminLogin);
			if(!fromGroup.equals("NONE"))
			{//2-1.release the previous relation and build a new one
				if(roleDao.removeMember(preRole, username) &&
						roleDao.addMember(nextRole, username))
				{
					//3.go to userlist page
					response.sendRedirect("jsp/admin/Users.jsp");
				}else{
					Util.dispatcherToErrorPage("Can't change his group.", request, response, this);
				}
			}else{//2-2.build a new one
				if(roleDao.addMember(nextRole, username))
				{
					//3.go to userlist page
					response.sendRedirect("jsp/admin/Users.jsp");
				}else{
					Util.dispatcherToErrorPage("Can't add a group to him.", request, response, this);
				}
			}
		}
		
	}

}
