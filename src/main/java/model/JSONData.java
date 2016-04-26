package model;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONData {
	private JSONObject data;
	
	public JSONData create(){
		data = new JSONObject();
		return this;
	}
	
	public JSONData put(String key, Object value){
		try {
			data.put(key, value);
		} catch (JSONException e) { e.printStackTrace(); }
		return this;
	}

	public JSONObject getJSONObject() {
		return data;
	}
}
