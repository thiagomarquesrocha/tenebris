package control.area;

import org.json.JSONObject;

import model.Dao;

public class AreaDao extends Dao{
	
	private static AreaDao instance = new AreaDao();
	
	private AreaDao(){}
	
	public static AreaDao getInstance(){
		return instance;
	}

	@Override
	public JSONObject add(Object... o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object... o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject update(Object... o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject list(Object... o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject find(Object... o) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
