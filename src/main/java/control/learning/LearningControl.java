package control.learning;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Dao;
import model.JSONOut;
import model.Learning;

public class LearningControl {

	public static void save(Dao dao, Learning learning) {
		String sql = String.format("INSERT INTO redebayesiana (idObra, idUsuario, relevancia, titulo, pchave) VALUES (%s, %s, %s, '%s', %s)", learning.getWorkId(), learning.getUserId(), learning.getRelevant(), learning.getTitle(), learning.getPchave());
		
		// Tenta cadastrar a nova aprendizagem
		PreparedStatement stmt;
		try {
			stmt = dao.getCon().prepareStatement(sql);
			
			// executa
			stmt.execute();
			
			stmt.close();
			
			dao.getData()
			.put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA)
			.put(JSONOut.DATA, null);
		} catch (SQLException e) {
			e.printStackTrace();
			dao.getData()
			.put(JSONOut.CODE, JSONOut.Learning.NAO_FOI_POSSIVEL_SALVAR_APRENDIZAGEM)
			.put(JSONOut.DATA, null);
		}
	}

}
