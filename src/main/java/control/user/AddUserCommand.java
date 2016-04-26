package control.user;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import control.JSONUtil;
import control.Print;
import model.User;

public class AddUserCommand extends UserCommand {

	@Override
	public void execute() throws Exception {
		// Conteudo do HTTP
		getResponse().setContentType("text/javascript");
		
		StringBuffer sb = JSONUtil.readJSON(getRequest());
		// Cria o usuario que deseja se autenticar
	    User user;
	    if(!sb.toString().isEmpty()) // POST (JSON)
	    	 user = UserRequest.addUser(sb.toString());
	    else // GET
	    	user = UserRequest.addUser(getRequest());
	    
		// Adiciona o usuario no banco
		JSONObject data = getDao().add(UserDao.ADD_USER, user);
		
		// Verifica se o usuario foi cadastrado
		if(UserJSON.hasUser(data)){
			// Recupera a sessao
			HttpSession session = getRequest().getSession();
			try {
				Long id = Long.valueOf(UserJSON.get(data, UserDao.LABEL_ID).toString());
				System.out.println("USER : " + id);
				// Cria uma sessao para o usuario
				session.setAttribute(ProfileUserCommand.USER, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Mostra a saida em JSON
		Print.json(getResponse(), data);
	}

}
