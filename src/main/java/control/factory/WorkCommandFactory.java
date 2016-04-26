package control.factory;

import control.work.WorkInfoCommand;
import model.MainCommand;
import model.NoCommand;

public class WorkCommandFactory extends CommandFactory<MainCommand> {
	
	private final static WorkCommandFactory instance = new WorkCommandFactory();
	
	// Comandos de obras
	public static final String VIEW = "view";
	
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
			default:
				c = new NoCommand();
				break;
		}
		
		return c;
	}
}
