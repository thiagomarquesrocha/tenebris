package control.work;

import org.json.JSONObject;

import control.Print;
import model.UserWork;

public class WorkInfoCommand extends WorkCommand{
	
	@Override
	public void execute() throws Exception {
		// Conteudo do getResponse()
		getResponse().setContentType("text/javascript");
		getResponse().setCharacterEncoding("UTF-8");
		
		// Cria a obra e o usuario
		UserWork userWork = WorkUserRequest.getInstance().create(getRequest());
		// Busca os dados da obra 
		JSONObject json = getDao().find(WorkDao.FIND_BY_ID, userWork);
		// Mostra a saida em JSON
		Print.json(getResponse(), json);
	}
}
