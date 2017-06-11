package control.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import control.Conversor;
import model.Area;
import model.Dao;
import model.JSONOut;
import model.User;

public class UserControl {
	
	public static void findUserByLogin(UserDao dao, Object[] o) {
		// Transforma em JSON a saida
		JSONArray resultQuery;
		try {
			User user = (User) o[1];
			
			// Se agum parametro faltou na requisicao { login ou senha }
			if(!UserRequest.isValidUser(user)){
				dao.getData()
				.put(JSONOut.CODE, JSONOut.User.LOGIN_OU_SENHA_NAO_INFORMADO);
				return;
			}
			
			// cria um preparedStatement
			String sql = String.format("SELECT DISTINCT %s FROM usuario" +
			        " WHERE login = ? AND senha = md5(?)", UserDao.LABEL_ALL);
			PreparedStatement stmt = dao.getCon().prepareStatement(sql);

			// preenche os valores
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			
			// Query com os dados do usuario
			ResultSet resultSet = stmt.executeQuery();

			resultQuery = Conversor.convertToJSON(resultSet);
			
			/**
			 *  Gera um objeto do tipo 
			 *  { 
			 *  	id : int, 
			 *  	nome : string, 
			 *  	login : string,  
			 *  	senha : string,
			 *  	cadastradoEm : int
			 *  }
			 */
			dao.getData().put(JSONOut.DATA, resultQuery);
			
			System.out.println("JSON : " + dao.getData().getJSONObject().toString());
			
			stmt.close();
		} catch (SQLException e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NENHUM_USUARIO_ENCONTRADO)
			.put(JSONOut.DATA, null)
			.put("sql", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_ENCONTRAR_ESTE_USUARIO)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		}
		
