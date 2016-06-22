package control.work;

import java.io.File;

import org.json.JSONObject;

import control.DataUtil;
import model.Dao;
import model.JSONOut;
import model.Obra;
import model.User;
import model.UserWork;

public class WorkDao extends Dao{
	
	public static final int FIND_BY_ID = 1;
	public static final int LIST_BY_USER = 2;
	public static final int REMOVE_BY_ID = 3;
	public static final int LIST_RECENTS = 4;
	
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
	public JSONObject delete(Object... o) {
		boolean isInvalid = DataUtil.create(getData(), o, JSONOut.Work.NENHUMA_OBRA_ENCONTRADA);
		// Se nao existe nenhum parametro nao eh possivel completar o cadastro
		if(isInvalid) return getData().getJSONObject();
		
		try{	
			UserWork userWork = (UserWork) o[1];
			Obra work = userWork.getWork();
    		File file = new File(work.getFile());
        	
    		if(file.delete() || !file.exists()){
    			System.out.println(file.getName() + " is deleted!");
    			ObraDao.excluiObra(work.getid());
    		}else{
    			System.out.println("Delete operation is failed.");
    			throw new RuntimeException("Delete operation is failed.");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		getData()
			.put(JSONOut.CODE, JSONOut.Work.NAO_FOI_POSSIVEL_DELETAR_ESTA_OBRA)
			.put(JSONOut.DATA, null)
			.put("msg", e.getMessage());
    	}
		
		return getData().getJSONObject();
	}

	@Override
	public JSONObject list(Object... o) {
		boolean isInvalid = DataUtil.create(getData(), o, JSONOut.Work.NENHUMA_OBRA_ENCONTRADA);
		// Se nao existe nenhum parametro nao eh possivel completar o cadastro
		if(isInvalid) return getData().getJSONObject();
		int op = (int) o[0];
		
		switch(op){
			case LIST_BY_USER :
				// Get the work object
				User user = (User) o[1];
				WorkControl.listByUser(getData(), user.getId());
				break;
			case LIST_RECENTS :
				WorkControl.listRecents(this, getData());
				break;
		}
		
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
