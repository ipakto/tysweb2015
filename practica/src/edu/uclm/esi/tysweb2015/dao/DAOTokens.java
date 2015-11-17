package edu.uclm.esi.tysweb2015.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class DAOTokens {
	public static void crearRecuperar(String token,String email) throws Exception{
		Conexion bd=Broker.get().getConnectionInsercion();
		try{
			String sql="INSERT INTO tokens values (?,?,?);";
			PreparedStatement p=bd.prepareStatement(sql);
			p.setString(1,token);
			p.setString(2, email);
			p.setLong(3, System.currentTimeMillis()+60000);
			p.executeUpdate();
		}catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

	public static String comprobar(String token) throws Exception{
		Conexion bd=Broker.get().getConnectionSeleccion();
		String email="";
		try{
			String sql="SELECT email, fExpira FROM tokens WHERE token=?;";
			PreparedStatement p=bd.prepareStatement(sql);
			p.setString(1,token);
			ResultSet rs=p.executeQuery();
			if(rs.next()){
				email=rs.getString(1);
				long fExpira=rs.getLong(2);
				bd.close();
				if(fExpira<System.currentTimeMillis()){
					throw new Exception ("La fecha ha expirado");
				}
			}else throw new Exception ("Token no encontrado");
		}catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
		return email;
	}

}
