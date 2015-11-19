package edu.uclm.esi.tysweb2015.dominio;

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

	public void insert(Usuario usuario) throws ClassNotFoundException, SQLException {
		DAOUsuario.insert(this);
		
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

	public void update() throws ClassNotFoundException, SQLException {
		DAOUsuario.update(this);
		
	}
	
	public void existe() throws Exception{
		DAOUsuario.existe(this);
	}


}
