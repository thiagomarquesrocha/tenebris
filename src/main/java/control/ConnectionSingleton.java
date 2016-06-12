package control;

import java.sql.Connection;

import control.factory.ConnectionFactory;

public class ConnectionSingleton {
	private static final ConnectionSingleton instance = new ConnectionSingleton();
	private Connection connection;
	
	private ConnectionSingleton(){
		connection = new ConnectionFactory().getConnection();
	}

	public static ConnectionSingleton getInstance() {
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
