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
	%>
	<%=name%> 
	<form action="/project3/Servlet" method="get">
	    <input type="hidden" value=<%=name%> name="userName">
		<%
			String selectionText = (String) request.getAttribute("dropDownOptions");
		%>
		Please select a Category<br><%=selectionText%><br> 
		<input type="submit" value="Show Items" name="showItemsButton">
	</form>
</body>
</html>