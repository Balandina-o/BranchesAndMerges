package kurs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AuthoServlet")
public class AuthoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login, password, page;
		response.setContentType("text/html");

		login = request.getParameter("login");
		password = request.getParameter("password");

		
		if(login == "") {
			request.setAttribute("error", "Неверный логин или пароль!");
			page = "/Authorisator.jsp";

		}else { 
			page = "/Calculator.jsp";

		}

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
		requestDispatcher.forward(request, response);

	}

}
