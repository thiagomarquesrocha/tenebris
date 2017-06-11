package control.user;


import org.json.JSONObject;

import control.Print;
import model.User;

public class UpdateRecommendationCommand extends UserCommand {

	@Override
	public void execute() throws Exception {
		// Conteudo do HTTP
		getResponse().setContentType("text/javascript");
		
		
		// Cria o usuario que deseja atualizar a recomendacao
	    User user = new UserRequest().create(getRequest());
	    
		// Atualiza o usuario no banco
		JSONObject data = getDao().update(UserDao.UPDATE_RECOMMENDATION, user);
		
		// Mostra a saida em JSON
		Print.json(getResponse(), data);
	}

}
