package linfeng.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import linfeng.forms.ConnectionForm;
import linfeng.beans.User;
import linfeng.db.UsersDB;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Welcome")
@MultipartConfig
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int SIZE = 10240;
	private static final String FILE_PATH= "/Users/linfengwang/file_upload/";
  
    public Welcome() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String messages [] = {"Paris","Rome","Londre","Berlin","Madrid"};
		request.setAttribute("messages", messages);
		request.setAttribute("time", "night");
		String paramName = request.getParameter("name");
		
		//user for testing the jstl bean properties.

		User user = new User();
		user.setFirstName("lee");
		user.setLastName("bruce");
		request.setAttribute("user", user);
	
		//using data from database(mysql)
		UsersDB userDB = new UsersDB();
		request.setAttribute("userDB", userDB.getUsers());
		
		//get the session value and delete session;
		HttpSession session = request.getSession();
		String nameFromSession = (String) session.getAttribute("login");
		//session.invalidate();
		
		Cookie[] cookies = request.getCookies();
		if(cookies !=null) {
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("userName")) {
					request.setAttribute("userName", cookie.getValue());
				}
			}
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/welcome.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//authentification and session;
		/*
		ConnectionForm form = new ConnectionForm();
		form.authentification(request);
		request.setAttribute("form", form); 
		*/
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		//set a cookie
		Cookie cookie = new Cookie("userName",login);
		cookie.setMaxAge(60*60*60);
		response.addCookie(cookie);
						
		if(pass.toUpperCase().equals(login.toUpperCase()+"123")) {
			request.setAttribute("connection", "Connecté");
			
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			session.setAttribute("pass", pass);
		}
	
		
		if(request.getPart("myfile").getSize() != 0) {
			//treat the uploading file;
			String desc = request.getParameter("desc");
			request.setAttribute("desc", desc);
			
			Part part = request.getPart("myfile");
			//get the name of the file;
			String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			request.setAttribute("fileName", fileName);
			//save the file into the destination repo;
			saveFileLocal(request,part,fileName,FILE_PATH);	
		}
		
		//save the login and pass into the database;
		User user = new User();
		user.setFirstName(login);
		user.setLastName(pass);
		UsersDB userDB = new UsersDB();
		userDB.addUser(user);
		request.setAttribute("userDB", userDB.getUsers());
		
		doGet(request, response);
	}
	
	private void saveFileLocal(HttpServletRequest request,Part part,String fileName,String path) throws IOException {
			BufferedInputStream input = null;
			BufferedOutputStream output = null;
			try {
				input = new BufferedInputStream(part.getInputStream(),SIZE);
				output = new BufferedOutputStream(new FileOutputStream(new File(path + fileName)), SIZE);
				byte[] byteBuffer = new byte[SIZE];
				int length;
				while((length = input.read(byteBuffer))>0) {
					output.write(byteBuffer,0,length);
				}
			}finally {
				try {
					if(input !=null) input.close();
				}catch(IOException ignore) {}
				try {
					if(output !=null) output.close();
				}catch(IOException ignore) {}
			}
		}
	}
