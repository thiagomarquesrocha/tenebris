package control.work;

import org.json.JSONObject;

import control.Print;
import control.user.UserRequest;
import model.User;

public class WorkListRecentsCommand extends WorkCommand {

	@Override
	public void execute() throws Exception {
		getResponse().setContentType("text/javascript");
		getResponse().setCharacterEncoding("UTF-8");
		// Create a new user requested
		User user = new UserRequest().create(getRequest());
		// Look for recents works
		JSONObject json = getDao().list(WorkDao.LIST_RECENTS, user);
		// Show the list in JSON
		Print.json(getResponse(), json);
	}

}
