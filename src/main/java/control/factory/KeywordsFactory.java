package control.factory;

import control.MainCommand;
import control.keywords.ListKeywordsCommand;
import model.NoCommand;

public class KeywordsFactory extends CommandFactory<MainCommand>{
	
	private static KeywordsFactory instance = new KeywordsFactory();
	
	// Comandos de palavras chaves
	public static final String LIST_KEYWORDS = "list keywords";
	
	private KeywordsFactory(){}

	public static KeywordsFactory getInstance() {
		return instance;
	}

	@Override
	public MainCommand getCommand(String command) {
		MainCommand c;
		switch (command) {
			case LIST_KEYWORDS :
				c = new ListKeywordsCommand();
				break;
			default:
				c = new NoCommand();
				break;
	}
	
	return c;
	}

}
