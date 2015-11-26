package edu.uclm.esi.tysweb2015.acciones;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;

public class PonerAnuncio extends ActionSupport {
	private int idCategoria;
	private int idAnunciante;
	private String descripcion;
	private String resultado;
	
	public String execute(){
		try{
			Gestor g= Gestor.get();
			g.ponerAnuncio(idCategoria, idAnunciante, descripcion);
			
			this.resultado="OK";
			return SUCCESS;
		}catch(Exception e){
			this.resultado=e.getMessage();
			return ERROR;
		}
	}
	public void setIdCategoria(int idCategoria) {this.idCategoria = idCategoria;}

	public void setIdAnunciante(int idAnunciante) {this.idAnunciante = idAnunciante;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	public String getResultado(){return resultado;}

}
