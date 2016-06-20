package model;

public class UserWork {
	private Obra work;
	private User user;
	
	public Obra getWork() {
		return work;
	}
	public UserWork setWork(Obra work) {
		this.work = work;
		return this;
	}
	public User getUser() {
		return user;
	}
	public UserWork setUser(User user) {
		this.user = user;
		return this;
	}
	
	
}
