package com.DB.control;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

import com.DB.dao.FileDao;
import com.DB.dao.LogDao;
import com.DB.dao.SQLServer.FileDaoImpl;
import com.DB.dao.SQLServer.LogDaoImpl;

/**
 * Servlet implementation class NewUserChart
 */
public class NewUserChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserChart() {
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
		LogDao logDao = new LogDaoImpl(Util.adminLogin);
		Map<Date, Integer> mapLogin = logDao.getChartLog();
				
		// 2. generate a pie chart according File Type
		// Create a simple pie chart
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		//dataset.setDomainIsPointsInTime(true);
		TimeSeries s1 = new TimeSeries("Amount Access", Day.class);
		Iterator<Entry<Date, Integer>> itor = mapLogin.entrySet().iterator(); 
		while(itor.hasNext()){
			Entry<Date, Integer> e = (Entry<Date, Integer>) itor.next();
			//System.out.println(e.getKey() + " =  " + e.getValue());
			s1.add(new Day(e.getKey()), e.getValue());
		}
		dataset.addSeries(s1);
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"User Login Chart",
		            "Date", 
		            "Access",
		            dataset,
		            true,
		            true,
		            false
				);
		XYPlot plot = (XYPlot) chart.getPlot(); 
	    //plot.setAxisOffset(new RectangleInsets(5.0, 10.0, 10.0, 5.0));  
	    plot.setDomainCrosshairVisible(true);
	    plot.setRangeCrosshairVisible(true);
	    XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plot.getRenderer();
	    renderer.setBaseShapesVisible(true);
	    renderer.setBaseShapesFilled(true);
	    //NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();        
	    //numberAxis.setRange(new Range(0,10));   
	    DateAxis axis = (DateAxis) plot.getDomainAxis();
	    axis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy"));
	    axis.setAutoTickUnitSelection(false);
	    axis.setVerticalTickLabels(true);
		try {
			ChartUtilities.saveChartAsJPEG(new File(getServletContext().getRealPath("/") + Util.CHART_USER_PATH), chart,
					Util.chart_Width, Util.chart_Height);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
			e.printStackTrace();
			Util.dispatcherToErrorPage("Problem occurred creating chart : " + e.getMessage(), request, response, this);
		}
		response.sendRedirect("jsp/admin/UserChart.jsp");
	}
}
