<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>6th Street Music Co. </title>
</head>
<body>
	<%
			String name = (String) request.getAttribute("userName");
			String password = (String) request.getAttribute("InputtedPassword");
	%>
	<%=name%> 
	<form action="/project3/Servlet" method="get">
	    <input type="hidden" value=<%=name%> name="userName">
	    <input type="hidden" value=<%=password%> name="InputtedPassword">
		<%
			String selectionText = (String) request.getAttribute("dropDownOptions");
		%>
		Please select a Category<br><%=selectionText%><br> 
		<input type="submit" value="Show Items" name="showItemsButton">
		<input type="submit" value="View Cart" name="viewCartButton">
	</form>
</body>
</html>