		System.out.println("Usuario encontrado!");
	}
	
	public void addUser(UserDao dao, Object[] o) {
		// Transforma em JSON a saida
		JSONArray resultQuery;
		try {
			User user = (User) o[1];
			
			// Se agum parametro faltou na requisicao { login ou senha }
			if(!UserRequest.isValidUser(user)){
				dao.getData()
				.put(JSONOut.CODE, JSONOut.User.FALTA_PARAMETROS_PARA_CADASTRAR);
				return;
			}
			
			// cria um preparedStatement
			String sql = "insert into usuario" +
			        " (nome,login,senha,cadastradoEm)" +
			        " values (?,?,md5(?),now())";
			PreparedStatement stmt = dao.getCon().prepareStatement(sql, UserDao.COLUMMN_ID);

			// preenche os valores
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getLogin());
			stmt.setString(3, user.getPassword());
			
			// executa
			stmt.execute();
			
			// Recupera o ID o metadado da query executada
			ResultSet resultSet = stmt.getGeneratedKeys();
			resultQuery = Conversor.convertToJSON(resultSet);
			
			// Gera um objeto do tipo { id : int }
			dao.getData().put(JSONOut.DATA, UserJSON.generateObjectId(resultQuery));
			
			//System.out.println("JSON : " + json.toString());
			
			stmt.close();
		} catch (SQLException e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.LOGIN_EM_USO)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		} catch (Exception e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_CADASTRAR_USUARIO)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		}
		
		//System.out.println("Novo usuario cadastrado!");
	}

	public static void findUserById(UserDao dao, Object[] o) {
		// Transforma em JSON a saida
		JSONArray resultQuery;
		try {
			User user = (User) o[1];
			
			// cria um preparedStatement
			String sql = String.format("SELECT DISTINCT %s FROM usuario a LEFT OUTER JOIN area b ON " +
			        "a.area = b.id WHERE a.id = ?", UserDao.LABEL_ALL_PROFILE);
			PreparedStatement stmt = dao.getCon().prepareStatement(sql);

			// preenche os valores
			stmt.setLong(1, user.getId());
			
			// Query com os dados do usuario
			ResultSet resultSet = stmt.executeQuery();

			resultQuery = Conversor.convertToJSON(resultSet);
			
			/**
			 *  Gera um objeto do tipo 
			 *  { 
			 *  	id : int, 
			 *  	nome : string, 
			 *  	login : string,  
			 *  	senha : string,
			 *  	cadastradoEm : int
			 *  }
			 */
			dao.getData().put(JSONOut.DATA, resultQuery);
			
			//System.out.println("JSON : " + json.toString());
			
			stmt.close();
		} catch (SQLException e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NENHUM_USUARIO_ENCONTRADO)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		} catch (Exception e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_ENCONTRAR_ESTE_USUARIO)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		}
		
		System.out.println("Usuario encontrado {findById}!");
	}

	public static void updateArea(UserDao dao, Object[] o) {
		try{
			User user = (User) o[1];
			Area area = user.getArea();
			String sql;
			PreparedStatement stmt;
			Long areaId;
			ResultSet resultSet;
			
			sql = String.format("INSERT INTO area (nome) VALUES ('%s')", area.getNome());
			try {
				// Tenta cadastrar a nova area no sistema
				stmt = dao.getCon().prepareStatement(sql, UserDao.COLUMMN_ID);
				// executa
				stmt.execute();
				
				// Recupera o ID o metadado da query executada
				resultSet = stmt.getGeneratedKeys();
				JSONArray resultQuery = Conversor.convertToJSON(resultSet);
				JSONObject result = UserJSON.generateObjectId(resultQuery);
				areaId = result.getLong("id");
				stmt.close();
			} catch (SQLException e) {
				// A area informada ja existe, vamos recuperar o ID dela
				e.printStackTrace();
				areaId = findAreaByName(dao, area.getNome());
			}
			
			// Atualiza o usuario com o ID retornado
			areaId = updateAreaId(dao, areaId, user.getId());
			
			dao.getData()
			.put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA)
			.put(JSONOut.DATA, new JSONObject().put("id", areaId) ); 
			
		}catch(Exception e){
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_COMPLETAR_ACAO)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		}
	}

	private static Long updateAreaId(UserDao dao, Long areaId, Long user) throws Exception {
		String sql = "UPDATE usuario SET area = ? WHERE id = ?";
		PreparedStatement stmt = dao.getCon().prepareStatement(sql, UserDao.COLUMMN_ID);
		stmt.setLong(1, areaId);
		stmt.setLong(2, user);
		stmt.execute();
		stmt.close();
		return areaId;
	}

	private static Long findAreaByName(Dao dao, String area) throws Exception {
		String sql = "SELECT * FROM area WHERE nome = '"+area+"'";
		System.out.println("SQL : " + sql);
		PreparedStatement stmt = dao.getCon().prepareStatement(sql);
		// Query com os dados do usuario
		ResultSet resultSet = stmt.executeQuery();

		JSONArray resultQuery = Conversor.convertToJSON(resultSet);
		long areaId = resultQuery.getJSONObject(0).getLong("id");
		stmt.close();
		return areaId;
	}
	
	public static void listInstitutions(Dao dao) {
		try {
			String sql = "SELECT * FROM instituicao";
			System.out.println("SQL : " + sql);
			PreparedStatement stmt = dao.getCon().prepareStatement(sql);
			// Query com os dados do usuario
			ResultSet resultSet = stmt.executeQuery();

			JSONArray resultQuery = Conversor.convertToJSON(resultSet);
			
			dao.getData()
			.put(JSONOut.DATA, resultQuery);
			
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_COMPLETAR_ACAO)
			.put(JSONOut.DATA, null);
		}
	}

	public static void updateRecommendation(UserDao dao, Object[] o) {
		try{
			User user = (User) o[1];
			String sql;
			PreparedStatement stmt;
			Long areaId;
			ResultSet resultSet;
			
			sql = String.format("UPDATE usuario SET recommendation = %s WHERE id = %s", user.getRecommendation(), user.getId());
			// Tenta atualizar recomendacao no sistema
			stmt = dao.getCon().prepareStatement(sql, UserDao.COLUMMN_ID);
			// executa
			stmt.execute();
			
			stmt.close();
			
			dao.getData()
			.put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA)
			.put(JSONOut.DATA, null ); 
		} catch (Exception e) {
			// Nao foi possivel atualizar a recomendacao
			dao.getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_COMPLETAR_ACAO)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		}
	}
}
