package control.learning;

import org.json.JSONObject;

import control.MainCommand;
import control.Print;
import model.Learning;

public class SaveLearningCommand extends MainCommand {

	@Override
	public void execute() throws Exception {
		// Conteudo do HTTP
		getResponse().setContentType("text/javascript");
		
		System.out.println("Salvando uma aprendizagem");
		
		Learning learning = LearningRequest.newInstance().create(getRequest());
	    
		// Salva a aprendizagem
		JSONObject data = getDao().add(LearningDao.SAVE_LEARNING, learning);
		
		// Mostra a saida em JSON
		Print.json(getResponse(), data);
		
	}

}
