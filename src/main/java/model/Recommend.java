package model;

import java.util.ArrayList;
import java.util.List;

public class Recommend {
	private List<Obra> recommend;

	public Recommend() {
		recommend = new ArrayList<>();
	}
	
	public Recommend add(List<Obra> list){
		recommend.addAll(list);
		return this;
	}
}
