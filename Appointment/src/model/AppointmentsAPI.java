package model;

import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/AppointmentsAPI") 
public class AppointmentsAPI extends HttpServlet 
{  
	private static final long serialVersionUID = 1L;
	
	Appointment AppointmentObj = new Appointment();
	
	public AppointmentsAPI()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)    
			throws ServletException, IOException 
	{
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)    
			throws ServletException, IOException 
	{  
		String output = AppointmentObj.insertAppointment(request.getParameter("appNo"),      
				request.getParameter("appType"),     
				request.getParameter("appDate"),
				request.getParameter("appDocID"), 
				request.getParameter("appDesc")); 
	 
	 response.getWriter().write(output); 
	 
	} 
	 
	protected void doPut(HttpServletRequest request, HttpServletResponse response)    
			throws ServletException, IOException 
	{  
		Map paras = getParasMap(request); 
	 
	 String output = AppointmentObj.updateAppointment(paras.get("hidAppIDSave").toString(),     
			 paras.get("appNo").toString(),     
			 paras.get("appType").toString(),  
			 paras.get("appDate").toString(),	
			 paras.get("appDocID").toString(),        
			 paras.get("appDesc").toString()); 
	 
	 response.getWriter().write(output); } 
	 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)    
			throws ServletException, IOException 
	{  
		Map paras = getParasMap(request); 
	 
	 String output = AppointmentObj.deleteAppointment(paras.get("appID").toString()); 
	 
	 response.getWriter().write(output); 
	}
	
	// Convert request parameters to a Map 
	private static Map getParasMap(HttpServletRequest request) 
	{  
		Map<String, String> map = new HashMap<String, String>();  
		try  
		{   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?          
					scanner.useDelimiter("\\A").next() : "";  
					scanner.close(); 
	 
	  String[] params = queryString.split("&");   
	  for (String param : params)   
	  { 

 
	   String[] p = param.split("=");    
	   map.put(p[0], p[1]);   
	   }  
	  }  
		catch (Exception e)  
		{  
			
		}  return map; }

	
}
