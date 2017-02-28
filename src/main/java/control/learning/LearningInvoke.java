package control.learning;

import control.Invoke;

public class LearningInvoke extends Invoke {
	private static final LearningInvoke instance = new LearningInvoke();
	
	private LearningInvoke(){}

	public static LearningInvoke getInstance() {
		return instance;
	}
}
