package com.DB.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.dao.FileDao;
import com.DB.dao.SQLServer.FileDaoImpl;
import com.DB.model.File;
import com.DB.model.View;

/**
 * Servlet implementation class EditFile
 */
public class EditFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFile() {
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
		//1. get all the paramters(fileid, name, author, descriptions, filegroup, filetype)
		int id = Integer.parseInt(request.getParameter("fileid"));
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String descriptions = request.getParameter("descriptions");
		String fileGroup = request.getParameter("filegroup");
		String fileType = request.getParameter("filetype");
		//2. build an object of file
		File file = new File();
		file.setId(id);
		file.setNom(name);
		file.setAutheur(author);
		file.setDescription(descriptions);
		file.setTag_View(new View(fileGroup));
		file.setType_File(fileType);
		//3. update this file record
		FileDao fileDao = new FileDaoImpl(Util.adminLogin);
		if(fileDao.updateFile(file))
		{
			//4. back to file list page
			response.sendRedirect("jsp/admin/Files.jsp");
		}else{
			Util.dispatcherToErrorPage("Failed to update.", request, response, this);
		}
	}

}
