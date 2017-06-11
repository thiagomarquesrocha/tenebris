package control.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import control.Conversor;
import control.DataUtil;
import control.JSONData;
import control.factory.InterestFactory;
import model.Dao;
import model.JSONOut;
import model.Preference;
import model.User;

public class UserDao extends Dao{
	
	public static final int ADD_USER = 1;
	public static final int ADD_INTEREST = 2;
	public static final int ADD_INSTITUTION = 3;
	public static final int ADD_AUTH = 4;
	public static final int UPDATE_AREA = 5;
	public static final int LIST_INSTITUTIONS = 6;
	public static final int UPDATE_RECOMMENDATION = 7;
	
	public static final int FIND_BY_LOGIN = 1;
	public static final int FIND_BY_ID = 2;
	
	public static final String[] COLUMMN_ID = new String[]{"id"};
	public static final String LABEL_ALL = "id, nome, login, UNIX_TIMESTAMP(cadastradoEm) as cadastradoEm";
	public static final String LABEL_NAME = "nome";
	public static final String LABEL_ID = "id";
	
	private static final UserDao instance = new UserDao();
	public static final String LABEL_ALL_PROFILE = "a.id, a.nome, a.login, UNIX_TIMESTAMP(a.cadastradoEm) as cadastradoEm, a.recommendation, b.nome as area, b.id as areaId";
		
	private UserDao(){
		super();
	}

	public static UserDao getInstance() {
		return instance;
	}

	@Override
	public JSONObject add(Object... o) {
		
		JSONData data = getData().create();
		
		data.put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA);
		
