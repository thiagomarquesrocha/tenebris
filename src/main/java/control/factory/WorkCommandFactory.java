package control.factory;

import control.work.WorkInfoCommand;
import control.work.WorkSaveCommand;
import model.MainCommand;
import model.NoCommand;

public class WorkCommandFactory extends CommandFactory<MainCommand> {
	
	private final static WorkCommandFactory instance = new WorkCommandFactory();
	
	// Comandos de obras
	public static final String VIEW = "view work";
	public static final String NEW = "new work";
	
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
			default:
				c = new NoCommand();
				break;
		}
		
		return c;
	}
}
