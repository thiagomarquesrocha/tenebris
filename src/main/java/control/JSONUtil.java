package control;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JSONUtil {
	
	static Gson gson = new Gson();
	
	public static StringBuffer readJSON(HttpServletRequest request){
		StringBuffer sb = new StringBuffer();
		 
	    try 
	    {
	      BufferedReader reader = request.getReader();
	      String line = null;
	      while ((line = reader.readLine()) != null)
	      {
	        sb.append(line);
	      }
	      reader.close();
	    } catch (Exception e) { e.printStackTrace(); }
	    
	    return sb;
	}
	
	public static Object get(Object object, Class<?> c) throws IOException {
		String saidafinal = gson.toJson(object);
		String yourString = new String(saidafinal.getBytes("UTF-8"));
		return gson.fromJson(yourString, c);
	}
	
	public static String get(Object object) throws IOException {
		String saidafinal = gson.toJson(object);
		String yourString = new String(saidafinal.getBytes("UTF-8"));
		return yourString;
	}
	
	public static void print(HttpServletResponse response, Object object) throws IOException {
		String yourString = get(object);
		response.getWriter().append(yourString);
	}
	
	public static void printWithoutUtf8(HttpServletResponse response, Object object) throws IOException {
		String saidafinal = gson.toJson(object);
		response.getWriter().append(saidafinal);
	}
}
