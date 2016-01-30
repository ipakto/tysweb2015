package edu.uclm.esi.tysweb2015.dominio;

/******************************************************************************************
 * *****************TECNOLOG�AS Y SISTEMAS DE LA INFORMACI�N*******************************
 * ******************ESCUELA SUPERIOR DE INFORM�TICA(UCLM)*********************************
 * ************************PR�CTICA REALIZADA POR:*****************************************
 *		 * 				- Jorge Vela Plaza											      *
 *		 * 				- Francisco Ruiz Romero											  *
 *		 * 				- Rosana Rodr�guez-Bobada Aranda								  *
 * 																						  *
 ******************************************************************************************/

import java.io.File;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dao.DAOAnuncio;

public class Archivo {
	private File archivo;
	private int idAnuncio;
	private String tipo;
	
	public Archivo(File archivo,int idAnuncio,String tipo){
		this.archivo=archivo;
		this.idAnuncio=idAnuncio;
		this.tipo=tipo;
	}

	public void insert() throws Exception {
		DAOAnuncio.insertArchivo(this);
	}
	
	public File getArchivo() {
		return archivo;
	}

	public int getIdAnuncio() {
		return idAnuncio;
	}

	public String getTipo() {
		return tipo;
	}

	
	
	
}
