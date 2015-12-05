package edu.uclm.esi.tysweb2015.dominio;

import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dao.DAOAnuncio;
import edu.uclm.esi.tysweb2015.dao.DAOUsuario;

public class Anuncio {
	
	private int idCategoria;
	private int idAnunciante;
	private String descripcion;

	public  Anuncio (int idC, int idA, String d){
		this.idCategoria=idC;
		this.idAnunciante=idA;
		this.descripcion=d;
	}
	public void insert() throws ClassNotFoundException, SQLException {
		DAOAnuncio.insert(this);		
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public int getIdAnunciante() {
		return idAnunciante;
	}
	public String getDescripcion() {
		return descripcion;
	}
	
}
