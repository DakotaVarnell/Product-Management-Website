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
	<%=name%> 
	<form action="/project3/Servlet" method="get">
	    <input type="hidden" value=<%=name%> name="userName">
	    <input type="hidden" value=<%=password%> name="InputtedPassword">
		<%
			String car = (String) request.getAttribute("carChoice");
			String desc = (String) request.getAttribute("carDesc");
		%>
		Your car:<br><%=car%><br> 
		<br><%=desc%><br>
		<input type="submit" value="Start Over!" name="indexButton">
	</form>
</body>
</html>