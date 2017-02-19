package control.factory;

import control.MainCommand;
import model.NoCommand;

public class KeywordsFactory extends CommandFactory<MainCommand>{
	
	private static KeywordsFactory instance = new KeywordsFactory();
	
	private KeywordsFactory(){}

	public static KeywordsFactory getInstance() {
		return instance;
	}

	@Override
	public MainCommand getCommand(String command) {
		MainCommand c;
		switch (command) {
			default:
				c = new NoCommand();
				break;
	}
	
	return c;
	}

}
