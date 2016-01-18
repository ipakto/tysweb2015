<%@page import="edu.uclm.esi.tysweb2015.dao.Broker"%>
<%@ page language="java" contentType="application/json" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, org.json.*" %>

<%

String email=request.getParameter("emailAnunciante");
Connection bd=Broker.get().getConnectionSeleccion(); //HAY QUE PONER ESTO CUAND TENGAMOS LOS DATOS EN LA BD LOCAL


String sql0="select id FROM usuarios WHERE email=?";
PreparedStatement ps0=bd.prepareStatement(sql0);
ps0.setString(1,email);
ResultSet rs0=ps0.executeQuery();
JSONObject jso=new JSONObject();
int idUsuario;
if(rs0.next()){
   idUsuario=rs0.getInt(1);
   String sql="UPDATE usuarios SET estado=1 WHERE id=?";
   CallableStatement cs=bd.prepareCall(sql);
	cs.setInt(1, idUsuario);
	cs.executeUpdate();
	jso.put("resultado","OK");
}
%>
<%= jso.toString() %>
