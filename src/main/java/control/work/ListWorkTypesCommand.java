package control.work;


import org.json.JSONObject;

import control.Print;

public class ListWorkTypesCommand extends WorkCommand {

	@Override
	public void execute() throws Exception {
		// Conteudo do HTTP
		getResponse().setContentType("text/javascript");
		
		System.out.println("Listando os tipos de obras");
	    
		// Lista todas os tipos de obras existentes
		JSONObject data = getDao().list(WorkDao.LIST_WORK_TYPES);
		
		// Mostra a saida em JSON
		Print.json(getResponse(), data);
	}

}
