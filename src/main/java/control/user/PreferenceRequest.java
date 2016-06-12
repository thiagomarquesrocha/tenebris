package control.user;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import control.JSONData;
import control.factory.InterestFactory;
import model.JSONOut;
import model.Preference;
import model.Request;

public class PreferenceRequest extends Request<Preference> {
	
	private final static PreferenceRequest instance = new PreferenceRequest();
	private static final String FIELD = "field";
	private static final String VALUE = "value";
	private static final String TYPE = "type";
	private static final String ACTION = "action";
	private static final String USER = "user";
	
	private PreferenceRequest(){}

	public static PreferenceRequest getInstance() {
		return instance;
	}

	@Override
	public Preference get(HttpServletRequest request) {
		try {
			Preference interest = InterestFactory.create();
			//System.out.println("ID : " + request.getParameter(ID));
			interest.setField(String.valueOf(request.getParameter(FIELD)));
			interest.setValue(String.valueOf(request.getParameter(VALUE)));
			interest.setType(String.valueOf(request.getParameter(TYPE)));
			interest.setAction(Integer.valueOf(request.getParameter(ACTION)));
			interest.setUser(Long.valueOf(request.getParameter(USER)));
			return interest;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Preference post(StringBuffer sb) {
		try {
			Preference interest = InterestFactory.create();
			JSONObject json = new JSONObject(sb.toString());
			//System.out.println("ID : " + request.getParameter(ID));
			interest.setField(json.getString(FIELD));
			interest.setValue(json.getString(VALUE));
			interest.setType(json.getString(TYPE));
			interest.setAction(json.getInt(ACTION));
			interest.setUser(json.getLong(USER));
			return interest;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isUsed(JSONData data){
		try {
			return data.getJSONObject().getString(JSONOut.CODE).equals(JSONOut.User.PREFERENCIA_EM_USO);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Recupera o ID do primeiro item do array
	 * @param data
	 * @return
	 */
	public static long getId(JSONData data){
		try {
			return data.getJSONObject().getJSONArray(JSONOut.DATA).getJSONObject(0).getLong("id");
		} catch (JSONException e) {
			e.printStackTrace();
			
			try {
				return data.getJSONObject().getJSONObject(JSONOut.DATA).getLong("id");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		
		return 0;
	}
}
