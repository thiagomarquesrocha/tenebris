package control.user;

import javax.servlet.http.HttpSession;
import org.json.JSONObject;

import control.JSONData;
import control.Print;
import control.factory.DataFactory;
import control.recomendation.profile.Rotina;
import model.JSONOut;
import model.User;

public class ProfileUserCommand extends UserCommand{

	public static final String USER = "user";
	
	@Override
	public void execute() throws Exception {
		// Conteudo do getResponse()
		getResponse().setContentType("text/javascript");
		
		// Recupera a sessao
		HttpSession session = getRequest().getSession();
		
		// Cria o objeto de saida em JSON
		JSONData data = DataFactory.create();
		
		Rotina.Executar();
		
		// Existe uma sessao
		if(session!=null){
			Long id = 0l;
			try {
				id = (Long) session.getAttribute(USER);
			} catch (Exception e) {	}
			if(id == null || id <= 0){ // Nao existe nenhum usuario autenticado
				System.out.println("Nao existe nenhuma sessao, pois nao encontrou nenhum ID");
				data.put(JSONOut.CODE, JSONOut.User.NAO_AUTENTICADO);
			}else{
				// Cria o usuario
				User user = UserRequest.profile(id);
				// Busca os dados do usuario 
				JSONObject json = getDao().find(UserDao.FIND_BY_ID, user);
				// Mostra a saida em JSON
				Print.json(getResponse(), json);
				// Finaliza
				return;
			}
		}else{ // Nao existe uma sessao
			System.out.println("Nao existe nenhuma sessao");
			data.put(JSONOut.CODE, JSONOut.User.NAO_AUTENTICADO);
		}
		
		// Retorna o JSON de saida da autenticacao
		Print.json(getResponse(), data);
	}
}
