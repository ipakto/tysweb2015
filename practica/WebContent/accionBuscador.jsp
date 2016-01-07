<%@page import="edu.uclm.esi.tysweb2015.dao.Broker"%>
<%@ page language="java" contentType="application/json" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, org.json.*" %>

<%

String palabras=request.getParameter("palabras");
String [] arrayPalabras=palabras.split(" ");
Connection bd=Broker.get().getConnectionSeleccion(); //HAY QUE PONER ESTO CUAND TENGAMOS LOS DATOS EN LA BD LOCAL


String sql="SELECT id, descripcion, titulo, precio FROM Anuncios WHERE descripcion LIKE '%"+arrayPalabras[0]+"%' ";
PreparedStatement ps=bd.prepareStatement(sql);
ResultSet rs;
JSONArray jsa=new JSONArray();
if(arrayPalabras.length>1){
	for(int i=1;i< arrayPalabras.length;i++){
		sql+="OR descripcion LIKE '%"+arrayPalabras[i]+"%' ";
	}
	ps=bd.prepareStatement(sql);
}
rs=ps.executeQuery();
while (rs.next()) {
	   //String par="{\"id\": "+ rs.getInt(1)+", "+ "\"nombre\": \""+rs.getString(2)+"\"}"";
	   JSONObject jso=new JSONObject();
	   jso.put("id",rs.getInt(1));
	   jso.put("descripcion",rs.getString(2));
	   jso.put("titulo",rs.getString(3));
	   jso.put("precio",rs.getString(4));
	   
	   String sql2="select ruta FROM Archivos WHERE idAnuncio="+rs.getInt(1)+" AND tipo=\"image\"";
	   PreparedStatement ps2=bd.prepareStatement(sql2);
	   ResultSet rs2=ps2.executeQuery();
	   if(rs2.next())jso.put("ruta",rs2.getString(1));
	   else jso.put("ruta","http://localhost:8080/practica/img/NO_EXISTE.png");//IMAGEN_NO_DISPONIBLE.png");

	   jsa.put(jso);//rs.getString(1));
	}
bd.close();
%>
<%= jsa.toString() %>



