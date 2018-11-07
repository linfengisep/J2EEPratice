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
		/*
		ConnectionForm form = new ConnectionForm();
		form.authentification(request);
		request.setAttribute("form", form);
		 */
		
		//treat the uploading file;
		
		String desc = request.getParameter("desc");
		request.setAttribute("desc", desc);
		Part part = request.getPart("myfile");
		//get the name of the file;
		String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		request.setAttribute("fileName", fileName);
		
		//save the file into the destination repo;
		saveFileLocal(request,part,fileName,FILE_PATH);
		
		doGet(request, response);
	}
	
	private static String getUploadingFileName (Part part) {
		for(String contentDisposition:part.getHeader("content-disposition").split(";")) {
			if(contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf("=")+1);
			}
		}
		return null;
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
