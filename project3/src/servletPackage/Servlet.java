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
	private ProductCollection myCart;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		//myData = new ProductCollection("./servletPackage/inventoryTest.txt");
		myData = new ProductCollection("C:\\Users\\varne\\git\\project3\\project3\\src\\servletPackage\\inventoryTest.txt");
		myCart = new ProductCollection();
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
		String choice = request.getParameter("optionChoice");
		request.setAttribute("optionChoice", choice);

 	
		//System.out.println(user);
		//System.out.println(password);
		
		// Set refresh, autoload time as 1 seconds
        //response.setIntHeader("Refresh", 10);
		
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
					String value = "<select name=\"categories\">";
					Iterator<Product> iter = myData.getIterator();
					while (iter.hasNext()) {
						Product p = iter.next();
						value += "<option value=\""+p.getInstrType()+"\">"+p.getInstrType()+"</option>";
					}
					value += "</select>\r\n";
					request.setAttribute("dropDownOptions",value); 	
					RequestDispatcher rd=request.getRequestDispatcher("./products.jsp");
					rd.forward(request,response);
					
				}	
				if(request.getParameter("showItemsButton")!= null)
				{
					
					String info = "";
					String value = "<select name=\"ids\">";
					Iterator<Product> iter = myData.getIterator();
					while (iter.hasNext()) {
						Product p = iter.next();
						if(p.getInstrType().equals(request.getParameter("categories")))
						{
							info += p.toString()+"<br>";
							value += "<option name = \"idDropDown\" value=\""+p.getId()+"\">"+p.getId()+"</option>";

						}
					}
					value += "</select>\r\n";
					
					
					response.getWriter().append("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>6th Street Music Co. </title>\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"	\r\n" + 
							"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" +
							"		<input type=\"hidden\" value=\""+user+"\" name=\"userName\">\r\n" + 
							"		<input type=\"hidden\" value=\""+password+"\" name=\"InputtedPassword\">\r\n" + 
							info+
							value+
							"	<h5>Choose the ID to purchase</h5>"+
							"	<input type=\"submit\" value=\"Add to Cart\" name=\"cartButton\">\r\n" +
							"	</form>\r\n" + 
							"</body>\r\n" + 
							"</html>");
				}
				
				if(request.getParameter("cartButton") != null)
				{
					String selected_id = request.getParameter("ids");
					Iterator<Product> iter1 = myData.getIterator();
					while (iter1.hasNext()) {
						Product p = iter1.next();
						if(p.getId().equals(selected_id))
						{
							//myData.decreaseStatus(p.getId(), 1);
							//myData.toWrite();
							myCart.addInstrument(p);
						}
					}
					
					RequestDispatcher rd=request.getRequestDispatcher("index.html");
					rd.forward(request,response);
				}
				if(request.getParameter("viewCartButton") != null) 
				{
					
					String cart = "";
					Iterator<Product> iter = myCart.getIterator();
					while (iter.hasNext()) {
						Product p = iter.next();
						p.setQuantity(1);	
						cart += p.toString()+"<br>";
					}
					
					response.getWriter().append("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>6th Street Music Co. </title>\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"	\r\n" + 
							"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" +
							"		<input type=\"hidden\" value=\""+user+"\" name=\"userName\">\r\n" + 
							"		<input type=\"hidden\" value=\""+password+"\" name=\"InputtedPassword\">\r\n" + 
							"	<h5>Your Cart</h5>"+
							cart+
							"	</form>\r\n" + 
							"</body>\r\n" + 
							"</html>");
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
							"			<option value=\"add\">Add Quantity</option>\r\n" + 
							"			<option value=\"remove\">Remove Quantity</option>\r\n" + 
							"			<option value=\"find\">Find Instrument</option>\r\n" + 
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
						value += "<option name = \"idDropDown\" value=\""+p.getId()+"\">"+p.getId()+"</option>";
						
					}
					value += "</select>\r\n";
					request.setAttribute("ids", value);
				
					if(request.getParameter("optionChoice").equals("add"))
					{
						
						response.getWriter().append("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>6th Street Music Co. </title>\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"	\r\n" + 
							"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
							"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
							"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
							"	    <input type=\"hidden\" value="+choice+" name=\"choice\">\r\n"+
							value+
							"	<input type=\"range\" min=\"1\" max=\"100\" value=\"50\" class=\"slider\" name=\"myRange\"><br>"+
							"	<input type=\"reset\" value =\"Reset\" name=\"reset\" class = \"resetButton\">"+
							"	<input type=\"submit\" value =\"Submit\" name=\"submitChoice\" class = \"submitChoiceButton\"><br>"+
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
									"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
									"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
									"	    <input type=\"hidden\" value="+choice+" name=\"choice\">\r\n"+
									value+
									"	<input type=\"range\" min=\"1\" max=\"100\" value=\"50\" class=\"slider\" name=\"myRange\"><br>"+
									"	<input type=\"reset\" value =\"Reset\" name=\"reset\" class = \"resetButton\">"+
									"	<input type=\"submit\" value =\"Submit\" name=\"submitChoice\" class = \"submitChoiceButton\"><br>"+
									"	<h5>Choose the ID and then the quantity to remove</h5>"+
									
									info+
									"	</form>\r\n" + 
									"</body>\r\n" + 
									"</html>");
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
									"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
									"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
									"	    <input type=\"hidden\" value="+choice+" name=\"choice\">\r\n"+
									value+
									"	<input type=\"range\" min=\"1\" max=\"100\" value=\"50\" class=\"slider\" name=\"myRange\"><br>"+
									"	<input type=\"reset\" value =\"Reset\" name=\"reset\" class = \"resetButton\">"+
									"	<input type=\"submit\" value =\"Submit\" name=\"submitChoice\" class = \"submitChoiceButton\"><br>"+
									"	<h5>Choose the ID and then the quantity to remove</h5>"+
									
									info+
									"	</form>\r\n" + 
									"</body>\r\n" + 
									"</html>");
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
									"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
									"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
									"	    <input type=\"hidden\" value="+choice+" name=\"choice\">\r\n"+
									value+
									"	<input type=\"range\" min=\"1\" max=\"100\" value=\"50\" class=\"slider\" name=\"myRange\"><br>"+
									"	<input type=\"reset\" value =\"Reset\" name=\"reset\" class = \"resetButton\">"+
									"	<input type=\"submit\" value =\"Submit\" name=\"submitChoice\" class = \"submitChoiceButton\"><br>"+
									"	<h5>Choose the ID and then the quantity to remove</h5>"+
									
									info+
									"	</form>\r\n" + 
									"</body>\r\n" + 
									"</html>");
							
						}
				}
				if(request.getParameter("submitChoice")!=null) 
				{
				
					//System.out.println(request.getParameter("choice"));
					
					response.getWriter().append("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>6th Street Music Co. </title>\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"	\r\n" + 
							"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
							"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
							"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
							"	    <input type=\"hidden\" value="+choice+" name=\"choice\">\r\n"+
							"	</form>\r\n" + 
							"</body>\r\n" + 
							"</html>");
					
					String chosen = request.getParameter("choice");
					System.out.println(chosen);
					
					String selected_id = request.getParameter("ids");
					String increase_quantity = request.getParameter("myRange");
					int inp_quan_integer = Integer.parseInt(increase_quantity);

					Iterator<Product> iter1 = myData.getIterator();
					while (iter1.hasNext()) {
						Product p = iter1.next();
						int total_add_quantity = inp_quan_integer + p.getQuantity();
						int total_sub_quantity = p.getQuantity() - inp_quan_integer;
						
						
						if(p.getId().equals(selected_id))
						{
							System.out.println("id matches");
							if(chosen.equals("add")) {
								p.setQuantity(total_add_quantity);
								myData.toWrite();
								System.out.println("add working");
							}
							if(chosen.equals("remove")) {
								p.setQuantity(total_sub_quantity);
								myData.toWrite();
								System.out.println("sub working");
							}
							if(chosen.equals("find")) {
								System.out.println("find working");
							}
							if(chosen.equals("size")) {
								System.out.println("size working");
							}
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
