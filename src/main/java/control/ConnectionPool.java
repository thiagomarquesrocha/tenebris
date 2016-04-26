package control;

import java.sql.Connection;

import control.factory.ConnectionFactory;

public class ConnectionPool {
	private static final ConnectionPool instance = new ConnectionPool();
	private Connection connection;
	
	private ConnectionPool(){
		connection = new ConnectionFactory().getConnection();
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
