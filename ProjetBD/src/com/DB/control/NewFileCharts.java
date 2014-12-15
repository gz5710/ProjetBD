package com.DB.control;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.DB.dao.FileDao;
import com.DB.dao.SQLServer.FileDaoImpl;

/**
 * Servlet implementation class NewFileCharts
 */
public class NewFileCharts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFileCharts() {
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
		// 1. get all the datas of files
		FileDao fileDao = new FileDaoImpl(Util.adminLogin);
		Map<String, Integer> mapFileType = fileDao.getChartFileType();
		Map<String, Integer> mapFileGroup = fileDao.getChartFileGroup();
		Map<String, Integer> mapFileView = fileDao.getChartFileView();
				
		// 2. generate a pie chart according File Type
		// Create a simple pie chart
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		Iterator itor = mapFileType.entrySet().iterator(); 
		while(itor.hasNext()){
			Entry e = (Entry) itor.next();
			pieDataset.setValue(e.getKey().toString() + " : " + e.getValue().toString() + " views", 
					Integer.parseInt(e.getValue().toString()));
		}
		JFreeChart chart = ChartFactory.createPieChart
				("Views on different File Types", // Title
						pieDataset, // Dataset
						true, // Show legend
						true, // Use tooltips
						false // Configure chart to generate URLs?
						);
		try {
			ChartUtilities.saveChartAsJPEG(new File(getServletContext().getRealPath("/") + Util.CHART_FILETYPE_PATH), chart,
					Util.chart_Width, Util.chart_Height);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
			e.printStackTrace();
			Util.dispatcherToErrorPage("Problem occurred creating chart : " + e.getMessage(), request, response, this);
		}
		
		
		// 3. generate a pie chart according File Group
		pieDataset.clear();
		itor = mapFileGroup.entrySet().iterator(); 
		while(itor.hasNext()){
			Entry e = (Entry) itor.next();
			pieDataset.setValue(e.getKey().toString() + " : " + e.getValue().toString() + " views", 
					Integer.parseInt(e.getValue().toString()));
		}
		chart = ChartFactory.createPieChart3D
				("Views on different File Group", // Title
						pieDataset, // Dataset
						true, // Show legend
						true, // Use tooltips
						false // Configure chart to generate URLs?
						);
		try {
			ChartUtilities.saveChartAsJPEG(new File(getServletContext().getRealPath("/") + Util.CHART_FILEGROUP_PATH), chart,
					Util.chart_Width, Util.chart_Height);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
			e.printStackTrace();
			Util.dispatcherToErrorPage("Problem occurred creating chart : " + e.getMessage(), request, response, this);
		}
		
		
		// 4. generate a chart according File Views
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		itor = mapFileView.entrySet().iterator(); 
		while(itor.hasNext()){
			Entry e = (Entry) itor.next();
			dataset.addValue(Integer.parseInt(e.getValue().toString()),
					e.getKey().toString() + " : " + e.getValue().toString() + " views", 
					e.getKey().toString()
					);
		}
		chart = ChartFactory.createBarChart(
				"Views on different Files",       // chart title
					"All the files",               // domain axis label
					"Views",                  // range axis label
					dataset,                  // data
					PlotOrientation.VERTICAL, // orientation
					true,                     // include legend
					true,                     // tooltips?
					false                     // URLs?
				);
		try {
			String path = getServletContext().getRealPath("/") + Util.CHART_FILEVIEW_PATH;
			System.out.println(path);
			ChartUtilities.saveChartAsJPEG(new File(path), chart,
					Util.chart_Width, Util.chart_Height);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
			e.printStackTrace();
			Util.dispatcherToErrorPage("Problem occurred creating chart : " + e.getMessage(), request, response, this);
		}
		
		// 5. go to File FileCharts.jsp
		response.sendRedirect("jsp/admin/FileCharts.jsp");
	}

}
