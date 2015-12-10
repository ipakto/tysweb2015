package edu.uclm.esi.tysweb2015.dominio;

import java.io.File;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dao.DAOTokens;

public class Gestor {
	private static Gestor yo;
	public static Gestor get(){
		if(yo==null){
			yo=new Gestor();
		}
		return yo;
	}
	private Gestor(){
		
	}
	//USUARIOS
	public void registra(String email, String nombre, String apellido1, String apellido2, String telefono, String pwd1,
			int ubicacion) throws ClassNotFoundException, SQLException {
		Usuario usuario=new Usuario(email,nombre,apellido1,apellido2,telefono,pwd1,ubicacion);
		usuario.insert();
	}
	public Usuario identificar(String email, String pwd) throws Exception{
		// TODO Auto-generated method stub
		Usuario usuario=new Usuario(email,pwd);
		return usuario;
	}
	public void cambiar(String email, String pwd) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Usuario u=new Usuario(email,null,null,null,null,pwd,0);
		u.update();
	}
	public void existe(String email) throws Exception{
		Usuario u=new Usuario();
		u.setEmail(email);
		u.existe();
	}
	public void registraUsuarioGoogle(String email) throws ClassNotFoundException, SQLException {
		Usuario u=new Usuario(email);
		u.insert(2);
		
	}
	//TOKEN
	public String validar(String token) throws Exception {
		return DAOTokens.comprobar(token);
	}
	//ANUNCIOS
	public Anuncio ponerAnuncio(int idCategoria, int idAnunciante, String descripcion) throws ClassNotFoundException, SQLException{
		Anuncio a= new Anuncio (idCategoria, idAnunciante, descripcion);
		a.insert();
		return a;
		
	}
	public void insertarFoto(File archivo, int idAnuncio) throws ClassNotFoundException, SQLException {
		Foto f=new Foto(archivo,idAnuncio);
		f.insert();
		
	}
}
