package control.keywords;

import org.json.JSONObject;

import control.MainCommand;
import control.Print;
import model.Keyword;

public class ListKeywordsCommand extends MainCommand {

	@Override
	public void execute() throws Exception {
		// Conteudo do HTTP
		getResponse().setContentType("text/javascript");
		
		System.out.println("Listando as palavras chaves de uma obra");
		
		Keyword keyword = KeywordRequest.newInstance().create(getRequest());
	    
		// Lista todas as palavras chaves de uma obra
		JSONObject data = getDao().list(KeywordsDao.LIST_KEYWORDS, keyword);
		
		// Mostra a saida em JSON
		Print.json(getResponse(), data);

	}

}
