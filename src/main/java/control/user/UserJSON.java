package control.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.JSONOut;

public class UserJSON {
	public static long getId(JSONArray json) throws JSONException, NullPointerException{
		return json.getJSONObject(0).getLong(model.Config.KEY_ID);
	}
	
	public static long getId(JSONObject data) throws JSONException, NullPointerException{
		return data.getLong(model.Config.KEY_ID);
	}
	
	public static Object get(JSONObject data, String key){
		try {
			return data.getJSONArray(JSONOut.DATA).getJSONObject(0).get(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new Object();
	}
	
	public static JSONObject getUser(JSONArray json) throws JSONException, NullPointerException{
		return json.getJSONObject(0);
	}
	
	public static JSONObject generateObjectId(JSONArray data) throws NullPointerException, JSONException{
		return new JSONObject().put("id", getId(data));
	}
	
	public static boolean hasUser(JSONObject data){
		try {
			if( !data.has(JSONOut.DATA) ) return false;
			JSONArray users = data.getJSONArray(JSONOut.DATA);
			return users.length() > 0 && users.getJSONObject(0).has(UserDao.LABEL_ID);
		} catch (JSONException e) {	e.printStackTrace(); }
		return false;
	}
}
