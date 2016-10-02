package control.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	/**
	 * Server openshift
	 */
	
	/*public static final String HOST_NAME = "127.11.163.130";
	public static final String HOST = String.format("jdbc:mysql://%s:3306/", HOST_NAME);
	public static final String BD = "tenebris2016";
	public static final String USER = "adminT6xc2fl";
	public static final String PASSWORD = "hVW5UBvkrf4s";*/
	
	/**
	 * Localhost
	 * @return
	 */
	public static final String HOST_NAME = "localhost";
	public static final String HOST = String.format("jdbc:mysql://%s:3306/", HOST_NAME);
	public static final String BD = "tenebris2016";
	public static final String USER = "root";
	public static final String PASSWORD = "";
	
	public Connection getConnection(){
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
