package model;

import java.util.ArrayList;
import java.util.List;

public class Recommend {
	private List<Obra> recommend;

	public Recommend() {
		recommend = new ArrayList<>();
	}
	
	public Recommend add(List<Obra> list){
		if(list == null) return this;
		recommend.addAll(list);
		return this;
	}
}
