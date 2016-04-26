package control.user;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import control.factory.UserFactory;
import model.User;

public class ProfileRequest {
	
	public static final String NAME = "name";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	
	public static User addUser(HttpServletRequest request) throws NullPointerException{
		User user = UserFactory.create();
		user.setName(request.getParameter(NAME));
		user.setLogin(request.getParameter(LOGIN));
		user.setPassword(request.getParameter(PASSWORD));
		return user;
	}
	
	public static User addUser(String json) {
		User user = UserFactory.create();
		JSONObject oUser;
		try {
			oUser = new JSONObject(json);
			user.setLogin(oUser.getString(LOGIN));
			user.setPassword(oUser.getString(PASSWORD));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static User login(HttpServletRequest request) throws NullPointerException{
		User user = UserFactory.create();
		user.setLogin(request.getParameter(LOGIN));
		user.setPassword(request.getParameter(PASSWORD));
		return user;
	}
	
	public static User login(String json) {
		User user = UserFactory.create();
		JSONObject oUser;
		try {
			oUser = new JSONObject(json);
			user.setLogin(oUser.getString(LOGIN));
			user.setPassword(oUser.getString(PASSWORD));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean isValidUser(User user){
		if(/*user.getName() == null ||*/ !hasLogin(user) || !hasSenha(user)) return false;
		return true;
	}
	
	public static boolean hasLogin(User user){
		return user.getLogin() != null && !user.getLogin().isEmpty();
	}
	
	public static boolean hasSenha(User user){
		return user.getPassword() != null && !user.getPassword().isEmpty();
	}

	public static User profile(Long id) {
		User user = UserFactory.create();
		user.setId(id);
		return user;
	}
}
