package model;

public class User {
	private Long id;
	private String name, login, password;
	private Area area;
	private long institution;
	private int recommendation = 1;
	
	public User() {
	}
	
	public User(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public Area getArea(){
		return area;
	}

	public void setInstitution(long institution) {
		this.institution = institution;
	}

	public long getInstitution() {
		return institution;
	}

	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}

	public int getRecommendation() {
		return recommendation;
	}
}
