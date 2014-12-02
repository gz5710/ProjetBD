package com.DB.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.dao.FileDao;
import com.DB.dao.SQLServer.FileDaoImpl;

/**
 * Servlet implementation class DeleteFile
 */
public class DeleteFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFile() {
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
		//1. get all the parameters (fileid, chemin)
		int fileId = Integer.parseInt(request.getParameter("fileid"));
		String chemin = request.getParameter("chemin");
		//2. delete the file
		FileDao fileDao = new FileDaoImpl(Util.adminLogin);
		try {
			if(fileDao.deleteFile(chemin)){
				//3. delete the record in database
				if (fileDao.removeFileRecord(fileId)) {
					//4. back to the file list page
					response.sendRedirect("jsp/admin/Files.jsp");
				} else {
					Util.dispatcherToErrorPage("Failed to delete logical file in database.", request, response, this);
				}
			}else{
				Util.dispatcherToErrorPage("Failed to delete physic file.", request, response, this);				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.dispatcherToErrorPage("Failed due to " + e.getMessage(), request, response, this);
		}
	}

}
