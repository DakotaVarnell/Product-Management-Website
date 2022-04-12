<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String name = (String) request.getAttribute("userName");
		String password = (String) request.getAttribute("InputtedPassword");
	%>
	<form action="/project3/Servlet" method="get">
	    <input type="hidden" value=<%=name%> name="userName">
	    <input type="hidden" value=<%=password%> name="InputtedPassword">
		<%
			String selectionText = (String) request.getAttribute("dropDownOptions");
		%>

	</form>
</body>
</html>



















<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3 id = "wrongCredentials">Incorrect Input</h3>
	<form action=http://localhost:8080/project3/Servlet	method="get">
		Username: <input type="text" name="userName"> <br>
		Password: <input type="password" name="InputtedPassword"> <br>
		<input type="submit" value="Go!" name="indexButton">
	</form>
</body>
</html>
