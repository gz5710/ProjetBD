package com.DB.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.dao.FileDao;
import com.DB.dao.RoleDao;
import com.DB.dao.SQLServer.FileDaoImpl;
import com.DB.dao.SQLServer.RoleDaoImpl;
import com.DB.model.File;
import com.DB.model.Role;
import com.DB.model.User;
import com.DB.model.View;

/**
 * Servlet implementation class DisplayFile
 */
public class DisplayFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayFile() {
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
		//1. get all the parameters(fileid)
		int fileId = Integer.parseInt(request.getParameter("fileid"));
		//2. get curUser from session
		HttpSession session = request.getSession(true);
		User curUser = (User) session.getAttribute("curUser");
		//3. get Views who belong to curUser with admin login
		RoleDao roleDao = new RoleDaoImpl(Util.adminLogin);
		Role role = roleDao.getRole(curUser.getUsername());
		List<View> views = roleDao.getViewsWithRole(role);
		//4. retrieve the file in the views with curUser login
		FileDao fileDao = new FileDaoImpl(curUser.getLogin());
		File file = fileDao.getFileInViews(views, fileId);
		//5. if not null, redirect to the view file page.
		if(file!=null)
		{
			response.sendRedirect("jsp/FileView.jsp?path="+file.getChemin());
		}else{
			//6. if null, go to error page
			Util.dispatcherToErrorPage("Sorry, you haven't right to view this file.", request, response, this);
		}
		
	}

}
