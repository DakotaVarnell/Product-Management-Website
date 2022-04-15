package servletPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductCollection myData;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		myData = new ProductCollection("C:\\Users\\varne\\git\\project3\\project3\\src\\servletPackage\\inventoryTest.txt");
		myData.toRead();
		
		
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("userName");
		request.setAttribute("userName",user); 
		String password = request.getParameter("InputtedPassword");
		request.setAttribute("InputtedPassword", password); 
		String addInst = request.getParameter("add");
		request.setAttribute("add", addInst); 
			
		//if(request.getParameter("indexButton")!=null) {
		if(user.equals("md") && password.equals("pw"))
			{
	
		
				if(request.getParameter("indexButton")!=null) {
					String color = request.getParameter("backgroundColor");
					response.getWriter().append("<!DOCTYPE html>\r\n" + 
							"<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>6th Street Music Co.</title>\r\n" + 
							"</head>\r\n"); 
					if (color != null) {
						response.getWriter().append("<body style=\"background-color:"+color+";\">\r\n"); 
					}
					else {
						response.getWriter().append("<body>\r\n"); 
					}

					response.getWriter().append(user+" Please Select One:<br>	<form action=http://localhost:8080/project3/Servlet\r\n" + 
							"		method=\"get\">\r\n" + 
							"		<input type=\"hidden\" value=\""+user+"\" name=\"userName\">\r\n" + 
							"		<input type=\"hidden\" value=\""+password+"\" name=\"InputtedPassword\">\r\n" + 
							"		<input type=\"submit\" value=\"Employee\" name=\"employeeButton\">\r\n" + 
							"		<input type=\"submit\" value=\"Customer\" name=\"customerButton\">\r\n" + 
							"	</form>\r\n" + 
							"</body>\r\n" + 
							"</html>");
				}
				if(request.getParameter("customerButton")!=null) {
					String value = "<select name=\"cars\">";
					Iterator<Product> iter = myData.getIterator();
					while (iter.hasNext()) {
						Product p = iter.next();
						value += "<option value=\""+p.getInstrType()+"\">"+p.getInstrType()+"</option>";
					}
					value += "</select>\r\n";
					request.setAttribute("dropDownOptions",value); 	
					RequestDispatcher rd=request.getRequestDispatcher("/products.jsp");
					rd.forward(request,response);
				}		
				if(request.getParameter("getCarInfo")!=null) {
					String carChoice = request.getParameter("cars");
					request.setAttribute("carChoice",carChoice);

					RequestDispatcher rd=request.getRequestDispatcher("/products2.jsp");
					rd.forward(request,response);
				}		
				if(request.getParameter("employeeButton")!=null) {
					response.getWriter().append("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>6th Street Music Co. </title>\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							user+"	\r\n" + 
							"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
							"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
							"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
							"		\r\n" + 
							"		Please select a Function:<br>\r\n" + 
							"		<select name=\"optionChoice\">\r\n" + 
							"			<option value=\"add\">Add Instrument</option>\r\n" + 
							"			<option value=\"remove\">Remove Instrument</option>\r\n" + 
							"			<option value=\"find\">Find Instrument</option>\r\n" + 
							"			<option value=\"buy\">Buy Inventory</option>\r\n" + 
							"			<option value=\"sell\">Sell Inventory</option>\r\n" + 
							"			<option value=\"size\">Inventory Size</option>\r\n" + 
							"		</select>\r\n" + 
							"		<br> \r\n" + 
							"		<input type=\"submit\" value=\"Submit\" name=\"submitButton\">\r\n" + 
							"	</form>\r\n" + 
							"</body>\r\n" + 
							"</html>");
				}	
				if(request.getParameter("submitButton")!=null)
				{
					
					String info = "";
					String value = "<select name=\"ids\">";
					Iterator<Product> iter = myData.getIterator();
					while (iter.hasNext()) {
						Product p = iter.next();
						info +=  p.toString()+"<br>";
						value += "<option value=\""+p.getId()+"\">"+p.getId()+"</option>";
						
					}
					value += "</select>\r\n";
					request.setAttribute("ids", value);
					
					if(request.getParameter("optionChoice").equals("add"))
					{
						System.out.println("add");
						response.getWriter().append("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>6th Street Music Co. </title>\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"	\r\n" + 
							"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
							value+
							"	<input type=\"range\" min=\"1\" max=\"100\" value=\"50\" class=\"slider\" name=\"myRange\"><br>"+
							"	<input type=\"reset\" value =\"Reset\" name=\"reset\" class = \"resetButton\">"+
							"	<input type=\"submit\" value =\"Submit\" name=\"submit\" class = \"submitButton\"><br>"+
							"	<h5>Choose the ID and then the quantity to add</h5>"+
							
							info+
							"	</form>\r\n" + 
							"</body>\r\n" + 
							"</html>");

						

					}
					if(request.getParameter("optionChoice").equals("remove"))
					{
						System.out.println("remove");
						
						response.getWriter().append("<html>\r\n" + 
								"<head>\r\n" + 
								"<meta charset=\"ISO-8859-1\">\r\n" + 
								"<title>6th Street Music Co. </title>\r\n" + 
								"</head>\r\n" + 
								"<body>\r\n" + 
								"	\r\n" + 
								"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
								"	<input type=\"text\" name=\"addBox\" class = \"addTextBox\">"+
								"	<input type=\"submit\" value =\"Finish\" name=\"finish\" class = \"finishButton\"><br>"+
								"	<h5>Enter the ID and then the quantity to delete seperated by a comma</h5>"+
								
								info+
								"	</form>\r\n" + 
								"</body>\r\n" + 
								"</html>");
							
							if(request.getParameter("finish") != null)
							{
								
							}
						
					}
					if(request.getParameter("optionChoice").equals("find"))
					{
						System.out.println("find");
						
						response.getWriter().append("<html>\r\n" + 
								"<head>\r\n" + 
								"<meta charset=\"ISO-8859-1\">\r\n" + 
								"<title>6th Street Music Co. </title>\r\n" + 
								"</head>\r\n" + 
								"<body>\r\n" + 
								"	\r\n" + 
								"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
								"	<input type=\"text\" name=\"addBox\" class = \"addTextBox\">"+
								"	<input type=\"submit\" value =\"Finish\" name=\"finish\" class = \"finishButton\"><br>"+
								"	<h5>Enter ID to be Found</h5>"+
								
								info+
								"	</form>\r\n" + 
								"</body>\r\n" + 
								"</html>");
							
							if(request.getParameter("finish") != null)
							{
								
							}
					}
					if(request.getParameter("optionChoice").equals("buy"))
					{
						System.out.println("buy");
						
						response.getWriter().append("<html>\r\n" + 
								"<head>\r\n" + 
								"<meta charset=\"ISO-8859-1\">\r\n" + 
								"<title>6th Street Music Co. </title>\r\n" + 
								"</head>\r\n" + 
								"<body>\r\n" + 
								"	\r\n" + 
								"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
								"	<input type=\"text\" name=\"addBox\" class = \"addTextBox\">"+
								"	<input type=\"submit\" value =\"Finish\" name=\"finish\" class = \"finishButton\"><br>"+
								"	<h5>Enter ID of Bought Item</h5>"+
								
								info+
								"	</form>\r\n" + 
								"</body>\r\n" + 
								"</html>");
							
							if(request.getParameter("finish") != null)
							{
								
							}
					}
					if(request.getParameter("optionChoice").equals("sell"))
					{
						System.out.println("sell");
						
						response.getWriter().append("<html>\r\n" + 
								"<head>\r\n" + 
								"<meta charset=\"ISO-8859-1\">\r\n" + 
								"<title>6th Street Music Co. </title>\r\n" + 
								"</head>\r\n" + 
								"<body>\r\n" + 
								"	\r\n" + 
								"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
								"	<input type=\"text\" name=\"addBox\" class = \"addTextBox\">"+
								"	<input type=\"submit\" value =\"Finish\" name=\"finish\" class = \"finishButton\"><br>"+
								"	<h5>Enter ID of Sold Item</h5>"+
								
								info+
								"	</form>\r\n" + 
								"</body>\r\n" + 
								"</html>");
							
							if(request.getParameter("finish") != null)
							{
								
							}
					}
					if(request.getParameter("optionChoice").equals("size"))
					{
						System.out.println("size");
						
						response.getWriter().append("<html>\r\n" + 
								"<head>\r\n" + 
								"<meta charset=\"ISO-8859-1\">\r\n" + 
								"<title>6th Street Music Co. </title>\r\n" + 
								"</head>\r\n" + 
								"<body>\r\n" + 
								"	\r\n" + 
								"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
								"	<h5>The Size of the Inventory is "+
								myData.getInventorySize()+"</h5>"+
								"	</form>\r\n" + 
								"</body>\r\n" + 
								"</html>");
							
							if(request.getParameter("finish") != null)
							{
								
							}
					}
				
				}				
			}
				
	else
	{
		RequestDispatcher rd=request.getRequestDispatcher("/wrongInput.jsp");
		rd.forward(request,response);
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
