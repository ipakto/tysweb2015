package edu.uclm.esi.tysweb2015.dao.test;

/******************************************************************************************
 * *****************TECNOLOGÍAS Y SISTEMAS DE LA INFORMACIÓN*******************************
 * ******************ESCUELA SUPERIOR DE INFORMÁTICA(UCLM)*********************************
 * ************************PRÁCTICA REALIZADA POR:*****************************************
 *		 * 				- Jorge Vela Plaza											      *
 *		 * 				- Francisco Ruiz Romero											  *
 *		 * 				- Rosana Rodríguez-Bobada Aranda								  *
 * 																						  *
 ******************************************************************************************/

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import edu.uclm.esi.tysweb2015.dao.Broker;


public class BrokerTest {

	
	public void testAperturaDeConexiones() throws Exception {
		Broker bd=Broker.get();
		int i=1;
		boolean error=false;
		do{/*
			try{
				bd.getDB("pepe@pepe.com","1234");
			}catch(SQLException e){
				error=true;
			}*/
			System.out.println(i++);
		}while(!error);
		fail("Not yet implemented");
	}
	
	public void testTiemposDeInsercion(){
		String sql="INSERT INTO anuncios (descripcion,idCategoria,idAnunciante)"+"values(?,?,?);";
		//Método 1:sobre una conexión abierta
		try{
			long ti=System.currentTimeMillis();
			Connection bd=Broker.get().getConnectionInsercion();
			PreparedStatement p=bd.prepareStatement(sql);
			for(int i=0;i<500;i++){
				p.setString(1, "anuncio de una moto de Prueba"+i);
				p.setInt(2, 1);
				p.setInt(3, 33);
				p.executeUpdate();
			}
			bd.close();
			long tf=System.currentTimeMillis();
			System.out.println("Tiempo conexión abierta"+(tf-ti)/1000);
		}catch(Exception e){
			fail("Excepcion inesperada; "+e);
		}
		//Método 1:sobre una conexión que vamos abriendo y cerrando
		try{
			long ti2=System.currentTimeMillis();
			for(int i=0;i<500;i++){
				Connection bd=Broker.get().getConnectionInsercion();
				PreparedStatement p=bd.prepareStatement(sql);
				p.setString(1, "anuncio de una moto de Prueba"+i);
				p.setInt(2, 1);
				p.setInt(3, 33);
				p.executeUpdate();
				bd.close();
			}
			long tf2=System.currentTimeMillis();
			System.out.println("Tiempo conexión abierta"+(tf2-ti2)/1000);
		}catch(Exception e){
			fail("Excepcion inesperada; "+e);
		}
	}
 
	public void testInsertarEnPrueba(){
		String sql="INSERT INTO prueba (cadena1, cadena2) values(?,?);";
		//Método 1:sobre una conexión abierta
		try{
			long ti=System.currentTimeMillis();
			Connection bd=Broker.get().getConnectionInsercion();
			PreparedStatement p=bd.prepareStatement(sql);
			String cadenaAleatoria;
			for(int i=0;i<300;i++){
				cadenaAleatoria=getCadenaAleatoria();
				p.setString(1,cadenaAleatoria);
				p.setString(2, cadenaAleatoria);
				p.executeUpdate();
			}
			bd.close();
			long tf=System.currentTimeMillis();
			System.out.println("Tiempo de inserción: "+(tf-ti)/1000);
		}catch(Exception e){
			fail("Excepcion inesperada; "+e);
		}
	}
	@Test
	public void testBusquedaEnPrueba(){
		String sql="SELECT * FROM prueba order by cadena1";
		String sql2="SELECT * FROM prueba order by cadena2";
		try{
			long ti=System.currentTimeMillis();
			Connection bd=Broker.get().getConnectionSeleccion();
			PreparedStatement p=bd.prepareStatement(sql);
			ResultSet r=p.executeQuery();
			r.next();
			System.out.println(r.getString(1));
			long tf=System.currentTimeMillis();
			System.out.println("Tiempo busqueda1: "+(tf-ti));
			

			long ti2=System.currentTimeMillis();
			Connection bd2=Broker.get().getConnectionSeleccion();
			PreparedStatement p2=bd.prepareStatement(sql);
			ResultSet r2=p.executeQuery();
			r2.next();
			System.out.println(r2.getString(1));
			long tf2=System.currentTimeMillis();
			System.out.println("Tiempo busqueda2: "+(tf2-ti2));
		}catch(Exception e){
			fail("Excepcion inesperada; "+e);
		}
	}
	private String getCadenaAleatoria() {
		String r="";
		int longitud=new Random().nextInt(255)+1;
		for(int i=0;i<longitud;i++){
			r+=(char)( new Random().nextInt(100)+65);
		}
		return r;
	}
}
