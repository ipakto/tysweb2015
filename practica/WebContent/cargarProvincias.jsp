
<%@page import="edu.uclm.esi.tysweb2015.dao.Broker"%>
<%@page import="edu.uclm.esi.tysweb2015.dao.Conexion"%>
<%@ page language="java" contentType="application/json"  pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,org.json.*"%>
<%

String sComunidad=request.getParameter("comunidad");
int comunidad=Integer.parseInt(sComunidad);
/*
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://alarcosj.esi.uclm.es:3306/tysweb2015";

Connection db=DriverManager.getConnection(url,"selectorTSW2015","");*/
Conexion bd=Broker.get().getConnectionSeleccion(); 
String sql="SELECT id, nombre FROM ubicaciones WHERE tipo='Provincia' and idPadre=? order by nombre";

PreparedStatement ps=bd.prepareStatement(sql);
JSONArray jsa=new JSONArray();
ps.setInt(1,comunidad);
ResultSet rs=ps.executeQuery();
while (rs.next()){
	JSONObject jso=new JSONObject();
	jso.put("id",rs.getInt(1));
	jso.put("nombre",rs.getString(2));
	jsa.put(jso);
}
bd.close();
%>
<%= jsa.toString() %> 
