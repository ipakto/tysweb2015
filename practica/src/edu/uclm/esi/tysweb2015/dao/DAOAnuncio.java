package edu.uclm.esi.tysweb2015.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import edu.uclm.esi.tysweb2015.dao.Broker;
import edu.uclm.esi.tysweb2015.dao.Conexion;
import edu.uclm.esi.tysweb2015.dominio.Anuncio;
import edu.uclm.esi.tysweb2015.dominio.Archivo;

public class DAOAnuncio {

	public static void insert(Anuncio anuncio) throws ClassNotFoundException, SQLException {
		String sql="{call insertarAnuncio(?,?,?,?,?,?)}";
		Conexion bd = Broker.get().getConnectionInsercion();
		try {	
			CallableStatement cs=bd.prepareCall(sql);
			cs.setInt(1, anuncio.getIdAnunciante());
			cs.setString(2, anuncio.getDescripcion());
			cs.setInt(3, anuncio.getIdCategoria());
			cs.setString(4,anuncio.getTitulo());
			cs.setString(5, anuncio.getPrecio());
			cs.registerOutParameter(6, java.sql.Types.INTEGER);
			cs.executeUpdate();
			anuncio.setIdAnuncio(cs.getInt(6));
		} catch (Exception e) {
			throw e;
		} finally {
			bd.close();
		}
	}

	public static void insertArchivo(Archivo archivo) throws ClassNotFoundException, SQLException {
		String sql="insert into archivos (idAnuncio,ruta,tipo) values (?,?,?);";
		Conexion bd = Broker.get().getConnectionInsercion();
		try {	
			CallableStatement cs=bd.prepareCall(sql);
			cs.setInt(1, archivo.getIdAnuncio());
			cs.setString(2, archivo.getArchivo().getAbsolutePath());
			cs.setString(3, archivo.getTipo());
			cs.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			bd.close();
		}
	}
	public static Vector<String> recuperarArchivos(Archivo foto) throws ClassNotFoundException, SQLException{
		String sql="select ruta from fotosr where idAnuncio=?;";
		Conexion bd = Broker.get().getConnectionInsercion();
		try {	
			PreparedStatement p=bd.prepareStatement(sql);
			p.setInt(1, foto.getIdAnuncio());
			ResultSet rs=p.executeQuery();
			Vector<String> fotos=new Vector<String>();
			if(rs.next()){
				fotos.add(rs.getString(1));
			}
			return fotos;
		} catch (Exception e) {
			throw e;
		} finally {
			bd.close();
		}
	}
	public static void marcarFavorito(Anuncio anuncio) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql="insert into favoritos (idAnuncio,idUsuario) values (?,?);";
		Conexion bd = Broker.get().getConnectionInsercion();
		try {	
			CallableStatement cs=bd.prepareCall(sql);
			cs.setInt(1, anuncio.getIdAnuncio());
			cs.setInt(2, anuncio.getIdAnunciante());
			cs.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			bd.close();
		}
	}

}
