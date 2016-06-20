package control.work;

import org.json.JSONObject;

import control.Print;
import model.UserWork;

public class WorkRemoveCommand extends WorkCommand {

	@Override
	public void execute() throws Exception {
		// Get user and work from request
		UserWork userWork = WorkUserRequest.getInstance().create(getRequest());
		// Execute remove command
		JSONObject json = getDao().delete(WorkDao.REMOVE_BY_ID, userWork);
		// Show JSON resulted
		Print.json(getResponse(), json);
	}

}
