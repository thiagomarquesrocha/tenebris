package control.work;

import org.json.JSONObject;

import control.Print;
import control.user.UserRequest;
import model.User;

/**
 * List works one user
 * @author Thiago
 *
 */
public class WorkListByUserCommand extends WorkCommand {

	@Override
	public void execute() throws Exception {
		
		// Create a new user requested
		User user = new UserRequest().create(getRequest());
		// Look for works filtering by user id
		JSONObject json = getDao().list(WorkDao.LIST_BY_USER, user);
		// Show the list in JSON
		Print.json(getResponse(), json);
	}

}
