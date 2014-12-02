package com.DB.control;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.DB.dao.FileDao;
import com.DB.dao.SQLServer.FileDaoImpl;
import com.DB.model.View;

/**
 * Servlet implementation class NewFile
 */
public class NewFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFile() {
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
		//1. get all the parameters(name,author,descriptions,filegroup,filetype,file)
		Hashtable ht = new Hashtable();
		String chemin = null;
		//2. upload this file;
		//process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        chemin = Util.UPLOAD_DIRECTORY + File.separator + name;
                        item.write(new File(chemin));
                    }else{
                    	String fieldname = item.getFieldName();
                        String fieldvalue = item.getString();
                        ht.put(fieldname, fieldvalue);
                    }
                }
               //File uploaded successfully
                //3. build an object of File
                com.DB.model.File file = new com.DB.model.File();
                file.setAutheur(ht.get("author").toString());
                file.setChemin(chemin);
                file.setDate_Pub(Util.getCurDate());
                file.setDescription(ht.get("descriptions").toString());
                file.setNom(ht.get("name").toString());
                file.setTag_View(new View(ht.get("filegroup").toString()));
                file.setType_File(ht.get("filetype").toString());
                file.setVu(0);
        		//4. store in database
                FileDao fileDao = new FileDaoImpl(Util.adminLogin);
                if(fileDao.addFileRecord(file))
                {
            		//5. back to file list page
                	response.sendRedirect("jsp/admin/Files.jsp");
                }else{
                	Util.dispatcherToErrorPage("Failure in saving in database.", request, response, this);
                }
            } catch (Exception ex) {
               Util.dispatcherToErrorPage("File Upload Failed due to " + ex, request, response, this);
            }
        }else{
            Util.dispatcherToErrorPage("Sorry this Servlet only handles file upload request", request, response, this);
        }
	}

}
