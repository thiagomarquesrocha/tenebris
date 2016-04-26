package control;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.JSONData;

public class Print {
	public static void json(HttpServletResponse response, JSONData data) throws IOException{
		response.getWriter().append(data.getJSONObject().toString()).close();
	}
	
	public static void json(HttpServletResponse response, JSONObject data) throws IOException{
		response.getWriter().append(data.toString()).close();
	}
}
