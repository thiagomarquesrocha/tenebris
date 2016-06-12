package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import control.ConnectionSingleton;

public class Execucao {

	public static void main(String[] args) throws Exception {
		Connection con = ConnectionSingleton.getInstance().getConnection();
		System.out.println("Conectado!");
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM nivel");
		
		ResultSet res = stmt.executeQuery();
		
		System.out.println("Lista de Niveis");
		
		while(res.next()){
			System.out.println("Id : " + res.getString("id"));
			System.out.println("Nivel : " + res.getString("nome"));
		}
		
		res.close();
		stmt.close();
		con.close();
	}
}
