package control.work;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import control.Conversor;
import model.JSONOut;
import model.UserWork;

public class WorkSQL {

	public static void findById(WorkDao dao, Object[] o) {
		// Transforma em JSON a saida
		JSONArray resultQuery;
		try {
			UserWork userWork = (UserWork) o[1];
			
			Long work = userWork.getWork().getId();
			Long user = userWork.getUser().getId();
			
			// cria um preparedStatement
			String sql = String.format("SELECT a.*, b.avaliacao FROM (SELECT a.id, a.autor, a.resumo, a.data, LCASE(a.titulo) as titulo, b.nome as area, c.nome as instituicao FROM obra a, area b, instituicao c WHERE a.area=b.id AND c.id = a.instituicao AND a.id = %s) a LEFT OUTER JOIN (SELECT * FROM avaliacao WHERE obra = %s AND usuario = %s) b ON a.id = b.obra", work, work, user);
			PreparedStatement stmt = dao.getCon().prepareStatement(sql);
			
			// Query com os dados do usuario
			ResultSet resultSet = stmt.executeQuery();

			resultQuery = Conversor.convertToJSON(resultSet);
			
			/**
			 *  Gera um objeto do tipo 
			 *  { 
			 *  	id : int, 
			 *  	autor : string, 
			 *  	resumo : string,  
			 *  	data : timestamp,
			 *  	titulo : string,
			 *  	area : string,
			 *  	instituicao : string,
			 *  	avaliacao : int
			 *  }
			 */
			dao.getData().put(JSONOut.DATA, resultQuery);
			
			//System.out.println("JSON : " + json.toString());
			
			stmt.close();
		} catch (SQLException e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.Work.NENHUMA_OBRA_ENCONTRADA)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		} catch (Exception e) {
			dao.getData()
			.put(JSONOut.CODE, JSONOut.Work.NAO_FOI_POSSIVEL_ENCONTRAR_ESTA_OBRA)
			.put(JSONOut.DATA, null);
			e.printStackTrace();
		}
	}
}
