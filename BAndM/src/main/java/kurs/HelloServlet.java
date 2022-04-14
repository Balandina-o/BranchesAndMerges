package kurs;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
          
        response.setContentType("text/html"); //let there be MERGE
        PrintWriter writer = response.getWriter();//Risotto the best
 
        String name = request.getParameter("username");
        String age = request.getParameter("userage");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String[] courses = request.getParameterValues("courses");
         
        try {
        	////
            writer.println("<p>Name: " + name + "</p>");
            writer.println("MEtalicaaAaA " + age + "</p>");
            writer.println("<p>Gender: " + gender + "</p>");
            writer.println("<p>Country: " + country + "</p>");
            writer.println("<h4>Courses</h4>");
            for(String course: courses)
                writer.println("<li>" + course + "</li>");
            

        } finally {
            writer.close();  
        }
    }
}