package edu.uclm.esi.tysweb2015.acciones;



import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Mail;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class Cambio extends ActionSupport {
	private String email;
	private String resultado;
	
	public String execute(){
		try{
			Gestor gestor=Gestor.get();
			gestor.existe(email);
			Mail.enviarMail(email);
			this.resultado="Te hemos enviado un mail para cambiar tu contraseña.";
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
	
}