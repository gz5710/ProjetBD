package com.DB.control;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.model.Login;

public class Util {
	
	public final static Login adminLogin = new Login("sa", "5710640");
	public final static String UPLOAD_DIRECTORY = "\\dossier";
	public final static String CHART_FILETYPE_PATH = "charts\\filetype.jpg";
	public final static String CHART_FILEGROUP_PATH = "charts\\filegroup.jpg";
	public final static String CHART_FILEVIEW_PATH = "charts\\fileview.jpg";
	public final static String CHART_USER_PATH = "charts\\user.jpg";
	public final static int chart_Width = 1000;
	public final static int chart_Height = 500;
	
	public static void dispatcherToErrorPage(String ErrMsg, HttpServletRequest request, HttpServletResponse response, HttpServlet servlet)
	{
		request.setAttribute("ErrorMsg", ErrMsg);
		try {
			servlet.getServletContext().getRequestDispatcher("/jsp/ErrorPage.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void dispatcherToPage(String Page, HttpServletRequest request, HttpServletResponse response, HttpServlet servlet)
	{
		try {
			servlet.getServletContext().getRequestDispatcher(Page).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static Date getCurDate()
	{
		Date curDate = new Date(new java.util.Date().getTime());
		return curDate;
	}
}
