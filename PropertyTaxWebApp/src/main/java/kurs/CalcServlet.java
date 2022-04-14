package kurs;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kadastr, tax, square, part, period, childrens, benefit, town, property;
		response.setContentType("text/html");

		town = request.getParameter("town");
		property = request.getParameter("property");
		kadastr = request.getParameter("kadastr");
		tax = request.getParameter("tax");
		square = request.getParameter("square");
		part = request.getParameter("part");
		period = request.getParameter("period");
		childrens = request.getParameter("childrens");
		benefit = request.getParameter("benefit");

		request.setAttribute("kadastr", kadastr);
		request.setAttribute("tax", tax);
		request.setAttribute("square", square);
		request.setAttribute("part", part);
		request.setAttribute("period", period);
		request.setAttribute("childrens", childrens);
		request.setAttribute("benefit", benefit);

		RequestGenerator reqGen = new RequestGenerator(
				kadastr,
				tax,
				square,
				part,
				period,
				childrens != null ? childrens : "0",
						benefit != null ? benefit : "0",
								Double.parseDouble(town),
								Double.parseDouble(property)
				);
		
		if(reqGen.check() != null) {
			request.setAttribute("warnings", reqGen.check());
			
		}else {
			request.setAttribute("result", reqGen.count());
			
		}
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Calculator.jsp");
		requestDispatcher.forward(request, response);

	}
	
	
	
}
