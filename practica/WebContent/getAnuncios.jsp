<%@page import="edu.uclm.esi.tysweb2015.dao.Broker"%>
<%@ page language="java" contentType="application/json" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, org.json.*" %>

<%

int idCategoria=Integer.parseInt(request.getParameter("idCategoria"));
/*Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://alarcosj.esi.uclm.es:3306/tysweb2015";

Connection bd=DriverManager.getConnection(url, "selectorTSW2015", "");*/
Connection bd=Broker.get().getConnectionSeleccion(); //HAY QUE PONER ESTO CUAND TENGAMOS LOS DATOS EN LA BD LOCAL


String sql="select id,descripcion,titulo, precio FROM Anuncios WHERE idCategoria=? order by fechaDeAlta DESC";
PreparedStatement ps=bd.prepareStatement(sql);
JSONArray jsa=new JSONArray();
ps.setInt(1, idCategoria);
ResultSet rs=ps.executeQuery();
while (rs.next()) {
	   //String par="{\"id\": "+ rs.getInt(1)+", "+ "\"nombre\": \""+rs.getString(2)+"\"}"";
	   JSONObject jso=new JSONObject();
	   jso.put("id",rs.getInt(1));
	   jso.put("descripcion",rs.getString(2));
	   jso.put("titulo",rs.getString(3));
	   jso.put("precio",rs.getString(4));
	   jsa.put(jso);//rs.getString(1));
	}
bd.close();
%>
<%= jsa.toString() %>



