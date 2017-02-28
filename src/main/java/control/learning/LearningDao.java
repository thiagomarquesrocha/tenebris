package control.learning;

import org.json.JSONObject;

import control.DataUtil;
import model.Dao;
import model.JSONOut;
import model.Learning;

public class LearningDao extends Dao{

	public static final int SAVE_LEARNING = 1;
	
	private static LearningDao instance = new LearningDao();
	
	private LearningDao(){}

	public static LearningDao getInstance() {
		return instance;
	}

	public static void setInstance(LearningDao instance) {
		LearningDao.instance = instance;
	}

	@Override
	public JSONObject add(Object... o) {
		boolean isInvalid = DataUtil.create(getData(), o, JSONOut.Erro.OCORREU_ALGUM_ERRO);
		// Se nao existe nenhum parametro nao eh possivel completar a aprendizagem
		if(isInvalid) return getData().getJSONObject();
		int op = (int) o[0];
		
		switch(op){
			case SAVE_LEARNING :
				Learning learning = (Learning) o[1];
				LearningControl.save(this, learning);
				break;
		}
		
		return getData().getJSONObject();
	}

	@Override
	public JSONObject delete(Object... o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject update(Object... o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject list(Object... o) {
		return null;
	}

	@Override
	public JSONObject find(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

}
