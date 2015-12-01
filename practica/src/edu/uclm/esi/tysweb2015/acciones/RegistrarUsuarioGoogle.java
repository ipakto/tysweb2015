package edu.uclm.esi.tysweb2015.acciones;



import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;

public class RegistrarUsuarioGoogle extends ActionSupport {
	private String email;
	private String resultado;
	
	public String execute(){
		try{
			Gestor gestor=Gestor.get();
			gestor.registraUsuarioGoogle(email);
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
}