package control.keywords;

import control.Invoke;

public class KeywordsInvoke extends Invoke {
	private static final KeywordsInvoke instance = new KeywordsInvoke();
	
	private KeywordsInvoke(){}

	public static KeywordsInvoke getInstance() {
		return instance;
	}
}
