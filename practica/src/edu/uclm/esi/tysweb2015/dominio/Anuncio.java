package edu.uclm.esi.tysweb2015.dominio;

/******************************************************************************************
 * *****************TECNOLOGÍAS Y SISTEMAS DE LA INFORMACIÓN*******************************
 * ******************ESCUELA SUPERIOR DE INFORMÁTICA(UCLM)*********************************
 * ************************PRÁCTICA REALIZADA POR:*****************************************
 *		 * 				- Jorge Vela Plaza											      *
 *		 * 				- Francisco Ruiz Romero											  *
 *		 * 				- Rosana Rodríguez-Bobada Aranda								  *
 * 																						  *
 ******************************************************************************************/

import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dao.DAOAnuncio;
import edu.uclm.esi.tysweb2015.dao.DAOUsuario;

public class Anuncio {
	
	private int idCategoria;
	private int idAnunciante;
	private String descripcion;
	private int idAnuncio;
	private String titulo;
	private String precio;
	

	public  Anuncio (int idC, int idA, String d, String t, String p){
		this.idCategoria=idC;
		this.idAnunciante=idA;
		this.descripcion=d;
		this.titulo=t;
		this.precio=p;
	}
	public Anuncio (int idAnuncio, int idAnunciante){
		this.idAnunciante=idAnunciante;
		this.idAnuncio=idAnuncio;
	}
	public void insert() throws Exception {
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
	public int getIdAnuncio(){
		return idAnuncio;
	}
	public void setIdAnuncio(int idAnuncio){
		this.idAnuncio=idAnuncio;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getPrecio() {
		return precio;
	}
	
	public void marcarFavorito() throws Exception {
		// TODO Auto-generated method stub
		DAOAnuncio.marcarFavorito(this);	
	}
	public void desmarcarFavorito() throws Exception {
		// TODO Auto-generated method stub
		DAOAnuncio.desmarcarFavorito(this);
	}
	public void borrarAnuncio() throws Exception {
		// TODO Auto-generated method stub
		DAOAnuncio.borrarAnuncio(this);
	}
	
}