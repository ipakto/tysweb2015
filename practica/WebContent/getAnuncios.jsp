<%@page import="edu.uclm.esi.tysweb2015.dao.Broker"%>
<%@ page language="java" contentType="application/json" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, org.json.*" %>

<%

int idCategoria=Integer.parseInt(request.getParameter("idCategoria"));
/*Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://alarcosj.esi.uclm.es:3306/tysweb2015";

Connection bd=DriverManager.getConnection(url, "selectorTSW2015", "");*/
Connection bd=Broker.get().getConnectionSeleccion(); //HAY QUE PONER ESTO CUAND TENGAMOS LOS DATOS EN LA BD LOCAL

String email=request.getParameter("emailAnunciante");
int idUsuario=-50;
if(!email.equals("null")){
		String sql0="select id FROM usuarios WHERE email=?";
		PreparedStatement ps0=bd.prepareStatement(sql0);
		ps0.setString(1,email);
		ResultSet rs0=ps0.executeQuery();
		if(rs0.next()){
		   idUsuario=rs0.getInt(1);
		}
}

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
	   
	   String sql2="select ruta FROM Archivos WHERE idAnuncio="+rs.getInt(1)+" AND tipo=\"image\"";
	   PreparedStatement ps2=bd.prepareStatement(sql2);
	   ResultSet rs2=ps2.executeQuery();
	   if(rs2.next())jso.put("ruta",rs2.getString(1));
	   else jso.put("ruta","http://localhost:8080/practica/img/NO_EXISTE.png");//IMAGEN_NO_DISPONIBLE.png");
	   
	   if(idUsuario!=-50){
		   String sql3="SELECT * from favoritos where idUsuario=? AND idAnuncio=? ";
		   PreparedStatement ps3=bd.prepareStatement(sql3);
		   ps3.setInt(1,idUsuario);
		   ps3.setInt(2,rs.getInt(1));
		   ResultSet rs3=ps3.executeQuery();
		   if(rs3.next()) jso.put("fav",0);
		   else jso.put("fav",2);
	   }else{
		   jso.put("fav",2);
	   }
	   
	   jsa.put(jso);//rs.getString(1));
	}
bd.close();
%>
<%= jsa.toString() %>



