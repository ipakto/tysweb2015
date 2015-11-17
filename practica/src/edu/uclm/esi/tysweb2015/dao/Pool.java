package edu.uclm.esi.tysweb2015.dao;

import java.sql.SQLException;
import java.util.Vector;

public class Pool {
	private Vector<Conexion> seleccionLibres;
	private Vector<Conexion> seleccionOcupadas;
	private Vector<Conexion> insercionLibres;
	private Vector<Conexion> insercionOcupadas;
	
	public Pool(int nS, int nI) throws SQLException {
		seleccionLibres=new Vector<>();
		seleccionOcupadas=new Vector<>();
		insercionOcupadas=new Vector<>();
		insercionLibres=new Vector<>();
		for (int i=0;i<nS;i++){
			seleccionLibres.add(new Conexion("root","",this,"s"));
		}
		for (int i=0;i<nI;i++){
			insercionLibres.add(new Conexion("root","",this,"i"));
		}
	}
	public Conexion getConexionDeSeleccion() throws SQLException{
		if(seleccionLibres.size()==0)
			throw new SQLException("No hay conexiones libres");
		Conexion result=seleccionLibres.remove(0);
		seleccionOcupadas.add(result);
		return result;
	}
	public Conexion getConexionDeInsercion() throws SQLException{
		if (insercionLibres.size()==0)
			throw new SQLException("No hay conexiones libres");
		Conexion result=insercionLibres.remove(0);
		insercionOcupadas.add(result);
		return result;
	}
	public void moverASeleccion(Conexion conexion){
		this.seleccionOcupadas.remove(conexion);
		this.seleccionLibres.add(conexion);
	}
	public void moverAInsercion(Conexion conexion){
		this.insercionOcupadas.remove(conexion);
		this.insercionLibres.add(conexion);
	}
}
