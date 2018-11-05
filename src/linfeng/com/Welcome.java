package linfeng.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Welcome() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String messages [] = {"Paris","Rome","Londre","Berlin","Madrid"};
		request.setAttribute("messages", messages);
		request.setAttribute("time", "night");
		
		String paramName = request.getParameter("name");
		request.setAttribute("urlname", paramName);
		this.getServletContext().getRequestDispatcher("/WEB-INF/welcome.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
