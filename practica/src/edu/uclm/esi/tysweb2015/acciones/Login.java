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

public class Login extends ActionSupport {
	private String email;
	private String pwd;
	private String resultado;
	
	public String execute(){
		try{
			Gestor gestor=Gestor.get();
			Usuario usuario=gestor.identificar(email,pwd);
			ServletActionContext.getRequest().getSession().setAttribute("usuario",usuario);
			ServletActionContext.getResponse().addHeader("premium", String.valueOf(usuario.getEstado()));
			this.resultado="OK";
			return SUCCESS;
		}catch(Exception e){
			this.resultado=e.getMessage();
			return ERROR;
		}
	}
	public String getResultado(){
		return this.resultado;
	}
	public void setEmail(String email){this.email=email;}
	
	public void setPwd(String pwd){this.pwd=pwd;}
}