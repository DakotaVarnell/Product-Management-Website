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
		
		
		//I tried this many different ways and last minute had to throw in the absolute path because that was all that was working. I'm sorry I know it doesn't work but on your device
		//but I couldn't figure it out soon enough to submit, 3 different projects all due today so time was limited :)
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

		
		if(user.equals("md") && password.equals("pw"))//if the user signs in with the right credentials move forward
			{
			
		
				if(request.getParameter("indexButton")!=null) {//if our sign in button is pressed we will move further into the webpage
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
					response.getWriter().append("<h2 class = \"user\">" + user + "</h2>" +" <h2 class = \"selectOne\">Please Select One:</h2><br>	<form action=http://localhost:8080/project3/Servlet\r\n" +
							"		method=\"get\">\r\n" + 
							"		<link rel=\"stylesheet\" href=\"style.css\" type = \"text/css\">"+
							"		<input type=\"hidden\" value=\""+user+"\" name=\"userName\">\r\n" + 
							"		<input type=\"hidden\" value=\""+password+"\" name=\"InputtedPassword\">\r\n" + 
							"		<input type=\"submit\" value=\"Employee\" class = \"employee\" name=\"employeeButton\">\r\n" + 
							"		<input type=\"submit\" value=\"Customer\" class = \"customer\" name=\"customerButton\">\r\n" + 
							"	</form>\r\n" + 
							"</body>\r\n" + 
							"</html>");
				}
				if(request.getParameter("customerButton")!=null) {//if the customer button is pressed it will show a drop down with the instrument types and a few buttons
					String value = "<select class = \"categoriesButton\" name=\"categories\">";
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
				if(request.getParameter("showItemsButton")!= null)//if the show item buttons is pressed it wil display all of our items and an id drop down
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
							"		<link rel=\"stylesheet\" href=\"style.css\" type = \"text/css\">"+
							"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" +
							"		<input type=\"hidden\" value=\""+user+"\" name=\"userName\">\r\n" + 
							"		<input type=\"hidden\" value=\""+password+"\" name=\"InputtedPassword\">\r\n" + 
							info+
							value+
							"	<h5 class = \"displayMessage\" >Choose the ID to purchase</h5>"+
							"	<input type=\"submit\" class = \"addCart\" value=\"Add to Cart\" name=\"cartButton\">\r\n" +
							"	</form>\r\n" + 
							"</body>\r\n" + 
							"</html>");
				}
				
				if(request.getParameter("cartButton") != null)//if the cart button is pressed then we will add that value to the cart and then go back to the landing page
				{
					String selected_id = request.getParameter("ids");
					Iterator<Product> iter1 = myData.getIterator();
					while (iter1.hasNext()) {
						Product p = iter1.next();
						if(p.getId().equals(selected_id))
						{
							myData.decreaseStatus(p.getId(), 1);
							myData.toWrite();
							myCart.addInstrument(p);
						}
					}
					
					RequestDispatcher rd=request.getRequestDispatcher("index.html");
					rd.forward(request,response);
				}
				if(request.getParameter("viewCartButton") != null) //if the view cart button is pressed then we will display our cart and values in the cart
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
							"		<link rel=\"stylesheet\" href=\"style.css\" type = \"text/css\">"+
							"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" +
							"		<input type=\"hidden\" value=\""+user+"\" name=\"userName\">\r\n" + 
							"		<input type=\"hidden\" value=\""+password+"\" name=\"InputtedPassword\">\r\n" + 
							"	<h5>Your Cart</h5>"+
							cart+
							"	</form>\r\n" + 
							"</body>\r\n" + 
							"</html>");
				}
				
				if(request.getParameter("employeeButton")!=null) {//if the employee button is pressed it will allow a variet of options below
					response.getWriter().append("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>6th Street Music Co. </title>\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							user+"	\r\n" + 
							"		<link rel=\"stylesheet\" href=\"style.css\" type = \"text/css\">"+
							"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
							"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
							"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
							"		\r\n" + 
							"		Please select a Function:<br>\r\n" + 
							"		<select class = \"optionsButton\" name=\"optionChoice\">\r\n" + 
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
					//iterates through our backend and creates the drop downs for our id
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
				
					if(request.getParameter("optionChoice").equals("add"))//if our add button was chosen then we will allow the user to choose an id of the product to add and how much
					{
						
						response.getWriter().append("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>6th Street Music Co. </title>\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"	\r\n" + 
							"		<link rel=\"stylesheet\" href=\"style.css\" type = \"text/css\">"+
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
					if(request.getParameter("optionChoice").equals("remove"))//if our remove button was chosen then we will allow the user to choose an id of the product to remove and how much
					{
							
							response.getWriter().append("<html>\r\n" + 
									"<head>\r\n" + 
									"<meta charset=\"ISO-8859-1\">\r\n" + 
									"<title>6th Street Music Co. </title>\r\n" + 
									"</head>\r\n" + 
									"<body>\r\n" + 
									"	\r\n" + 
									"		<link rel=\"stylesheet\" href=\"style.css\" type = \"text/css\">"+
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
					if(request.getParameter("optionChoice").equals("find"))//if our find button was chosen then we will allow the user to choose an id of the product to disply
					{
							
							response.getWriter().append("<html>\r\n" + 
									"<head>\r\n" + 
									"<meta charset=\"ISO-8859-1\">\r\n" + 
									"<title>6th Street Music Co. </title>\r\n" + 
									"</head>\r\n" + 
									"<body>\r\n" + 
									"	\r\n" + 
									"		<link rel=\"stylesheet\" href=\"style.css\" type = \"text/css\">"+
									"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
									"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
									"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
									"	    <input type=\"hidden\" value="+choice+" name=\"choice\">\r\n"+
									value+
									"	<input type=\"submit\" value =\"Submit\" name=\"submitChoice\" class = \"submitChoiceButton\"><br>"+
									"	<h5>Choose the ID of the product you want to display</h5>"+
									
									info+
									"	</form>\r\n" + 
									"</body>\r\n" + 
									"</html>");
					}
					if(request.getParameter("optionChoice").equals("size"))//if our size button was chosen then display the size
						{
							
							response.getWriter().append("<html>\r\n" + 
									"<head>\r\n" + 
									"<meta charset=\"ISO-8859-1\">\r\n" + 
									"<title>6th Street Music Co. </title>\r\n" + 
									"</head>\r\n" + 
									"<body>\r\n" + 
									"	\r\n" + 
									"		<link rel=\"stylesheet\" href=\"style.css\" type = \"text/css\">"+
									"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
									"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
									"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
									"	    <input type=\"hidden\" value="+choice+" name=\"choice\">\r\n"+
									"	<h5>The inventory contains " + myData.getInventorySize() + " items</h5>"+
									"	</form>\r\n" + 
									"</body>\r\n" + 
									"</html>");
							
						}
				}
				if(request.getParameter("submitChoice")!=null) //if our submit is pressed then we will show a message and do some calculations depending on what was picked
				{
					
					response.getWriter().append("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>6th Street Music Co. </title>\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"	\r\n" + 
							"		<link rel=\"stylesheet\" href=\"style.css\" type = \"text/css\">"+
							"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
							"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
							"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
							"	    <input type=\"hidden\" value="+choice+" name=\"choice\">\r\n"+
							"		<h5>Thank you, return to previous page</h5>"+
							"	</form>\r\n" + 
							"</body>\r\n" + 
							"</html>");
					
					String chosen = request.getParameter("choice");//sets our chosen to the chosen value of the choice drop down
					
					
					String selected_id = request.getParameter("ids");

					Iterator<Product> iter1 = myData.getIterator();
					while (iter1.hasNext()) {
						Product p = iter1.next();

						
						if(p.getId().equals(selected_id))//if the id's match the chosen id we will stop here for a bit
						{

							if(chosen.equals("add")) //if choice chosen is add then it calculates the new value of the item added to
							{
								String increase_quantity = request.getParameter("myRange");
								int inp_quan_integer = Integer.parseInt(increase_quantity);
								int total_add_quantity = inp_quan_integer + p.getQuantity();
								
								p.setQuantity(total_add_quantity);
								myData.toWrite();
							}
							if(chosen.equals("remove")) //if choice chosen is remove then it calculates the new value of the item removed from
							{
								String increase_quantity = request.getParameter("myRange");
								int inp_quan_integer = Integer.parseInt(increase_quantity);
								int total_sub_quantity = p.getQuantity() - inp_quan_integer;
								
								p.setQuantity(total_sub_quantity);
								myData.toWrite();
							}
							if(chosen.equals("find")) //if the choice chosen was find then we will execute the code below which displays the info about the item
							{
								String show_product = p.toString();
								
								response.getWriter().append("<html>\r\n" + 
										"<head>\r\n" + 
										"<meta charset=\"ISO-8859-1\">\r\n" + 
										"<title>6th Street Music Co. </title>\r\n" + 
										"</head>\r\n" + 
										"<body>\r\n" + 
										"	\r\n" + 
										"		<link rel=\"stylesheet\" href=\"style.css\" type = \"text/css\">"+
										"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
										"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
										"	    <input type=\"hidden\" value="+password+" name=\"InputtedPassword\">\r\n" + 
										"	    <input type=\"hidden\" value="+choice+" name=\"choice\">\r\n"+
										"		<h5>The item you were looking for is displayed below</h5>"+
										show_product+
										"		<img src =\"" +p.getImage()+ "\" width = 300 height = 300"+
										"	</form>\r\n" + 
										"</body>\r\n" + 
										"</html>");
							}

						}
					}
				}

			}
		
	//Else that handles if the user isnt authenticated
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
