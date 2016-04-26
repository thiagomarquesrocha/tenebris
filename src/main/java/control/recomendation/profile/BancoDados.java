package control.recomendation.profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import control.factory.ConnectionFactory;

public class BancoDados {
	private static final String HOST = ConnectionFactory.HOST;
	private static final String BD = ConnectionFactory.BD;
	private static final String USER = ConnectionFactory.USER;
	private static final String PASSWORD = ConnectionFactory.PASSWORD;
		
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection(HOST + BD, USER, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
