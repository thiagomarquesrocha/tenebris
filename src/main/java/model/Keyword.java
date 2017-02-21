package model;

public class Keyword {
	
	private String[] words;
	
	public static Keyword newInstance(){
		return new Keyword();
	}
	
	public void setWords(String words){
		this.words = words.split(",");
	}

	public String[] getWords() {
		return words;
	}
}
