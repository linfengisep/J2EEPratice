<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><%
	if(request.getAttribute("time").toString().equals("day")){
		out.println("day");
	}else{
		out.println("night");
	}
%>
</title>

</head>
<body>
<%@ include file="menu.jsp" %>
	<p>${ empty messages[0] ? messages[0] : messages[1] }</p>
	<p><%  for(int i=0;i<10;i++){
			 out.println("Coding tous les jours,tu seras le meilleur programmeur.<br/>");}%> </p>
	<p><c:out value="test jstl" /></p>
</body>
</html>
