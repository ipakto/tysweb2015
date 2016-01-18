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
			int ubicacion) throws Exception {
		Usuario usuario=new Usuario(email,nombre,apellido1,apellido2,telefono,pwd1,ubicacion);
		usuario.insert();
	}
	public Usuario identificar(String email, String pwd) throws Exception{
		// TODO Auto-generated method stub
		Usuario usuario=new Usuario(email,pwd);
		return usuario;
	}
	public void cambiar(int idUsuario, String pwd) throws Exception {
		// TODO Auto-generated method stub
		Usuario u=new Usuario();
		u.setId(idUsuario);
		u.setPwd(pwd);
		u.update();
	}
	public Usuario existe(String email) throws Exception{
		Usuario u=new Usuario();
		u.setEmail(email);
		u.existe();
		return u;
	}
	public void registraUsuarioGoogle(String email, String nombre, String[] apellidos) throws Exception {
		Usuario u=new Usuario(email,nombre,apellidos[0],apellidos[1],null,null,0);
		u.insert(2);
		
	}
	//TOKEN
	public int validar(String token) throws Exception {
		return DAOTokens.comprobar(token);
	}
	//ANUNCIOS
	public int ponerAnuncio(int idCategoria, String emailAnunciante, String descripcion,String titulo, String precio) throws Exception{
		Usuario u=new Usuario(emailAnunciante);
		u.existe();
		Anuncio a= new Anuncio (idCategoria, u.getId(), descripcion, titulo, precio);
		a.insert();
		return a.getIdAnuncio();
		
	}
	public void insertarArchivo(File archivo, int idAnuncio, String tipo) throws Exception {
		Archivo a=new Archivo(archivo,idAnuncio,tipo);
		a.insert();
		
	}
	public void marcarFavorito(int idAnuncio, String emailAnunciante) throws Exception {
		// TODO Auto-generated method stub

		Usuario u=new Usuario(emailAnunciante);
		u.existe();
		Anuncio a=new Anuncio(idAnuncio, u.getId());
		a.marcarFavorito();
	}
	public void modificarPerfil(String emailSesion, String email, String nombre, String apellido1,
		String apellido2, String telefono) throws Exception {
		// TODO Auto-generated method stub
		Usuario u=new Usuario(email,nombre, apellido1 ,apellido2, telefono,"",0);
		u.modificar(emailSesion);
	}
	public void desmarcarFavorito(int idAnuncio, String emailAnunciante) throws Exception {
		// TODO Auto-generated method stub

		Usuario u=new Usuario(emailAnunciante);
		u.existe();
		Anuncio a=new Anuncio(idAnuncio, u.getId());
		a.desmarcarFavorito();
	}
	public void borrarAnuncio(int idAnuncio, String emailAnunciante) throws Exception {
		// TODO Auto-generated method stub

		Usuario u=new Usuario(emailAnunciante);
		u.existe();
		Anuncio a=new Anuncio(idAnuncio, u.getId());
		a.borrarAnuncio();
	}
}
