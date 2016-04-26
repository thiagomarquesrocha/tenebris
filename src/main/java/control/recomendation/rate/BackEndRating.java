package control.recomendation.rate;

import java.sql.*;

import control.factory.ConnectionFactory;

public class BackEndRating {
	
	public static void BackEndRat(int UserID, int ItemID, float VarRating) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		  String url = ConnectionFactory.HOST;
		  String dbName = ConnectionFactory.BD;
		  String driver = "com.mysql.jdbc.Driver";
		  String userName = ConnectionFactory.USER; 
		  String password = ConnectionFactory.PASSWORD;
		  
		  /*String url = "jdbc:mysql://localhost:3306/";
		  String dbName = "csv_db";
		  String driver = "com.mysql.jdbc.Driver";
		  String userName = "root"; 
		  String password = "";*/
		  
		  Class.forName(driver).newInstance();
		  DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		  Connection conn = DriverManager.getConnection(url+dbName,userName,password);
		  Statement st = conn.createStatement();
		  
		  try {
		  
			  String sql = "INSERT INTO avaliacao (obra, usuario, avaliacao) VALUES("+ItemID+","+UserID+","+VarRating+");";
			  st.executeUpdate(sql);
			  
			  System.out.print("Recomendação inserida com sucesso.");
			  conn.close();
		  
		  } catch (Exception e) {
			  
			  //e.printStackTrace();
			  
			  String sql = "UPDATE avaliacao SET avaliacao="+VarRating+" WHERE obra="+ItemID+" AND usuario = "+UserID+";";
			  st.executeUpdate(sql);
			  
			  System.out.print("Recomendação inserida com sucesso.");
			  conn.close();
		  
		  }
	}
}