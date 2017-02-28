package model;

public class Learning {
	private Long workId, userId;
	private int relevant;
	private String title;
	private Long pchave;
	
	private Learning(){}
	
	public static Learning newInstance(){
		return new Learning();
	}
	
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getRelevant() {
		return relevant;
	}
	public void setRelevant(int relevant) {
		this.relevant = relevant;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getPchave() {
		return pchave;
	}
	public void setPchave(Long pchave) {
		this.pchave = pchave;
	}
}
