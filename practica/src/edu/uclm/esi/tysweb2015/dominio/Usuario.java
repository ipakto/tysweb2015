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

import java.sql.Connection;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dao.DAOUsuario;

public class Usuario {

	private String email;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String telefono;
	private String pwd1;
	private int idUbicacion;
	private int id;
	private int estado;

	public Usuario(String email, String nombre, String apellido1, String apellido2, String telefono, String pwd1,
			int ubicacion) {
		this.email=email;
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.telefono=telefono;
		this.pwd1=pwd1;
		this.idUbicacion=ubicacion;
	}

	public Usuario(String email, String pwd) throws Exception {
		// TODO Auto-generated constructor stub
		DAOUsuario.identificar(this, email, pwd);
		//DAOUsuario.existe(this);
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String email) {
		this.email=email;
	}

	public void insert(int... tipoDeOAuth) throws Exception {
		DAOUsuario.insert(this,tipoDeOAuth);
		
	}

	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getEmail() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getPwd1() {
		return pwd1;
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void update() throws Exception {
		DAOUsuario.update(this);
		
	}
	
	public void existe() throws Exception{
		DAOUsuario.existe(this);
	}

	public void setPwd(String pwd) {
		this.pwd1=pwd;
		
	}

	public void modificar(String emailSesion) throws Exception{
		DAOUsuario.modify(this, emailSesion);
	}

	public void setEstado(int estado) {
		this.estado=estado;
		
	}
	public int getEstado() {
		return estado;
		
	}

}
