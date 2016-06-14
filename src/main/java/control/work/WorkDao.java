package control.work;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import control.DataUtil;
import control.JSONUtil;
import model.Dao;
import model.JSONOut;
import model.Obra;
import model.User;

public class WorkDao extends Dao{
	
	public static final int FIND_BY_ID = 1;
	public static final int LIST_BY_USER = 2;
	
	public static final String[] COLUMMN_ID = new String[]{"id"};
	public static final String LABEL_ALL = "*";
	
	private static final WorkDao instance = new WorkDao();
	
	private WorkDao(){
		super();
	}

	public static WorkDao getInstance() {
		return instance;
	}

	@Override
	public JSONObject add(Object... o) {
		return null;
	}

	@Override
	public void delete(Object... o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject list(Object... o) {
		boolean isInvalid = DataUtil.create(getData(), o, JSONOut.Work.NENHUMA_OBRA_ENCONTRADA);
		// Se nao existe nenhum parametro nao eh possivel completar o cadastro
		if(isInvalid) return getData().getJSONObject();
		// Get the work object
		User user = (User) o[1];
		// Lista as obras e retorna para a interface o JSON
		List<Obra> list = null;
		try {
			list = ObraDao.listaObra(user.getId());
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
		getData().put("works", works);
		
		return getData().getJSONObject();
	}

	@Override
	public JSONObject update(Object... o) {
		return null;
	}

	@Override
	public JSONObject find(Object... o) {
		boolean isInvalid = DataUtil.create(getData(), o, JSONOut.Work.NAO_FOI_POSSIVEL_ENCONTRAR_ESTA_OBRA);
		//System.out.println("Tentando executar a busca da obra");
		if(isInvalid) return getData().getJSONObject();
		
		// Recupera a operacao desejada
		int op = (Integer) o[0];
		
		switch (op) {
			case FIND_BY_ID :
				//System.out.println("Executando a busca da obra");
				WorkControl.findById(this, o);
				break;
			default:
				break;
		}
		
		return getData().getJSONObject();
	}
	
}
