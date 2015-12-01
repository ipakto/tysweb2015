package edu.uclm.esi.tysweb2015.acciones;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class PonerAnuncio extends ActionSupport {
	private int idCategoria;
	private String emailAnunciante;
	private String descripcion;
	private String resultado;
	
	public String execute(){
		if(idCategoria==0){
			this.resultado="Por favor, seleccione una categoría.";
			return ERROR;
		}else if(descripcion.equals("")){
			this.resultado="Por favor, introduzca una descripción del anuncio.";
			return ERROR;
		}
		try{
			Gestor g= Gestor.get();
			Usuario u=new Usuario(emailAnunciante);
			u.existe();
			g.ponerAnuncio(idCategoria, u.getId(), descripcion);
			
			this.resultado="OK";
			return SUCCESS;
		}catch(Exception e){
			this.resultado="No se ha podido insertar el anuncio";
			return ERROR;
		}
	}
	public void setIdCategoria(int idCategoria) {this.idCategoria = idCategoria;}

	public void setEmailAnunciante(String emailAnunciante) {this.emailAnunciante = emailAnunciante;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	public String getResultado(){return resultado;}

}
