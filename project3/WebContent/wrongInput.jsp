<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css" type = "text/css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String name = (String) request.getAttribute("userName");
		String password = (String) request.getAttribute("InputtedPassword");
	%>
	<h3 id = "wrongCredentials">Incorrect Input</h3>
	<form action="/project3/Servlet" method="get">
	    <input type="hidden" value=<%=name%> name="userName">
	    <input type="hidden" value=<%=password%> name="InputtedPassword">
		<%
			String selectionText = (String) request.getAttribute("dropDownOptions");
		%>

	</form>

	<form action=http://localhost:8080/project3/Servlet	method="get">
		Username: <input type="text" name="userName" class = "userName"> <br>
		Password: <input type="password" name="InputtedPassword" class = "InputtedPassword"> <br>
		<input type="submit" value="Go!" name="indexButton" class = "indexButton">
	</form>
</body>
</html>

