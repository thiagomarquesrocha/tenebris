package control.factory;

import control.MainCommand;
import control.work.WorkInfoCommand;
import control.work.WorkListByUserCommand;
import control.work.WorkListRecentsCommand;
import control.work.WorkRemoveCommand;
import control.work.WorkSaveCommand;
import control.work.WorkUpdateCommand;
import model.NoCommand;

public class WorkCommandFactory extends CommandFactory<MainCommand> {
	
	private final static WorkCommandFactory instance = new WorkCommandFactory();
	
	// Comandos de obras
	public static final String VIEW = "view work";
	public static final String NEW = "new work";
	public static final String LIST_BY_USER = "list by user";
	public static final String UPDATE_WORK = "update work";
	public static final String REMOVE_WORK = "remove work";
	public static final String LIST_RECENTS = "list recents";
	
	private WorkCommandFactory(){
		
	}
	
	public static WorkCommandFactory getInstance() {
		return instance;
	}

	@Override
	public MainCommand getCommand(String command) {
		MainCommand c;
		
		switch (command) {
			case VIEW:
				c = new WorkInfoCommand();
				break;
			case NEW:
				c = new WorkSaveCommand();
				break;
			case LIST_BY_USER:
				c = new WorkListByUserCommand();
				break;
			case UPDATE_WORK:
				c = new WorkUpdateCommand();
				break;
			case REMOVE_WORK:
				c = new WorkRemoveCommand();
				break;
			case LIST_RECENTS:
				c = new WorkListRecentsCommand();
				break;
			default:
				c = new NoCommand();
				break;
		}
		
		return c;
	}
}
