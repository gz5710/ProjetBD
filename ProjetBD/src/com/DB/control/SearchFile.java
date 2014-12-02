package com.DB.control;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.dao.FileDao;
import com.DB.dao.SQLServer.FileDaoImpl;
import com.DB.model.File;
import com.DB.model.MultiCritere;

/**
 * Servlet implementation class SearchFile
 */
public class SearchFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFile() {
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
		try {
			//1. get all the parameters(filetype, keywords, fromdatepub, todatepub, author)
			String fileType = request.getParameter("filetype");
			String keywords = request.getParameter("keywords");
			Date fromDatePub = null;
			Date toDatePub = null;
			String from = request.getParameter("fromdatepub");
			String to = request.getParameter("todatepub");
			if(!from.equals("") && !to.equals(""))
			{
				fromDatePub = Date.valueOf(from);
				toDatePub = Date.valueOf(to);
			}
			String author = request.getParameter("author");
			//2. build an object of MultiCritere
			MultiCritere mc = new MultiCritere(fileType, keywords, fromDatePub, toDatePub, author);
			//3. get the fileList which meets all the conditions.
			FileDao fileDao = new FileDaoImpl(Util.adminLogin);
			List<File> fileList = fileDao.searchFilesByMultiCritere(mc);
			//4. store the fileList in session.
			HttpSession hs = request.getSession();
			hs.setAttribute("fileList", fileList);
			hs.setAttribute("MultiCritere", mc);
			//5. return back to search page.
			response.sendRedirect("jsp/FileSearch.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.dispatcherToErrorPage("Search failed : " + e.getMessage(), request, response, this);
		}
		
	}

}
