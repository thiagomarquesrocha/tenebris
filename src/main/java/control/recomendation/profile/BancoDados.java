package control.recomendation.profile;

import java.sql.Connection;

import control.ConnectionSingleton;

public class BancoDados {
		
	public static Connection getConnection(){
		return ConnectionSingleton.getInstance().getConnection();
	}
}
