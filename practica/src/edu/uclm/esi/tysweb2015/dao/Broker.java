package edu.uclm.esi.tysweb2015.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class Broker {
	private String url="jdbc:mysql://localhost:3306/tysweb2015?noAccessToProcedureBodies=true";
	private GenericObjectPool<PoolableConnection> poolSelects;
	private GenericObjectPool<PoolableConnection> poolInserts;
	
	private Broker() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();
			poolConfig.setMaxTotal(50);

			PoolableConnectionFactory poolableConnectionFactorySelects=
					new PoolableConnectionFactory(new DriverManagerConnectionFactory(url, "root", ""), null); 
			this.poolSelects=new GenericObjectPool<PoolableConnection>(poolableConnectionFactorySelects, poolConfig);
			poolableConnectionFactorySelects.setPool(poolSelects);
			
			PoolableConnectionFactory poolableConnectionFactoryInserts=
					new PoolableConnectionFactory(new DriverManagerConnectionFactory(url, "root", ""), null);
			this.poolInserts=new GenericObjectPool<PoolableConnection>(poolableConnectionFactoryInserts, poolConfig);
			poolableConnectionFactoryInserts.setPool(poolInserts);
		}
		catch (Exception e) {}
	}
	
	private static class BrokerHolder {
		private static Broker broker=new Broker();
	}

	public static Broker get() throws Exception {
		return BrokerHolder.broker;
	}

	public Connection getConnectionSeleccion() throws Exception {
		return this.poolSelects.borrowObject();
	}
	
	public Connection getConnectionInsercion() throws Exception {
		return this.poolInserts.borrowObject();
	}

	public Connection getConnection(String userName, String pwd) throws Exception {
		Connection bd=DriverManager.getConnection(url, userName, pwd);
		bd.close();
		return this.poolInserts.borrowObject();
	}
	public boolean existe(String userName, String pwd) throws Exception {
		boolean resultado=false;
		try{
			Connection r=DriverManager.getConnection(url,userName,pwd);
			resultado=true;
			r.close();
		}catch(Exception e){
			throw e;
		}
		return resultado;
	}
}