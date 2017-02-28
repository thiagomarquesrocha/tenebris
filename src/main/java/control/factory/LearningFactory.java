package control.factory;

import control.MainCommand;
import control.learning.SaveLearningCommand;
import model.NoCommand;

public class LearningFactory extends CommandFactory<MainCommand>{
	
	private static LearningFactory instance = new LearningFactory();
	
	// Comandos de aprendizagens
	public static final String SAVE_LEARNING = "save learning";
	
	private LearningFactory(){}

	public static LearningFactory getInstance() {
		return instance;
	}

	@Override
	public MainCommand getCommand(String command) {
		MainCommand c;
		switch (command) {
			case SAVE_LEARNING :
				c = new SaveLearningCommand();
				break;
			default:
				c = new NoCommand();
				break;
	}
	
	return c;
	}

}
