package edu.uclm.esi.tysweb2015.acciones;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Anuncio;
import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class PonerAnuncio extends ActionSupport {
	private int idCategoria;
	private String emailAnunciante;
	private String descripcion;
	private String resultado;
	private String titulo;
	private String precio;
	
	public String execute(){
		if(titulo.equals("")){
			this.resultado="Por favor, introduzca un titulo del anuncio.";
			return ERROR;
		}else if(precio.equals("")){
			this.resultado="Por favor, introduzca un precio para el anuncio.";
			return ERROR;
		}else if(idCategoria==0){
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
			Anuncio a = g.ponerAnuncio(idCategoria, u.getId(), descripcion, titulo, precio);
			ServletActionContext.getRequest().getSession().setAttribute("idAnuncio",a.getIdAnuncio());
			this.resultado="OK";
			return SUCCESS;
		}catch(Exception e){
			this.resultado="No se ha podido insertar el anuncio";
			return ERROR;
		}
	}
	public void setIdCategoria(int idCategoria) {this.idCategoria = idCategoria;}
	public void setTitulo(String titulo) {this.titulo = titulo;}
	public void setPrecio(String precio) {this.precio = precio;}
	public void setEmailAnunciante(String emailAnunciante) {this.emailAnunciante = emailAnunciante;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	public String getResultado(){return resultado;}

}
