package control.user;

import control.Invoke;

public class UserInvoke extends Invoke {
	private static final UserInvoke instance = new UserInvoke();
	
	private UserInvoke(){}

	public static UserInvoke getInstance() {
		return instance;
	}
}
