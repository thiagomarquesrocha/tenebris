package control.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import control.MainCommand;

public class SessionUserCommand extends MainCommand {

	@Override
	public void execute() throws Exception {
		// Recupera a sessao
		HttpSession session = getRequest().getSession();
		// View que sera retornada para o usuario
		RequestDispatcher view;
		if(session != null){
			// Recupera um ID se existir na sessao
			Long id = (Long) session.getAttribute(ProfileUserCommand.USER);
			System.out.println("Existe uma sessao? " + id);
			// Direciona para a plataforma, o usuario esta logado
			if(id != null && id != 0)
				view = getRequest().getRequestDispatcher("application/index.html");
		}
		// Direciona para a tela principal do site
		view = getRequest().getRequestDispatcher("/index.html");
		try {
			view.forward(getRequest(), getResponse());
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}

}
