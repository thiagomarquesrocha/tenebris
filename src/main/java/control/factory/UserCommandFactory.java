package control.factory;

import control.user.PreferenceCRUDCommand;
import control.user.AddUserCommand;
import control.user.ListInstitutionsCommand;
import control.user.LoginUserCommand;
import control.user.LogoutUserCommand;
import control.user.ProfileUserCommand;
import control.user.SessionUserCommand;
import control.user.UpdateAreaCommand;
import model.MainCommand;
import model.NoCommand;

public class UserCommandFactory extends CommandFactory<MainCommand> {
	
	private final static UserCommandFactory instance = new UserCommandFactory();
	
	// Comandos de usuarios
	public static final String PROFILE = "profile";
	public static final String ADD = "add";
	public static final String LOGOUT = "logout";
	public static final String LOGIN = "login";
	public static final String SESSION = "session";

	public static final String ADD_INTEREST = "add_interest";
	public static final String UPDATE_AREA = "update_area";

	public static final String LIST_INSTITUTIONS = "list_institutions";
	
	private UserCommandFactory(){}
	
	public static UserCommandFactory getInstance() {
		return instance;
	}

	@Override
	public MainCommand getCommand(String command) {
		MainCommand c;
		
		switch (command) {
			case SESSION:
				c = new SessionUserCommand();
				break;
			case PROFILE:
				c = new ProfileUserCommand();
				break;
			case ADD :
				c = new AddUserCommand();
				break;
			case LOGOUT :
				c = new LogoutUserCommand();
				break;
			case LOGIN :
				c = new LoginUserCommand();
				break;
			case ADD_INTEREST :
				c = new PreferenceCRUDCommand();
				break;
			case UPDATE_AREA :
				c = new UpdateAreaCommand();
				break;
			case LIST_INSTITUTIONS:
				c = new ListInstitutionsCommand();
				break;
			default:
				c = new NoCommand();
				break;
		}
		
		return c;
	}
}
