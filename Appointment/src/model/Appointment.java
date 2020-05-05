package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","yasiya");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertAppointment(String no, String type, String date, String did, String desc)
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement    
			String query = " insert into appointments1(`appID`,`appNo`,`appType`,`appDate`,`appDocID`,`appDesc`)" + " values (?, ?, ?, ?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, no);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, did);
			preparedStmt.setString(6, desc);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newAppointments = readAppointments(); 
			output =  "{\"status\":\"success\", \"data\": \"" +        
							newAppointments + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the appointment.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readAppointments()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>App - No</th><th>App Type</th><th>App Date</th><th>App DocID</th><th>App Desc</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from appointments1";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
					String appID = Integer.toString(rs.getInt("appID"));
					String appNo = rs.getString("appNo");
					String appType = rs.getString("appType");
					String appDate = rs.getString("appDate");
					String appDocID = rs.getString("appDocID");
					String appDesc = rs.getString("appDesc");
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidAppIDUpdate\' name=\'hidAppIDUpdate\' type=\'hidden\' value=\'" + appID + "'>" 
							+ appNo + "</td>";      
				output += "<td>" + appType + "</td>";     
				output += "<td>" + appDate + "</td>";     
				output += "<td>" + appDocID + "</td>"; 
				output += "<td>" + appDesc + "</td>";
	 
				// buttons     
//				output += "<td><input name=\'btnUpdate\' type=\'button\' value=\'Update\' class=\' btnUpdate btn btn-secondary\'></td>"
//						+ "<td><form method=\"post\" action=\"items.jsp\">      "
//						+ "<input name=\'btnRemove\' type=\'submit\' value=\'Remove\' class=\'btn btn-danger\'> "
//						+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + itemID + "\">" + "</form></td></tr>"; 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-appid='" 
						+ appID + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the appointments.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	public String updateAppointment(String id, String no, String type, String date, String did, String desc) 
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE appointments1 SET appNo=?,appType=?,appDate=?,appDocID=?,appDesc=? WHERE appID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, no);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, did);
			preparedStmt.setString(5, desc);
			preparedStmt.setInt(6, Integer.parseInt(id));
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newAppointments = readAppointments();    
			output = "{\"status\":\"success\", \"data\": \"" +        
									newAppointments + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the appointment.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteAppointment(String appID)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement    
			String query = "delete from appointments1 where appID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(appID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newAppointments = readAppointments();    
			output = "{\"status\":\"success\", \"data\": \"" +       
								newAppointments + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the appointment.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	 
}



