package linfeng.forms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConnectionForm {
	private String result;
	
	public void authentification(HttpServletRequest request) {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		if(pass.toUpperCase().equals(login.toUpperCase()+"123")) {
			setResult("Connecté,Bienvenue:"+login);
		}else {
			setResult("Vous êtes pas connecté.");
		}
	}
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
