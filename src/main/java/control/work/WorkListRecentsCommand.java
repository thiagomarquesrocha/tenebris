package control.work;

import org.json.JSONObject;

import control.Print;

public class WorkListRecentsCommand extends WorkCommand {

	@Override
	public void execute() throws Exception {
		getResponse().setContentType("text/javascript");
		getResponse().setCharacterEncoding("UTF-8");
		// Look for recents works
		JSONObject json = getDao().list(WorkDao.LIST_RECENTS);
		// Show the list in JSON
		Print.json(getResponse(), json);
	}

}
