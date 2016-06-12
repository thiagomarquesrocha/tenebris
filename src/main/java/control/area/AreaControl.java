package control.area;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import control.Conversor;
import control.user.UserDao;
import control.user.UserJSON;

public class AreaControl {
	
	/**
	 * Add new area into table
	 * @param conn connection with Mysql
	 * @param area name of area
	 * @return ID from the new area inserted
	 * @throws Exception
	 */
	public static Long add(Connection conn, String area) throws Exception{
		String sql = String.format("INSERT INTO area (nome) VALUES ('%s')", area);
		
		// Tenta cadastrar a nova area no sistema
		PreparedStatement stmt = conn.prepareStatement(sql, UserDao.COLUMMN_ID);
		// executa
		stmt.execute();
		
		// Recupera o ID o metadado da query executada
		ResultSet resultSet = stmt.getGeneratedKeys();
		JSONArray resultQuery = Conversor.convertToJSON(resultSet);
		JSONObject result = UserJSON.generateObjectId(resultQuery);
		long areaId = result.getLong("id");
		stmt.close();
		
		return areaId;
	}
	
	/**
	 * Search any area by name into table
	 * @param conn connection with Mysql
	 * @param area name of area
	 * @return ID from the area found
	 * @throws Exception
	 */
	public static Long findByName(Connection conn, String area) throws Exception {
		String sql = "SELECT * FROM area WHERE nome = '"+area+"'";
		System.out.println("SQL : " + sql);
		PreparedStatement stmt = conn.prepareStatement(sql);
		// Query com os dados do usuario
		ResultSet resultSet = stmt.executeQuery();

		JSONArray resultQuery = Conversor.convertToJSON(resultSet);
		long areaId = resultQuery.getJSONObject(0).getLong("id");
		stmt.close();
		return areaId;
	}
}
