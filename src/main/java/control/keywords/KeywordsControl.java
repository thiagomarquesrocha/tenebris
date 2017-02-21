package control.keywords;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import control.Conversor;
import control.GenericJSON;
import control.user.UserDao;

public class KeywordsControl {
	
	public static Long add(Connection conn, String word) throws Exception{
		String sql = String.format("INSERT INTO idpalavrachave (palavrachave) VALUES ('%s')", word);
		
		// Tenta cadastrar a nova palavra chave
		PreparedStatement stmt = conn.prepareStatement(sql, UserDao.COLUMMN_ID);
		// executa
		stmt.execute();
		
		// Recupera o ID o metadado da query executada
		long areaId = GenericJSON.getField("id", stmt);
		stmt.close();
		
		return areaId;
	}
	
	public static Long findByName(Connection conn, String word) throws Exception {
		String sql = "SELECT * FROM idpalavrachave WHERE palavrachave = '"+word+"'";
		System.out.println("SQL : " + sql);
		PreparedStatement stmt = conn.prepareStatement(sql, KeywordsDao.COLUMMN_ID);
		// Query com os dados da palavra
		ResultSet resultSet = stmt.executeQuery();
		JSONArray resultQuery = Conversor.convertToJSON(resultSet);
		long areaId = resultQuery.getJSONObject(0).getLong("idpchave");
		stmt.close();
		return areaId;
	}

	public static void addKeywordAndWork(Connection conn, Long idpalavrachave, Long idObra) throws SQLException {
		String sql = String.format("INSERT INTO obra_palavrachave (obra, palavrachave) VALUES (%s,%s)", idObra, idpalavrachave);
		
		// Tenta cadastrar a nova palavra chave
		PreparedStatement stmt = conn.prepareStatement(sql);
		// executa
		stmt.execute();
	}
}