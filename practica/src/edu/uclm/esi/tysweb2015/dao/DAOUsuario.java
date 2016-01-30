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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class DAOUsuario {

	public static void insert(Usuario usuario, int... tipoDeOAuth) throws Exception {
		Connection bd=Broker.get().getConnectionInsercion();
		try{
			if(tipoDeOAuth.length==0){
				String sql="{call insertarUsuario(?,?,?,?,?,?,?,?)}";
				CallableStatement cs=bd.prepareCall(sql);
				cs.setString(1, usuario.getEmail());
				cs.setString(2, usuario.getPwd1());
				cs.setString(3, usuario.getNombre());
				cs.setString(4, usuario.getApellido1());
				cs.setString(5, usuario.getApellido2());
				cs.setString(6, usuario.getTelefono());
				cs.setInt(7, usuario.getIdUbicacion());
				cs.registerOutParameter(8, java.sql.Types.VARCHAR);
				cs.executeUpdate();
				String exito=cs.getString(8);
				if(exito!=null && !(exito.equals("OK"))){
					throw new SQLException(exito);
				}
			}else{
				String sql="{call insertarUsuarioOAuth(?,?,?,?,?,?)}";
				CallableStatement cs=bd.prepareCall(sql);
				cs.setString(1, usuario.getEmail());
				cs.setString(2, usuario.getNombre());
				cs.setString(3, usuario.getApellido1());
				cs.setString(4, usuario.getApellido2());
				cs.setInt(5, tipoDeOAuth[0]);
				cs.registerOutParameter(6, java.sql.Types.VARCHAR);
				cs.executeUpdate();
				String exito=cs.getString(6);
				if(exito!=null && !(exito.equals("OK"))){
					throw new SQLException(exito);
				}
			}
		}catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}
	
	public static void identificar(Usuario usuario,String email, String pwd) throws Exception {
		Connection bd=Broker.get().getConnectionSeleccion();
		try{
			String sql="SELECT id, nombre, apellido1, apellido2, telefono, idUbicacion, estado FROM Usuarios WHERE email=?";
			PreparedStatement p=bd.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs=p.executeQuery();
			if(rs.next()){
				int id=rs.getInt(1);
				String nombre=rs.getString(2);
				String apellido1=rs.getString(3);
				String apellido2=rs.getString(4);
				String telefono=rs.getString(5);
				int idUbicacion=rs.getInt(6);
				int estado=rs.getInt(7);
				bd.close();
				String userName="tysweb2015"+id;
				if(Broker.get().existe(userName, pwd)){
					//usuario=new Usuario(email, nombre, apellido1, apellido2, telefono, pwd, idUbicacion);
					usuario.setId(id);
					usuario.setEstado(estado);
				}
			}else throw new Exception ("Login o contraseña incorrectos");
			
		}catch(Exception e){
			throw e;
			
		}
		finally{
			bd.close();
		}
		
	}
	public static void update(Usuario usuario) throws Exception {
		Connection bd=Broker.get().getConnectionInsercion();
		try{
			String sql="{call cambiarPassword(?,?)}";
			String userName="tysweb2015"+usuario.getId();
			CallableStatement cs=bd.prepareCall(sql);
			cs.setString(1, userName);
			cs.setString(2, usuario.getPwd1());
			cs.executeUpdate();
		}catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
		
	}
	public static void existe(Usuario user) throws Exception{
		Connection bd=Broker.get().getConnectionSeleccion();
		try{
			String sql="SELECT id, estado FROM Usuarios WHERE email=?";
			PreparedStatement p=bd.prepareStatement(sql);
			p.setString(1, user.getEmail());
			ResultSet rs=p.executeQuery();
			if(rs.next()){
				int id=rs.getInt(1);
				int estado=rs.getInt(2);
				user.setId(id);
				user.setEstado(estado);
			}else throw new Exception ("Usuario no encontrado");
			
		}catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

	public static void modify(Usuario u, String emailSesion) throws Exception {
		Connection bd=Broker.get().getConnectionInsercion();
		try{
			String sql="SELECT id FROM Usuarios WHERE email=?";
			PreparedStatement p=bd.prepareStatement(sql);
			p.setString(1,emailSesion);
			ResultSet rs=p.executeQuery();
			int id;
			if(rs.next()){
				id=rs.getInt(1);
			}else throw new Exception("Usuario no encontrado");
			sql="UPDATE Usuarios SET email=?, nombre=?, apellido1=?, apellido2=?, telefono=? WHERE id=?;";
			PreparedStatement p2=bd.prepareStatement(sql);
			p2.setString(1, u.getEmail());
			p2.setString(2, u.getNombre());
			p2.setString(3, u.getApellido1());
			p2.setString(4, u.getApellido2());
			p2.setString(5, u.getTelefono());
			p2.setInt(6, id);
			p2.executeUpdate();
		}catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

}
