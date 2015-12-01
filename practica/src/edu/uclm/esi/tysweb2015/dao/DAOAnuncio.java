package edu.uclm.esi.tysweb2015.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dao.Broker;
import edu.uclm.esi.tysweb2015.dao.Conexion;
import edu.uclm.esi.tysweb2015.dominio.Anuncio;

public class DAOAnuncio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void insert(Anuncio anuncio) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO anuncios (descripcion,idCategoria,idAnunciante) values(?,?,?);";
		Conexion bd = Broker.get().getConnectionInsercion();
		PreparedStatement p = bd.prepareStatement(sql);
		try {	

			p.setString(1, anuncio.getDescripcion());
			p.setInt(2, anuncio.getIdCategoria());
			p.setInt(3, anuncio.getIdAnunciante());
			p.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			bd.close();
		}
	}

}
