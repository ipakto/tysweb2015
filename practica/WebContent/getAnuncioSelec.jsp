<%@page import="edu.uclm.esi.tysweb2015.dao.Broker"%>
<%@ page language="java" contentType="application/json" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, org.json.*" %>

<%
String sAnuncio=request.getParameter("idAnuncio");
int idAnuncio=Integer.parseInt(sAnuncio);
/*Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://alarcosj.esi.uclm.es:3306/tysweb2015";

Connection bd=DriverManager.getConnection(url, "selectorTSW2015", "");*/
Connection bd=Broker.get().getConnectionSeleccion(); //HAY QUE PONER ESTO CUAND TENGAMOS LOS DATOS EN LA BD LOCAL


String sql="select descripcion, titulo, precio FROM Anuncios WHERE id=? ";
PreparedStatement ps=bd.prepareStatement(sql);
JSONArray jsa=new JSONArray();
ps.setInt(1, idAnuncio);
ResultSet rs=ps.executeQuery();
if (rs.next()) {
	   //String par="{\"id\": "+ rs.getInt(1)+", "+ "\"nombre\": \""+rs.getString(2)+"\"}"";
	   JSONObject jso=new JSONObject();
	   JSONArray jsaImagen=new JSONArray();
	   JSONObject jso1=new JSONObject ();
	   JSONArray jsaVideo=new JSONArray();
	   JSONObject jso2=new JSONObject ();
	   jso.put("descripcion",rs.getString(1));
	   jso.put("titulo",rs.getString(2));
	   jso.put("precio",rs.getString(3));
	   
	   String sql2="select ruta FROM Archivos WHERE idAnuncio="+idAnuncio+" AND tipo=\"image\"";
	   PreparedStatement ps2=bd.prepareStatement(sql2);
	   ResultSet rs2=ps2.executeQuery();
	   if(rs2.next()){
		   do{
			   jso1=new JSONObject();
			   jso1.put("ruta",rs2.getString(1));
			   jsaImagen.put(jso1);
		   }while(rs2.next());
	   }
	   else{ 
		   jso1=new JSONObject();
		   jso1.put("ruta","http://localhost:8080/practica/img/NO_EXISTE.png");//IMAGEN_NO_DISPONIBLE.png");
		   jsaImagen.put(jso1);
	   }
  	   
  	   String sql3="select ruta FROM Archivos WHERE idAnuncio="+idAnuncio+" AND tipo=\"video\"";
	   PreparedStatement ps3=bd.prepareStatement(sql3);
	   ResultSet rs3=ps3.executeQuery();
	   
	   if(rs3.next()){
		   do{
			   jso2=new JSONObject();
			   jso2.put("ruta",rs3.getString(1));
			   jsaVideo.put(jso2);
		   }while(rs3.next());
	   }
	   else{ 
		   jso2=new JSONObject();
		   jso2.put("ruta","NO EXISTE");//VIDEO_NO_DISPONIBLE
		   jsaVideo.put(jso2);
	   }
	   jso.put("conjuntoImagenes",jsaImagen);
	   jso.put("conjuntoVideos",jsaVideo);
	   
  	   
	   jsa.put(jso);//rs.getString(1));
	   
	   
	}
bd.close();
%>
<%= jsa.toString() %>



