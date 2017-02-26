package control.keywords;

import org.json.JSONObject;

import control.DataUtil;
import model.Dao;
import model.JSONOut;
import model.Keyword;

public class KeywordsDao extends Dao{
	
	public static final String[] COLUMMN_ID = new String[]{"idpchave"};

	public static final int LIST_KEYWORDS = 1;
	
	private static KeywordsDao instance = new KeywordsDao();
	
	private KeywordsDao(){}

	public static KeywordsDao getInstance() {
		return instance;
	}



	public static void setInstance(KeywordsDao instance) {
		KeywordsDao.instance = instance;
	}



	@Override
	public JSONObject add(Object... o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject delete(Object... o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject update(Object... o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject list(Object... o) {
		boolean isInvalid = DataUtil.create(getData(), o, JSONOut.Keywords.NENHUMA_PALAVRACHAVE_ENCONTRADA);
		// Se nao existe nenhum parametro nao eh possivel completar o cadastro
		if(isInvalid) return getData().getJSONObject();
		int op = (int) o[0];
		
		switch(op){
			case LIST_KEYWORDS :
				Keyword keyword = (Keyword) o[1];
				KeywordsControl.listByWork(this, keyword.getWork());
				break;
		}
		
		return getData().getJSONObject();
	}

	@Override
	public JSONObject find(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

}
