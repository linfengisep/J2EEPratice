package linfeng.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import linfeng.forms.ConnectionForm;
import linfeng.beans.User;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int SIZE = 10240;
	private static final String FILE_PATH= "Users/..";
  
    public Welcome() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String messages [] = {"Paris","Rome","Londre","Berlin","Madrid"};
		request.setAttribute("messages", messages);
		request.setAttribute("time", "night");
		String paramName = request.getParameter("name");
		
		//user
		User user = new User();
		user.setAge(12);
		user.setName("lee bruce");
		user.setDomaine("art martieux");
		request.setAttribute("user", user);
		request.setAttribute("urlname", paramName);
		this.getServletContext().getRequestDispatcher("/WEB-INF/welcome.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionForm form = new ConnectionForm();
		form.authentification(request);
		request.setAttribute("form", form);
		//treat the uploading file;
		
		String desc = request.getParameter("desc");
		request.setAttribute("desc", desc);
		
		Part part = request.getPart("_file");
		//get the name of the file;
		String fileName = getFileName(part);
		
		request.setAttribute("filenName", fileName);
		doGet(request, response);
	}
	
	private static String getFileName(Part part) {
		return part.toString();
	}
}
