package control.recomendation.profile;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PerfilUsuario {
	String Termos, Autor, Unidade, Area;
	
	public void cadastrarUsuario(String Termos, String Autor, String Unidade, String Area) throws SQLException{
		String query = "INSERT INTO `perfil de usuário`(`Termos`, `Autor`, `Unidade`, `Area`) VALUES (\"" + Termos + "\", \"" + Autor + "\", \"" + Unidade + "\", \"" + Area +"\")";
	    PreparedStatement statement = Principal.bd.prepareStatement(query);
	    statement.execute();		
	}

}
