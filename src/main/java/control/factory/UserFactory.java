package control.factory;

import model.User;

public class UserFactory {
	
	public static User create(Long id){
		return new User(id);
	}
	
	public static User create(){
		return new User();
	}
}
