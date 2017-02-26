package model;

public class Keyword {
	
	private String[] words;
	private Long work;
	
	public static Keyword newInstance(){
		return new Keyword();
	}
	
	public Long getWork() {
		return work;
	}

	public void setWork(Long work) {
		this.work = work;
	}

	public void setWords(String words){
		this.words = words.split(",");
	}

	public String[] getWords() {
		return words;
	}
}