		// Se nao existi nenhum parametro nao eh possivel completar o cadastro
		if(o.length <= 0){
			data.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_COMPLETAR_ACAO);
			return data.getJSONObject();
		}
		
		// Recupera a operacao desejada
		int op = (Integer) o[0];
		
		switch(op){
			case ADD_USER : // Executa o comando para adicionar um usuario
				addUser(o);
				break;
			case ADD_INTEREST : case ADD_INSTITUTION : case ADD_AUTH :
				PreferencesSQL.saveNewPreference(this, o);
				// O interesse ja existe
				if(PreferenceRequest.isUsed(data)){
					System.out.println("Ö interesse ja estah em uso");
					PreferencesSQL.find(this, o);
				}
				
				Long id = PreferenceRequest.getId(data);
				
				PreferencesSQL.saveUserAndPreference(this, o, id);
				
				//System.out.println("Id : " + id);
				//System.out.println("JSON : " + data.getJSONObject().toString());
					
				break;
		}
		
		return getData().getJSONObject();
	}

	@Override
	public JSONObject delete(Object... o) {
		boolean isInvalid = DataUtil.create(getData(), o, JSONOut.User.NAO_FOI_POSSIVEL_COMPLETAR_ACAO);
		if(isInvalid) return getData().getJSONObject();
		
		// Recupera a operacao desejada
		int op = (Integer) o[0];
		
		switch(op){
			case ADD_INTEREST : case ADD_INSTITUTION : case ADD_AUTH :
				PreferencesSQL.remove(this, o);
				break;
		}
		
		return getData().getJSONObject();
	}

	@Override
	public JSONObject list(Object... o) {
		boolean isInvalid = DataUtil.create(getData(), o, JSONOut.User.NAO_FOI_POSSIVEL_COMPLETAR_ACAO);
		if(isInvalid) return getData().getJSONObject();
		
		// Recupera a operacao desejada
		int op = (Integer) o[0];
		
		switch(op){
			case LIST_INSTITUTIONS :
				UserControl.listInstitutions(this);
				break;
		}
		
		return getData().getJSONObject();
	}

	@Override
	public JSONObject update(Object... o) {
		
		boolean isInvalid = DataUtil.create(getData(), o, JSONOut.User.NAO_FOI_POSSIVEL_ENCONTRAR_ESTE_USUARIO);
		
		if(isInvalid) return getData().getJSONObject();
		
		// Recupera a operacao desejada
		int op = (Integer) o[0];
		
		switch(op){
			case UPDATE_AREA :
				UserControl.updateArea(this, o);
				break;
			case UPDATE_RECOMMENDATION :
				UserControl.updateRecommendation(this, o);
				break;
		}
		
		return getData().getJSONObject();
	}

	@Override
	public JSONObject find(Object... o) {
		boolean isInvalid = DataUtil.create(getData(), o, JSONOut.User.NAO_FOI_POSSIVEL_ENCONTRAR_ESTE_USUARIO);
		
		if(isInvalid) return getData().getJSONObject();
		
		// Recupera a operacao desejada
		int op = (Integer) o[0];
		
		switch (op) {
			case FIND_BY_LOGIN:
				UserControl.findUserByLogin(this, o);
				break;
			case FIND_BY_ID :
				UserControl.findUserById(this, o);
				Preference pref = InterestFactory.create();
				int total = o.length;
				int last = o.length-1;
				Object[] params = new Object[total+3];
				for(int i=0; i<total; i++){
					params[i] = o[i];
				}
				// Busca as tags
				pref.setField("tag");
				params[last+1] = pref;
				params[last+2] = "usuario_tags";
				params[last+3] = "tags";
				PreferencesSQL.list(this, params);
				// Busca as instituicoes
				pref.setField("instituicao");
				params[last+1] = pref;
				params[last+2] = "usuario_instituicoes";
				params[last+3] = "instituicoes";
				PreferencesSQL.list(this, params);
				// Busca os autores
				pref.setField("autor");
				params[last+1] = pref;
				params[last+2] = "usuario_autores";
				params[last+3] = "autores";
				PreferencesSQL.list(this, params);
				break;
			default:
				break;
		}
		
		return getData().getJSONObject();
	}
	
	private void addUser(Object[] o) {
		// Transforma em JSON a saida
		JSONArray resultQuery;
		try {
			User user = (User) o[1];
			
			// Se agum parametro faltou na requisicao { login ou senha }
			if(!UserRequest.isValidUser(user)){
				getData()
				.put(JSONOut.CODE, JSONOut.User.FALTA_PARAMETROS_PARA_CADASTRAR);
				return;
			}
			
			// cria um preparedStatement
			String sql = "INSERT INTO usuario" +
			        " (nome,login,senha,cadastradoEm)" +
			        " values (?,?,md5(?),now())";
			PreparedStatement stmt = getCon().prepareStatement(sql, COLUMMN_ID);

			// preenche os valores
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getLogin());
			stmt.setString(3, user.getPassword());
			
			// executa
			stmt.execute();
			
			// Recupera o ID o metadado da query executada
			ResultSet resultSet = stmt.getGeneratedKeys();
			resultQuery = Conversor.convertToJSON(resultSet);
			
			// Gera um objeto do tipo [ { id : int } ]
			JSONObject obj = UserJSON.generateObjectId(resultQuery);
			Long id = obj.getLong("id");
			getData().put(JSONOut.DATA, new JSONArray().put(obj));
			
			// Adiciona a preferencia do usuario por instituicao
			sql = "INSERT INTO usuario_instituicoes" +
			        " (usuario,instituicao)" +
			        " VALUES (?,?)";
			stmt = getCon().prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.setLong(2, user.getInstitution());
			
			stmt.execute();
			
			//System.out.println("JSON : " + json.toString());
			
			stmt.close();
		} catch (SQLException e) {
			getData()
			.put(JSONOut.CODE, JSONOut.User.LOGIN_EM_USO)
			.put(JSONOut.DATA, null)
			.put("sql", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			getData()
			.put(JSONOut.CODE, JSONOut.User.NAO_FOI_POSSIVEL_CADASTRAR_USUARIO)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		}
		
		//System.out.println("Novo usuario cadastrado!");
	}
}
