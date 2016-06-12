package control.work;

import org.json.JSONObject;

import control.DataUtil;
import control.JSONData;
import model.Dao;
import model.JSONOut;

public class WorkDao extends Dao{
	
	public static final int FIND_BY_ID = 1;
	
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
		JSONData data = getData().create();
		
		data.put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA);
		
		// Se nao existe nenhum parametro nao eh possivel completar o cadastro
		if(o.length <= 0){
			data.put(JSONOut.CODE, JSONOut.Work.NAO_FOI_POSSIVEL_ENCONTRAR_ESTA_OBRA);
			return data.getJSONObject();
		}
		
		return null;
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
