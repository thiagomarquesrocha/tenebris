package control;

import org.json.JSONException;
import org.json.JSONObject;

import model.JSONOut;

public class DataUtil {
	public static boolean create(JSONData json, Object[] o, String erroCode){
		boolean isInvalid = false;
		
		JSONData data = json.create();
		// Confirma a operacao se tudo ocorrer normalmente
		data.put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA);
		
		//System.out.println("Total de parametros : " + o.length);
		
		// Se nao existe nenhum parametro nao eh possivel completar o cadastro
		if(o.length <= 0){
			data.put(JSONOut.CODE, erroCode);
			isInvalid = true;
		}
		
		return isInvalid;
	}
	
	public static boolean isInvalid(JSONData data){
		try {
			return !data.getJSONObject().getString(JSONOut.CODE).equals(JSONOut.Sucess.COMPLETADA);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static JSONObject put(JSONObject data, String key, Object value){
		try {
			data.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}
}
