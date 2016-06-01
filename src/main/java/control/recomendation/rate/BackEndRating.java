package control.recomendation.rate;

import java.sql.*;

import control.ConnectionPool;
import control.factory.ConnectionFactory;

public class BackEndRating {
	
	public static void BackEndRat(int UserID, int ItemID, float VarRating) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Connection conn = ConnectionPool.getInstance().getConnection();
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