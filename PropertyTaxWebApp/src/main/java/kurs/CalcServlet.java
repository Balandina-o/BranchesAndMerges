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
	
	private String kadastr, tax, square, part, period, childrens, benefit, town, property, textField;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		this.town = request.getParameter("town");
		this.property = request.getParameter("property");
		this.kadastr = request.getParameter("kadastr");
		this.tax = request.getParameter("tax");
		this.square = request.getParameter("square");
		this.part = request.getParameter("part");
		this.period = request.getParameter("period");
		this.childrens = request.getParameter("childrens");
		this.benefit = request.getParameter("benefit");


		request.setAttribute("kadastr", kadastr);
		request.setAttribute("tax", tax);
		request.setAttribute("square", square);
		request.setAttribute("part", part);
		request.setAttribute("period", period);
		request.setAttribute("childrens", childrens);
		request.setAttribute("benefit", benefit);

		
		request.setAttribute("result", calculate());
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Calculator.jsp");
		requestDispatcher.forward(request, response);

	}
	
	

	public String calculate() {
		//double reductionFactor = Double.valueOf(this.getRegionConst());
		//double deduction = Double.valueOf(this.getPropertyConst2());

		TaxAmount taxamount = new TaxAmount(
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


		List<InputError> errors = taxamount.validate();
		String message = "";
		String text = "";
		
		if (errors.size() > 0) {
			for (int i = 0; i < errors.size(); i++) {
				message += errors.get(i).getName() + ": " + errors.get(i).getMessage() + "\n";
				
				
			}
			
			return message;

		} else {
			
			BigDecimal ttt = taxamount.calculate();
			text = ttt.toString();
			
			return text;
			
		}
	}

	private String getTextFieldByName(String name) throws Exception {
		String field;

		switch (name) {
		case "Кадастровая стоимость": field = "kadastr";
		break;
		case "Инвентаризационный налог": field = "tax";
		break;
		case "Площадь": field = "square";
		break;
		case "Размер доли": field = "part";
		break;
		case "Период владения": field = "period";
		break;
		case "Количество детей": field = "childrens";
		break;
		case "Льгота": field = "benefit";
		break;
		default: throw new Exception("Недопустимое имя параметра");
		}

		return field;
	}


}
