package edu.uclm.esi.tysweb2015.dao;

/******************************************************************************************
 * *****************TECNOLOGÍAS Y SISTEMAS DE LA INFORMACIÓN*******************************
 * ******************ESCUELA SUPERIOR DE INFORMÁTICA(UCLM)*********************************
 * ************************PRÁCTICA REALIZADA POR:*****************************************
 *		 * 				- Jorge Vela Plaza											      *
 *		 * 				- Francisco Ruiz Romero											  *
 *		 * 				- Rosana Rodríguez-Bobada Aranda								  *
 * 																						  *
 ******************************************************************************************/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOTokens {
	public static void crearRecuperar(String token,int idUsuario) throws Exception{
		Connection bd=Broker.get().getConnectionInsercion();
		try{
			String sql="INSERT INTO tokens values (?,?,?);";
			PreparedStatement p=bd.prepareStatement(sql);
			p.setString(1,token);
			p.setInt(2, idUsuario);
			p.setLong(3, System.currentTimeMillis()+86400000);
			p.executeUpdate();
		}catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

	public static int comprobar(String token) throws Exception{
		Connection bd=Broker.get().getConnectionSeleccion();
		int idUsuario;
		try{
			String sql="SELECT fExpira,idUsuario FROM tokens WHERE token=?;";
			PreparedStatement p=bd.prepareStatement(sql);
			p.setString(1,token);
			ResultSet rs=p.executeQuery();
			if(rs.next()){
				long fExpira=rs.getLong(1);
				idUsuario=rs.getInt(2);
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
		return idUsuario;
	}

}
