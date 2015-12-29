package edu.uclm.esi.tysweb2015.acciones;



import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;

public class RegistrarUsuarioGoogle extends ActionSupport {
	private String email;
	private String resultado;
	private String nombre;
	private String[] apellidos;
	
	public String execute(){
		try{
			Gestor gestor=Gestor.get();
			gestor.existe(email);
			this.resultado="OK";
			return SUCCESS;
		}catch(Exception e){
			if(e.getMessage().equals("Usuario no encontrado")){
				try{
					Gestor gestor=Gestor.get();
					gestor.registraUsuarioGoogle(email,nombre,apellidos);
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