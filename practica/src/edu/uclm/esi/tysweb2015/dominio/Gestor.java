package edu.uclm.esi.tysweb2015.dominio;

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
		usuario.insert(usuario);
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
	//TOKEN
	public String validar(String token) throws Exception {
		return DAOTokens.comprobar(token);
	}
	//ANUNCIOS
	public  void ponerAnuncio(int idCategoria, int idAnunciante, String descripcion) throws ClassNotFoundException, SQLException{
		Anuncio a= new Anuncio (idCategoria, idAnunciante, descripcion);
		a.insert();
		
	}
}
