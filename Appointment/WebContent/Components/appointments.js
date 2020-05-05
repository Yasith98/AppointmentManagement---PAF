$(document).ready(function() 
		{  
	if ($("#alertSuccess").text().trim() == "")  
    {   
		$("#alertSuccess").hide();  
     }  
	     $("#alertError").hide(); 
	  
});

$(document).on("click", "#btnSave", function(event) 
		{  
			$("#alertSuccess").text("");  
			$("#alertSuccess").hide();  
			$("#alertError").text("");  
			$("#alertError").hide(); 
			
			
			var status = validateAppForm();  
			if (status != true)  
			{   
				$("#alertError").text(status);   
				$("#alertError").show();   
				return;  
			} 
			
			var type = ($("#hidAppIDSave").val() == "") ? "POST" : "PUT"; 
			
			$.ajax( 
			{  
				url : "AppointmentsAPI",  
				type : type,  
				data : $("#formApp").serialize(),  
				dataType : "text",  
				complete : function(response, status)  
				{   
					onAppSaveComplete(response.responseText, status);  
					
				} 
			
		}); 
}); 
		
function onAppSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divAppsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

		} else if (status == "error")  
		{   
			$("#alertError").text("Error while saving.");   
			$("#alertError").show();  
		} else  
		{   
			$("#alertError").text("Unknown error while saving..");   
			$("#alertError").show();  
		} 

		$("#hidAppIDSave").val("");  
		$("#formApp")[0].reset(); 
		
}

$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "AppointmentsAPI",   
		type : "DELETE",   
		data : "appID=" + $(this).data("appid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onAppDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 


function onAppDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 

			$("#divAppsGrid").html(resultSet.data);   
			} else if (resultSet.status.trim() == "error")   
			{    
				$("#alertError").text(resultSet.data);    
				$("#alertError").show();   
			} 

			} else if (status == "error")  
			{   
				$("#alertError").text("Error while deleting.");   
				$("#alertError").show();  
			} else  
			{   
				$("#alertError").text("Unknown error while deleting..");   
				$("#alertError").show();  
			} 
	} 

$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidAppIDSave").val($(this).closest("tr").find('#hidAppIDUpdate').val());     
	$("#appNo").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#appType").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#appDate").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#appDocID").val($(this).closest("tr").find('td:eq(3)').text());
	$("#appDesc").val($(this).closest("tr").find('td:eq(4)').text()); 
}); 


function validateAppForm() 
{  
	// No  
	if ($("#appNo").val().trim() == "")  
	{   
		return "Insert Appointment Number.";   
	}

	 
	 // Type  
	if ($("#appType").val().trim() == "")  
	{   
		return "Insert Appointment Type.";  
	}
	//Date
	if ($("#appDate").val().trim() == "")  
	{   
		return "Insert Appointment Date.";  
	} 
	 
	//DocID
	if ($("#appDocID").val().trim() == "")  
	{   
		return "Insert Appointment DoctorID.";  
	} 
	
	 // is numerical value  
	//var tmpPrice = $("#itemPrice").val().trim();  
	//if (!$.isNumeric(tmpPrice))  
	//{   
		//return "Insert a numerical value for Item Price.";  
	//} 
	 

	 // convert to decimal price  
	//$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2)); 
	 
	 // DESCRIPTION------------------------  
	if ($("#appDesc").val().trim() == "")  
	{   
		return "Insert Appointment Description.";  
		
	} 
	 
	 return true;
	
}



