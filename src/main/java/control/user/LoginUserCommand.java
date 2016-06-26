package control.user;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import control.DataUtil;
import control.JSONUtil;
import control.Print;
import model.JSONOut;
import model.User;

public class LoginUserCommand extends UserCommand {

	@Override
	public void execute() throws Exception {
		
		StringBuffer sb = JSONUtil.readJSON(getRequest());
		
		// Cria o usuario que deseja se autenticar
	    User user;
	    if(!sb.toString().isEmpty()){ // POST (JSON)
	    	 user = UserRequest.login(sb.toString());
	    	 System.out.println("JSON : " + sb.toString());
	    }else{ // GET
			user = UserRequest.login(getRequest());
	    }
	   
		// Adiciona o usuario no banco
		JSONObject data = getDao().find(UserDao.FIND_BY_LOGIN, user);
		
		// Verifica se algum usuario foi encontrado
		if(UserJSON.hasUser(data)){
			// Recupera a sessao
			HttpSession session = getRequest().getSession(true);
			try {
				Object o = UserJSON.get(data, UserDao.LABEL_ID);
				System.out.println(o);
				System.out.println(data.toString());
				Long id = Long.valueOf(UserJSON.get(data, UserDao.LABEL_ID).toString());
				System.out.println("Session for USER : " + id);
				// Cria uma sessao para o usuario
				session.setAttribute(ProfileUserCommand.USER, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			data = DataUtil.put(data, JSONOut.CODE, JSONOut.User.NENHUM_USUARIO_ENCONTRADO);
		}
		
		// Mostra a saida em JSON
		Print.json(getResponse(), data);
	}

}
