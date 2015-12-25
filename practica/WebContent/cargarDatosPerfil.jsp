
<%@page import="edu.uclm.esi.tysweb2015.dao.Broker"%>
<%@ page language="java" contentType="application/json"  pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,org.json.*"%>
<%
Connection bd=Broker.get().getConnectionSeleccion(); 
String email=request.getParameter("email");
String sql="SELECT nombre,apellido1,apellido2,telefono FROM Usuarios WHERE email=?;";
PreparedStatement ps=bd.prepareStatement(sql);
ps.setString(1,email);
JSONObject jso=new JSONObject();
ResultSet rs=ps.executeQuery();
while (rs.next()){
	jso.put("nombre",rs.getString(1));
	jso.put("apellido1",rs.getString(2));
	jso.put("apellido2",rs.getString(3));
	jso.put("telefono",rs.getString(4));
	jso.put("email", email);
	//String par="{\"id\" : "+rs.getInt(1)+", "+ "\"nombre\" : \""+rs.getString(2)+ "\"}";
}
bd.close();
%>
<%= jso.toString() %>