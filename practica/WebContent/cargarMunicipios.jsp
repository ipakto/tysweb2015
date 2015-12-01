
<%@page import="edu.uclm.esi.tysweb2015.dao.Conexion"%>
<%@page import="edu.uclm.esi.tysweb2015.dao.Broker"%>
<%@ page language="java" contentType="application/json"  pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,org.json.*"%>
<% response.setHeader("Access-Control-Allow-Origin","*"); %>
<%

String sProvincia=request.getParameter("provincia");
int provincia=Integer.parseInt(sProvincia);

/*Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://alarcosj.esi.uclm.es:3306/tysweb2015";

Connection db=DriverManager.getConnection(url,"selectorTSW2015","");*/
Conexion bd=Broker.get().getConnectionSeleccion(); 
String sql="SELECT id, nombre FROM ubicaciones WHERE tipo='Municipio' and idPadre=? order by nombre";

PreparedStatement ps=bd.prepareStatement(sql);
ps.setInt(1,provincia);
JSONArray jsa=new JSONArray();
ResultSet rs=ps.executeQuery();
while (rs.next()){
	JSONObject jso=new JSONObject();
	jso.put("id",rs.getInt(1));
	jso.put("nombre",rs.getString(2));
	jsa.put(jso);
}
bd.close();

/*or.apache.http.*...
HttpCLient client=new DefaultHttpClient();
String url="http://www.uclm.es";
HttpGet get =new HttpGet(url);
HttpResponse resp=client.execute(get);
InputStream is=resp.getEntity().getContent();
BufferedReader br=new BufferedReader (new InputStreamReader(is));
String linea;
while((linea=br.readLine())!=null){
out.print(linea);
}
is.close();*/
%>
<%= jsa.toString() %> 

