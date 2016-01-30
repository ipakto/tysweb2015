package edu.uclm.esi.tysweb2015.acciones;

/******************************************************************************************
 * *****************TECNOLOGÍAS Y SISTEMAS DE LA INFORMACIÓN*******************************
 * ******************ESCUELA SUPERIOR DE INFORMÁTICA(UCLM)*********************************
 * ************************PRÁCTICA REALIZADA POR:*****************************************
 *		 * 				- Jorge Vela Plaza											      *
 *		 * 				- Francisco Ruiz Romero											  *
 *		 * 				- Rosana Rodríguez-Bobada Aranda								  *
 * 																						  *
 ******************************************************************************************/

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class RegistrarUsuarioGoogle extends ActionSupport {
	private String email;
	private String resultado;
	private String nombre;
	private String[] apellidos;
	
	public String execute(){
		try{
			Gestor gestor=Gestor.get();
			Usuario u=gestor.existe(email);
			ServletActionContext.getResponse().addHeader("premium", String.valueOf(u.getEstado()));
			this.resultado="OK";
			return SUCCESS;
		}catch(Exception e){
			if(e.getMessage().equals("Usuario no encontrado")){
				try{
					Gestor gestor=Gestor.get();
					gestor.registraUsuarioGoogle(email,nombre,apellidos);
					ServletActionContext.getResponse().addHeader("premium", String.valueOf(0));
					this.resultado="OK";
					return SUCCESS;
				}catch(Exception e2){
					this.resultado=e.getMessage();
					return ERROR;
				}
			}else{
				this.resultado=e.getMessage();
				return ERROR;
			}
		}
	}
	public String getResultado(){
		return this.resultado;
	}
	
	public void setEmail(String email){this.email=email;}
	public void setNombre(String nombre){this.nombre=nombre;}
	public void setApellidos(String apellidos){
		this.apellidos=apellidos.split(" ");
	}
}