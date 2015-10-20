package edu.uclm.esi.tysweb2015.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Broker {
	private static Broker yo;
	String url="jdbc:mysql://localhost:3306/tysweb2015?noAccessToProcedureBodies=true";
	private Broker() throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
	}
	public static Broker get() throws ClassNotFoundException{
		if(yo==null)
			yo=new Broker();
		return yo;
	}
	public Connection getConnectionSeleccion() throws SQLException {
		//return DriverManager.getConnection(url,"selectorTSW2015","");
		return DriverManager.getConnection(url,"root","");
	}
	public Connection getConnectionInsercion() throws SQLException {
		//return DriverManager.getConnection(url,"inserterTyS2015","inserterTyS2015");
		return DriverManager.getConnection(url,"root","");
	}
	public Connection getConnection(String userName, String pwd) throws SQLException {
		// TODO Auto-generated method stub
		return DriverManager.getConnection(url,userName,pwd);
	}
}
