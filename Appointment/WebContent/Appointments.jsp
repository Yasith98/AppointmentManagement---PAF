<%@page import="model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointments Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/appointments.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Appointment Management</h1>

				<form id="formApp" name="formApp" method="post"
					action="appointments.jsp">

					App no: <input id="appNo" name="appNo" type="text"
						class="form-control form-control-sm"> <br> App type:
					<input id="appType" name="appType" type="text"
						class="form-control form-control-sm"> <br> App date:
					<input id="appDate" name="appDate" type="date"
						class="form-control form-control-sm"> <br> App docID:
					<input id="appDocID" name="appDocID" type="text"
						class="form-control form-control-sm"> <br> App desc:
					<input id="appDesc" name="appDesc" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidAppIDSave" name="hidAppIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divAppsGrid">
					<%
						Appointment AppointmentObj = new Appointment();
					out.print(AppointmentObj.readAppointments());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
