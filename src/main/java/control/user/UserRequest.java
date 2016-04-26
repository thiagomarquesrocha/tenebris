package control.user;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import control.factory.UserFactory;
import model.Area;
import model.Request;
import model.User;

public class UserRequest extends Request<User> {
	
	public static final String NAME = "name";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	private static final String ID = "id";
	private static final String AREA = "area";
	private static final String AREA_ID = "areaId";
	private static final String INSTITUTION = "institution";
	
	private User user;
	
	public static User addUser(HttpServletRequest request) throws NullPointerException{
		User user = UserFactory.create();
		user.setName(request.getParameter(NAME));
		user.setLogin(request.getParameter(LOGIN));
		user.setPassword(request.getParameter(PASSWORD));
		user.setInstitution(Long.valueOf(request.getParameter(AREA)));
		return user;
	}
	
	public static User addUser(String json) {
		User user = UserFactory.create();
		JSONObject oUser;
		try {
			oUser = new JSONObject(json);
			user.setLogin(oUser.getString(LOGIN));
			user.setPassword(oUser.getString(PASSWORD));
			user.setInstitution(oUser.getLong(INSTITUTION));
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

	@Override
	public User get(HttpServletRequest request) {
		try {
			user = UserFactory.create();
			updateArea();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	

	@Override
	public User post(StringBuffer sb) {
		try {
			user = UserFactory.create();
			updateArea();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	

	private void updateArea() {
		
		// GET
		String id = request.getParameter(ID);
		String areaName = request.getParameter(AREA);
		String areaId = request.getParameter(AREA_ID);
		
		// POST
		if(id == null && areaName == null && areaId == null){
			try {
				JSONObject json = new JSONObject(sb.toString());
				id = json.optString(ID);
				areaName = json.optString(AREA);
				areaId = json.optString(AREA_ID);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		Area area = new Area();
		if( id != null)
			user.setId(Long.valueOf(id));
		if(areaId != null && areaId.length() > 0)
			area.setId(Long.valueOf(areaId));
		if(areaName != null)
			area.setNome(areaName);
		
		user.setArea(area);
		
		System.out.println(String.format("User = %s, ID = %s, Nome = %s", user.getId(), area.getId(), area.getNome()));
	}
}
