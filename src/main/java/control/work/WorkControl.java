package control.work;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;

import control.Conversor;
import control.JSONData;
import control.JSONUtil;
import model.Dao;
import model.JSONOut;
import model.Obra;
import model.User;
import model.UserWork;

public class WorkControl {

	public static void findById(WorkDao dao, Object[] o) {
		// Transforma em JSON a saida
		JSONArray resultQuery;
		try {
			UserWork userWork = (UserWork) o[1];
			
			Long work = userWork.getWork().getid();
			Long user = userWork.getUser().getId();
			
			// cria um preparedStatement
			String sql = String.format("SELECT * FROM (SELECT a.*, b.avaliacao, c.nome as autor FROM (SELECT a.id, a.autor as autorId, a.resumo, a.data, LCASE(a.titulo) as titulo, a.imagem as path, a.tipo, b.nome as area, c.nome as instituicao FROM obra a, area b, instituicao c WHERE a.area=b.id AND c.id = a.instituicao AND a.id = %s) a LEFT OUTER JOIN (SELECT * FROM avaliacao WHERE obra = %s AND usuario = %s) b ON a.id = b.obra, autor c WHERE c.id = a.autorId) a LEFT OUTER JOIN (SELECT obra, avg(avaliacao) as media FROM avaliacao GROUP BY obra) b ON a.id = b.obra GROUP BY a.id", work, work, user);
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

	public static String saveFile(HttpServletRequest httpServletRequest, FileItem item) throws IOException {
		System.out.println("Subindo a obra para o servidor...");
    	
		//Pega o diretório /obras dentro do diretório atual de onde a aplicação está rodando
		String caminho = httpServletRequest.getServletContext().getRealPath("/obras") + "/";
		
		// Cria o diretório caso ele não exista
		File diretorio = new File(caminho);
		
		System.out.println("Path : " + caminho);
		
		if (!diretorio.exists()){
			diretorio.mkdir();
		}
		
		// Mandar o arquivo para o diretório informado
		Calendar cal = Calendar.getInstance();
		
		String nome =  cal.get(Calendar.DAY_OF_MONTH)+ "_" + cal.get(Calendar.MONTH) + "_" + cal.get(Calendar.YEAR) + "_" + cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE) + "_" + cal.getTimeInMillis() + ".pdf";
		
		System.out.println("Nome : " + nome);
		
		File file = new File(diretorio, nome);
		FileOutputStream output = new FileOutputStream(file);
		
		InputStream is = item.getInputStream();
		
		byte[] buffer = new byte[2048];
		
		int nLidos;
		
		System.out.println("Absolute Path : " + file.getAbsolutePath());
		
		while ((nLidos = is.read(buffer)) >= 0) {
			output.write(buffer, 0, nLidos);
		}
		
		output.flush();
		output.close();
		
		return file.getAbsolutePath();
	}

	public static void listByUser(JSONData data, Long user) {
		// Lista as obras e retorna para a interface o JSON
		List<Obra> list = null;
		try {
			list = ObraDao.listaObra(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Convert to JSON the list object
		JSONArray works = null;
		try {
			System.out.println(JSONUtil.get(list));
			works = new JSONArray(JSONUtil.get(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		data.put("works", works);
	}

	public static void listRecents(Dao dao, User userActive, JSONData data){
		try {
			// Recupera o ID do usuario se existir
			Long user = (userActive != null)? userActive.getId() : null;
			// Monta a condicao para buscar a avaliacao de um usuario se existir
			String hasUser = (user != null)? "AND b.usuario = " + user : "";
			
			String sql = String.format("SELECT a.*, b.* FROM (SELECT a.*, b.avaliacao, c.nome as autor FROM (SELECT a.id as id, a.autor as autorId, a.resumo, a.data, LCASE(a.titulo) as titulo, b.nome as area, c.nome as instituicao, a.imagem as file, a.cadastradoEm FROM obra a, area b, instituicao c WHERE a.area=b.id AND c.id = a.instituicao) a LEFT OUTER JOIN avaliacao b ON a.id = b.obra %s, autor c WHERE c.id = a.autorId ORDER BY a.file ASC LIMIT 20) a LEFT OUTER JOIN (SELECT obra, avg(avaliacao) as media FROM avaliacao GROUP BY obra) b ON a.id = b.obra GROUP BY a.id", hasUser);
			//System.out.println("SQL : " + sql);
			PreparedStatement stmt = dao.getCon().prepareStatement(sql);
			// Query com os dados do usuario
			ResultSet resultSet = stmt.executeQuery();

			JSONArray resultQuery = Conversor.convertToJSON(resultSet);
			
			dao.getData()
			.put(JSONOut.DATA, resultQuery);
		} catch (Exception e) {
			e.printStackTrace();
			dao.getData()
			.put(JSONOut.CODE, JSONOut.Work.NENHUMA_OBRA_ENCONTRADA)
			.put(JSONOut.DATA, null);
		}
	}

	public static void listTypes(WorkDao dao, JSONData data) {
		try {
			String sql = "SELECT * FROM tipo_obra";
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
}
