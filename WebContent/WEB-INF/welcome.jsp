<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>
<c:if test="${!empty userName }">
	<c:out value="${userName}"/>
</c:if>
</title>

</head>
<body>
<%@ include file="menu.jsp" %>
	<p>${ empty messages[0] ? messages[0] : messages[1] }</p>
	<p><%  for(int i=0;i<3;i++){
			 out.println("Coding tous les jours,tu seras le meilleur programmeur.<br/>");}%> </p>
	<p><c:out value="test jstl" >default value</c:out></p>
	
	<c:set var = "pseudo" value = "météo paris" scope="page"/>
	<!-- 
	scope=page:cette variable est valide que dans la page actuelle.
	scope=request: pour tous les forwards.
	scope=session: pour les session.
	scope=application: pour touste application.
	
		//remove one variable from memory.
		<p><c:out value="${pseudo}"></c:out></p>
		<p><c:remove var="pseudo"/>
	 -->
	 

	 <!-- 
		remove one variable from memory.
		 <c:set target="${user}" property = "firstName" value="lee"/>
	 <p><c:out value="${user.firstName}"></c:out>
	 -->
	
	 <c:set target="${ user }" property="firstName" value="Mathieu" />
	 <p><c:out value="${ user.firstName }" /></p>
	 
	  <!-- 
		condition en jstl
	 -->
	 <c:if test="${1<2}" var = "big">1 is smaller than 2</br></c:if>
	  <!-- 
		condition switch
	 -->
	 <c:choose>
		 <c:when test="${big}"> big is ok in when</c:when>
		 <c:when test="${big}"> big is not ok</c:when>
		 <c:otherwise>default value</c:otherwise>
	 </c:choose>
	 
	 <!-- 
		condition boucle
	 -->
	 <c:forEach var ="i" begin="1" end = "4" step="1">
	 	<p>for each boucle N°<c:out value= "${i}"/> fois</p>
	 </c:forEach>
	 
	 <c:forEach items ="${ messages }" var ="message"  varStatus = "status">
	 	<p>N°<c:out value ="${status.count }"/> message:<c:out value= "${message}"/></p>
	 </c:forEach>
	 
	 <c:forTokens var="piece" items="yes/table/un gaçon/jolie/bien joué" delims=" ">
	 	<p>${piece}</p>
	 </c:forTokens>
	 
	 <c:forEach items="${ userDB }" var ="user" varStatus = "status">
	 	<p>
	 		N°:<c:out value="${status.count}"/> First Name:<c:out value="${user.firstName}"></c:out>
	 	</p>
	 </c:forEach>
	 
	 <c:if test="${empty userDB}">
	 	<c:out value="yes, empty"/>
	 </c:if>	 
	  <!-- 
		formulaire avec session
	 -->
	 <c:if test="${! empty sessionScope.login }">
	 	<p>Bienvenue:${sessionScope.login}</p>
	 </c:if>

	 
	 <c:if test="${! empty connection}">
	 	  <p><c:out value="${connection}"/></p>
	 </c:if>
	 
	  <!-- 
		envoyer les fichiers
	 -->
	 <c:if test="${ !empty fileName }">
	 	<c:out value="Le fichier ${fileName} a été téléchargé."/>
	 </c:if>
	 
	 <form method="POST" action="welcome" enctype="multipart/form-data">
	  	 <p>
			<label for="login">Compte:</label>
		 	<input type="text" id="login" name ="login"/>
		 </p>
		 
		 <p>
			<label for="pass">Mot de passe:</label>
		 	<input type="password" id="pass" name ="pass"/>
		 </p>
		 
	 	<p>
			<label for="desc">Description:</label>
		 	<input type="text" id="desc" name ="desc"/>
		 </p>
		 
		 <p>
			<label for=myfile>File:</label>
		 	<input type="file" id="myfile" name ="myfile"/>
		 </p>
		 
		 <input type="submit" value="envoyer"/>
	 </form>
	 
</body>
</html>
