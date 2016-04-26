package control.user;


import org.json.JSONObject;

import control.Print;

public class ListInstitutionsCommand extends UserCommand {

	@Override
	public void execute() throws Exception {
		// Conteudo do HTTP
		getResponse().setContentType("text/javascript");
		
		System.out.println("Listando as instituições");
	    
		// Lista todas as instituicoes existentes
		JSONObject data = getDao().list(UserDao.LIST_INSTITUTIONS);
		
		// Mostra a saida em JSON
		Print.json(getResponse(), data);
	}

}
