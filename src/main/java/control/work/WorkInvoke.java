package control.work;

import control.Invoke;

public class WorkInvoke extends Invoke {
	private static final WorkInvoke instance = new WorkInvoke();
	
	private WorkInvoke(){}

	public static WorkInvoke getInstance() {
		return instance;
	}
}
