<%@ page language="java" contentType="text/plain; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
response.setHeader("Access-Control-Allow-Origin","*");
response.setHeader("Access-Control-Allow-Credentials","true");

String preferencia=request.getParameter("preferencia");
if(preferencia!=null){
	Cookie cookie=new Cookie("cookiePublicidad",preferencia);
	cookie.setMaxAge(3600*24*10);
	response.addCookie(cookie);
}
%>