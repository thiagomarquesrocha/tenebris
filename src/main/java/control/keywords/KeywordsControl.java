package control.keywords;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;

import control.Conversor;
import control.GenericJSON;
import control.user.UserDao;
import model.Dao;
import model.JSONOut;

public class KeywordsControl {
	
	
	public static void clearKeywords(Connection conn, Long workId){
		String sql = String.format("DELETE FROM obra_palavrachave WHERE obra = %s", workId);
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
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
	
	public static void listByWork(Dao dao, Long idWork){
		String sql = String.format("SELECT b.* FROM obra_palavrachave a, idpalavrachave b WHERE a.obra = %s AND a.palavrachave = b.idpchave ", idWork);
		PreparedStatement stmt;
		try {
			stmt = dao.getCon().prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			JSONArray resultQuery = Conversor.convertToJSON(result);
			dao.getData()
			.put(JSONOut.DATA, resultQuery);
		} catch (Exception e) {
			e.printStackTrace();
			dao.getData()
			.put(JSONOut.CODE, JSONOut.Keywords.NENHUMA_PALAVRACHAVE_ENCONTRADA)
			.put(JSONOut.DATA, null);
		}
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
