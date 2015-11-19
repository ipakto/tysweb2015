package edu.uclm.esi.tysweb2015.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
	private Connection connection;
	private Pool pool;
	private String user;
	
	public Conexion(String user, String pwd, Pool pool,String u) throws SQLException {
		this.user=u;
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tysweb2015?noAccessToProcedureBodies=true",user,pwd);
		this.pool=pool;
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException{
		return connection.prepareStatement(sql);
	}
	//Hay que mejorarlo por lo de que el inserter y selector tiene el mismo user y pwd.
	public void close() throws SQLException{
		if(user.equals("s"))
			pool.moverASeleccion(this);
		else pool.moverAInsercion(this);
	}

	public CallableStatement prepareCall(String sql) throws SQLException {
		return connection.prepareCall(sql);
	}
}
