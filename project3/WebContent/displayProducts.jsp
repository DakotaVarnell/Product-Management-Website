<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Inventory</title>
</head>
<body>
	<%
		String data = (String) request.getAttribute("data");

	%>
	<%=data%> 
	<form action="/project3/Servlet" method="get">
	    <input type="hidden" value=<%=data%> name="data">
		<%
			String car = (String) request.getAttribute("carChoice");
			
		%>

		<input type="submit" value="Finish" name="doneEditing">
	</form>
</body>
</html>