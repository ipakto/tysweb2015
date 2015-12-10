package edu.uclm.esi.tysweb2015.dominio;

import java.io.File;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dao.DAOAnuncio;

public class Foto {
	private File archivo;
	private int idAnuncio;
	
	public Foto(File archivo,int idAnuncio){
		this.archivo=archivo;
		this.idAnuncio=idAnuncio;
	}

	public void insert() throws ClassNotFoundException, SQLException {
		DAOAnuncio.insertFoto(this);
	}
	
	public File getArchivo() {
		return archivo;
	}

	public int getIdAnuncio() {
		return idAnuncio;
	}

	
	
	
}
