<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Access-Control-Allow-Origin","*");
response.setHeader("Access-Control-Allow-Credentials","true");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body style="background:#80BFFF">
<%
boolean encontrado=false;
Cookie[] cookies=request.getCookies();
if(cookies!=null){
	for(Cookie cookie: cookies){
		if(cookie.getName().equals("cookiePublicidad")){
			encontrado=true;
			%>
			Anuncio personalizado sobre <%= cookie.getValue() %>.
			<%
		}
	}
}
if(!encontrado){
	%>
		No te conocemos asique esto es un anuncio random
		<%	
		}
%>



</body>
</html>