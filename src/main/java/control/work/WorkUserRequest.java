package control.work;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import control.factory.UserFactory;
import control.factory.UserWorkFactory;
import control.factory.WorkFactory;
import model.Obra;
import model.Request;
import model.User;
import model.UserWork;

public class WorkUserRequest extends Request<UserWork> {
	
	private final static WorkUserRequest instance = new WorkUserRequest();
	
	public static final String ID = "id";
	public static final String USER = "user";
	private static final String COMMAND = "command";
	
	private WorkUserRequest(){
		
	}

	public static WorkUserRequest getInstance() {
		return instance;
	}

	@Override
	public UserWork get(HttpServletRequest request) {
		try {
			UserWork userWork = UserWorkFactory.create();
			Obra work = WorkFactory.create();
			User user = UserFactory.create();
			//System.out.println("ID : " + request.getParameter(ID));
			work.setid(Long.valueOf(request.getParameter(ID)));
			user.setId(Long.valueOf(request.getParameter(USER)));
			userWork.setUser(user).setWork(work);
			return userWork;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UserWork post(StringBuffer sb) {
		try {
			UserWork userWork = UserWorkFactory.create();
			Obra work = WorkFactory.create();
			User user = UserFactory.create();
			JSONObject json = new JSONObject(sb.toString());
			//System.out.println("ID : " + request.getParameter(ID));
			work.setid(json.getLong(ID));
			user.setId(json.getLong(USER));
			
			if( json.has(COMMAND) ){
				String command = json.optString(COMMAND, "");
				if(command.equals("removeWork")){
					String path = json.getString("path");
					work.setFile(path);
				}
			}
			
			userWork.setUser(user).setWork(work);
			return userWork;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
