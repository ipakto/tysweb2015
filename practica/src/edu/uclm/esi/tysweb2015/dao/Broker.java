package edu.uclm.esi.tysweb2015.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Broker {
	private static Broker yo;
	static String url="jdbc:mysql://localhost:3306/tysweb2015?noAccessToProcedureBodies=true";
	private Broker() throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
	}
	public static Broker get() throws ClassNotFoundException{
		if(yo==null)
			yo=new Broker();
		return yo;
	}
	public static Connection getConnectionSeleccion() throws SQLException {
		//return DriverManager.getConnection(url,"selectorTSW2015","");
		return DriverManager.getConnection(url,"root","");
	}
	public static Connection getConnectionInsercion() throws SQLException {
		//return DriverManager.getConnection(url,"inserterTyS2015","inserterTyS2015");
		return DriverManager.getConnection(url,"root","");
	}
	public Connection getConnection(String userName, String pwd) throws SQLException {
		// TODO Auto-generated method stub
		return DriverManager.getConnection(url,userName,pwd);
	}
	
	public static Connection getDB(String email, String pwd) throws Exception {
		Connection bd=getConnectionSeleccion();
		try{
			String sql="SELECT id FROM Usuarios WHERE email=?";
			PreparedStatement p=bd.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs=p.executeQuery();
			Connection result=null;
			if(rs.next()){
				int id=rs.getInt(1);
				String userName="tysweb2015"+id;
				result=DriverManager.getConnection(url,userName,pwd);
				rs.close();
			}else throw new Exception ("Login o password inválidos");
			return result;
		}catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}
}
