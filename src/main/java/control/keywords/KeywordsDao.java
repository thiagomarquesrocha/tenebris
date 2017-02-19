package control.keywords;

import org.json.JSONObject;

import model.Dao;

public class KeywordsDao extends Dao{
	
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
	public JSONObject list(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject find(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

}
