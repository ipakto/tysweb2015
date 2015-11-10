package edu.uclm.esi.tysweb2015.acciones;



import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;

public class EfectuarCambio extends ActionSupport {
	private String token;
	private String pwd1;
	private String pwd2;
	private String resultado;
	
	public String execute(){
		if (validar()!=null){
			return ERROR;
		}else{
			try{
				Gestor gestor=Gestor.get();
				String email=gestor.validar(token);
				gestor.cambiar(email,pwd1);
				this.resultado="OK";
				return SUCCESS;
			}catch(Exception e){
				this.resultado=e.getMessage();
				return ERROR;
			}
		}
	}
	private String validar() {
		if (this.pwd1==null || this.pwd1.length()==0){
			return "Las password no puedo estar vacía";
		}
		if(!this.pwd1.equals(this.pwd2)){
			return "Las passwords no coinciden";
		}
		return null;
	}
	public String getResultado(){
		return this.resultado;
	}
	public void setToken(String token){
		String[] em=token.split("u=");
		this.token=em[1];
	}
	
	public void setPwd1(String pwd1){this.pwd1=pwd1;}
	public void setPwd2(String pwd2){this.pwd2=pwd2;}
}