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
import edu.uclm.esi.tysweb2015.dominio.Foto;

public class DAOAnuncio {

	public static void insert(Anuncio anuncio) throws ClassNotFoundException, SQLException {
		String sql="{call insertarAnuncio(?,?,?,?)}";
		Conexion bd = Broker.get().getConnectionInsercion();
		try {	
			CallableStatement cs=bd.prepareCall(sql);
			cs.setInt(1, anuncio.getIdAnunciante());
			cs.setString(2, anuncio.getDescripcion());
			cs.setInt(3, anuncio.getIdCategoria());
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.executeUpdate();
			anuncio.setIdAnuncio(cs.getInt(4));
		} catch (Exception e) {
			throw e;
		} finally {
			bd.close();
		}
	}

	public static void insertFoto(Foto foto) throws ClassNotFoundException, SQLException {
		String sql="insert into fotosr (idAnuncio,ruta) values (?,?);";
		Conexion bd = Broker.get().getConnectionInsercion();
		try {	
			CallableStatement cs=bd.prepareCall(sql);
			cs.setInt(1, foto.getIdAnuncio());
			cs.setString(2, foto.getArchivo().getAbsolutePath());
			cs.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			bd.close();
		}
	}
	public static Vector<String> recuperarFotos(Foto foto) throws ClassNotFoundException, SQLException{
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

}
