<%@page import="edu.uclm.esi.tysweb2015.dao.Broker"%>
<%@ page language="java" contentType="application/json" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, org.json.*" %>

<%

String emailUsuario=request.getParameter("emailUsuario");
/*Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://alarcosj.esi.uclm.es:3306/tysweb2015";

Connection bd=DriverManager.getConnection(url, "selectorTSW2015", "");*/
Connection bd=Broker.get().getConnectionSeleccion(); //HAY QUE PONER ESTO CUAND TENGAMOS LOS DATOS EN LA BD LOCAL


String sql="select id FROM Usuarios WHERE email=? ";
PreparedStatement ps=bd.prepareStatement(sql);
JSONArray jsa=new JSONArray();
ps.setString(1, emailUsuario);
ResultSet rs=ps.executeQuery();
if(rs.next()){
	int idUsuario=rs.getInt(1);
	String sql2="select idAnuncio FROM Favoritos WHERE idUsuario=?;";
	PreparedStatement ps2=bd.prepareStatement(sql2);
	ps2.setInt(1, idUsuario);
	ResultSet rs2=ps2.executeQuery();

	String sql3="select id,descripcion,titulo, precio FROM Anuncios WHERE id=?";
	while (rs2.next()) {
		JSONObject jso=new JSONObject();
		int idAnuncio=rs2.getInt(1);
		PreparedStatement ps3=bd.prepareStatement(sql3);
		ps3.setInt(1, idAnuncio);
		ResultSet rs3=ps3.executeQuery();
		if(rs3.next()){
			jso.put("id",rs3.getInt(1));
			jso.put("descripcion",rs3.getString(2));
			jso.put("titulo",rs3.getString(3));
			jso.put("precio",rs3.getString(4));
		}	   
	   String sql4="select ruta FROM Archivos WHERE idAnuncio=? AND tipo=\"image\"";
	   PreparedStatement ps4=bd.prepareStatement(sql4);
	   ps4.setInt(1, idAnuncio);
	   ResultSet rs4=ps4.executeQuery();
	   if(rs4.next()) jso.put("ruta",rs4.getString(1));
	   else jso.put("ruta","http://localhost:8080/practica/img/NO_EXISTE.png");//IMAGEN_NO_DISPONIBLE.png");
	   jsa.put(jso);//rs.getString(1));
	}
}
bd.close();
%>
<%= jsa.toString() %>



