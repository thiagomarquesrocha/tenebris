package control.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import control.Conversor;
import model.JSONOut;
import model.Preference;
import model.User;

public class PreferencesSQL {
	
	
	public static void list(UserDao dao, Object[] o){
		try {
			User u = (User) o[1];
			Preference preference = (Preference) o[2];
			String table = (String) o[3];
			String out = (String) o[4];
			String field = preference.getField();
			Long user = u.getId();
			// SELECT * FROM `usuario_tags` a, tag b WHERE usuario = 2 AND b.id = a.tag
			String sql = String.format("SELECT b.* FROM %s a, %s b WHERE usuario = %s AND b.id = a.%s", table, field, user, field);
			// cria um preparedStatement
			Statement stmt = dao.getCon().createStatement();
			
			// executa
			ResultSet resultSet = stmt.executeQuery(sql);
			
			JSONArray resultQuery = Conversor.convertToJSON(resultSet);
			
			//System.out.println("SQL : " + sql);
			
			//System.out.println("JSON : " + resultQuery.toString());
			
			// Gera um objeto do tipo { id : int }
			JSONObject obj = dao.getData().getJSONObject();
			if(obj.has(JSONOut.DATA))
				obj.getJSONArray(JSONOut.DATA).getJSONObject(0).put(out, resultQuery);
			//dao.getData().put(JSONOut.DATA, resultQuery);
			
			//System.out.println("JSON : " + dao.getData().getJSONObject().toString());
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_LISTAR_AS_PREFERENCIAS)
			.put(JSONOut.DATA, null);
		} catch(Exception e){
			e.printStackTrace();
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_LISTAR_AS_PREFERENCIAS)
			.put(JSONOut.DATA, null);
		}
	}
	
	public static void find(UserDao dao, Object[] o){
		try {
			Preference preference = (Preference) o[2];
			String table = preference.getField();
			String name = (String) preference.getValue();
			String sql = String.format("SELECT * FROM %s WHERE nome = '%s'", table, name);
			// cria um preparedStatement
			Statement stmt = dao.getCon().createStatement();
			
			// executa
			ResultSet resultSet = stmt.executeQuery(sql);
			
			JSONArray resultQuery = Conversor.convertToJSON(resultSet);
			
			//System.out.println("SQL : " + sql);
			
			//System.out.println("JSON : " + resultQuery.toString());
			
			// Gera um objeto do tipo { id : int }
			dao.getData().put(JSONOut.DATA, resultQuery);
			
			//System.out.println("JSON : " + dao.getData().getJSONObject().toString());
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_CADASTRAR_PREFERENCIA)
			.put(JSONOut.DATA, null);
		} catch(Exception e){
			e.printStackTrace();
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_CADASTRAR_PREFERENCIA)
			.put(JSONOut.DATA, null);
		}
	}
	
	public static void saveNewPreference(UserDao dao, Object[] o){
		save(dao, o, null);
	}
	
	public static void saveUserAndPreference(UserDao dao, Object[] o, Long id){
		save(dao, o, id);
	}
	
	private static void save(UserDao dao, Object[] o, Long id){
		// Transforma em JSON a saida
		JSONArray resultQuery;
		
		try {
			Preference preference = (Preference) o[2];
			String table = preference.getField();
			String tableRelationship = (String) o[1];
			
			if(id == null){
				PreparedStatement stmt;
				// cria um preparedStatement
				String sql = String.format("INSERT INTO %s (nome) VALUES (?)", table);
				stmt = dao.getCon().prepareStatement(sql, UserDao.COLUMMN_ID);
				// preenche os valores
				stmt.setObject(1, preference.getValue());
				// executa
				stmt.execute();
				// Recupera o ID o metadado da query executada
				ResultSet resultSet = stmt.getGeneratedKeys();
				resultQuery = Conversor.convertToJSON(resultSet);
				// Gera um objeto do tipo { id : int }
				dao.getData().put(JSONOut.DATA, UserJSON.generateObjectId(resultQuery));
				
				stmt.close();
			}else{
				String sql = String.format("INSERT INTO %s (usuario, %s) VALUES (%s, %s)", tableRelationship, table, preference.getUser(), id);
				System.out.println("SQL : " + sql);
				PreparedStatement statement = dao.getCon().prepareStatement(sql);
				statement.execute();
				dao.getData().put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA).put(JSONOut.DATA, null);
				statement.close();
			}
			
			
			//System.out.println("JSON : " + dao.getData().getJSONObject().toString());
		} catch (SQLException e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.PREFERENCIA_EM_USO)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		} catch (Exception e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_CADASTRAR_PREFERENCIA)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		}
		
		//System.out.println("Novo usuario cadastrado!");
	}
	
	public static void add(UserDao dao, Object[] o){
				try {
					String table = (String) o[1];
					Preference preference = (Preference) o[2];
					
					// Se agum parametro faltou na requisicao { login ou senha }
					if(preference == null){
						dao.getData()
						.put(JSONOut.CODE, JSONOut.User.FALTA_PARAMETROS_PARA_CADASTRAR);
						return;
					}
					
					// Cria um preparedStatement
					String sql = String.format("INSERT INTO %s" +
					        " (usuario, %s)" +
					        " VALUES (?,?)", table, preference.getField());
					
					//System.out.println("SQL : " + sql);
					
					PreparedStatement stmt = dao.getCon().prepareStatement(sql);
					
					// preenche os valores
					stmt.setLong(1, preference.getUser());
					stmt.setLong(2, (long) preference.getValue());
					
					// executa
					stmt.execute();
					
					// Gera um objeto do tipo { id : int }
					dao.getData().put(JSONOut.DATA, null);
					
					//System.out.println("JSON : " + json.toString());
					
					stmt.close();
				} catch (SQLException e) {
					dao.getData()
					.put(JSONOut.CODE, JSONOut.User.PREFERENCIA_EM_USO)
					.put(JSONOut.DATA, null);
					e.printStackTrace();
				} catch (Exception e) {
					dao.getData()
					.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_CADASTRAR_PREFERENCIA)
					.put(JSONOut.DATA, null);
					e.printStackTrace();
				}
				
				//System.out.println("Novo usuario cadastrado!");
	}

	public static void remove(UserDao dao, Object[] o) {
		try {
			Preference preference = (Preference) o[2];
			String field = preference.getField();
			String tableRelationship = (String) o[1];		
			String id = preference.getValue().toString();
			Long userId = preference.getUser();
			String sql = String.format("DELETE FROM %s WHERE usuario = %s AND %s = %s", tableRelationship, userId, field, id);
			System.out.println("SQL : " + sql);
			PreparedStatement statement = dao.getCon().prepareStatement(sql);
			statement.execute();
			dao.getData().put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA).put(JSONOut.DATA, null);
			statement.close();
		} catch (Exception e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_COMPLETAR_ACAO)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		}
	}
}
