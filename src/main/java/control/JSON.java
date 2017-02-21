package control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public abstract class JSON {
	public static JSONObject generateObjectField(String name, JSONArray data) throws NullPointerException, JSONException{
		return new JSONObject().put(name, getId(data));
	}
	
	public static long getId(JSONArray json) throws JSONException, NullPointerException{
		return json.getJSONObject(0).getLong(model.Config.KEY_ID);
	}
	
	public static long getField(String name, PreparedStatement stmt) throws Exception{
		ResultSet resultSet = stmt.getGeneratedKeys();
		JSONArray resultQuery = Conversor.convertToJSON(resultSet);
		JSONObject result = generateObjectField(name, resultQuery);
		long areaId = result.getLong(name);
		return areaId;
	}
}
