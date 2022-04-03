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
		myData = new ProductCollection("./project3/inventoryTest.txt");
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("userName");
		request.setAttribute("userName",user); 


		if(request.getParameter("indexButton")!=null) {
			String color = request.getParameter("backgroundColor");
			response.getWriter().append("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Insert title here</title>\r\n" + 
					"</head>\r\n"); 
					if (color != null) {
						response.getWriter().append("<body style=\"background-color:"+color+";\">\r\n"); 
					}
					else {
						response.getWriter().append("<body>\r\n"); 
					}

		response.getWriter().append(user+" here are your choices:<br>	<form action=http://localhost:8080/project3/Servlet\r\n" + 
					"		method=\"get\">\r\n" + 
					"		<input type=\"hidden\" value=\""+user+"\" name=\"userName\">\r\n" + 
					"		<input type=\"submit\" value=\"Pick Car\" name=\"pickCarButton\">\r\n" + 
					"		<input type=\"submit\" value=\"Pick Color\" name=\"pickColorButton\">\r\n" + 
					"	</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");
		}
		if(request.getParameter("pickCarButton")!=null) {
			String value = "<select name=\"cars\">";
			ArrayList <Product> temp = myData.toArrayList();
			//Iterator<Product> iter = temp.iterator().;
			while (temp.iterator().hasNext()) {
				Product p = temp.iterator().next();
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
			//String desc = myData.getDesc(carChoice);
			//request.setAttribute("carDesc",desc);
	
			RequestDispatcher rd=request.getRequestDispatcher("/products2.jsp");
			rd.forward(request,response);
		}		
		if(request.getParameter("pickColorButton")!=null) {
			response.getWriter().append("<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Insert title here</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					user+"	\r\n" + 
					"	<form action=\"/project3/Servlet\" method=\"get\">\r\n" + 
					"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
					"		\r\n" + 
					"		Please select a color:<br>\r\n" + 
					"		<select name=\"backgroundColor\">\r\n" + 
					"			<option value=\"powderblue\">powderblue</option>\r\n" + 
					"			<option value=\"Tomato\">Tomato</option>\r\n" + 
					"			<option value=\"Orange\">Orange</option>\r\n" + 
					"			<option value=\"LightGray\">LightGray</option>\r\n" + 
					"			<option value=\"SlateBlue\">SlateBlue</option>\r\n" + 
					"			<option value=\"MediumSeaGreen\">MediumSeaGreen</option>\r\n" + 
					"		</select>\r\n" + 
					"		<br> \r\n" + 
					"		<input type=\"submit\" value=\"Go!\" name=\"indexButton\">\r\n" + 
					"	</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");
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
