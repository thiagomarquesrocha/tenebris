package control.user;

import javax.servlet.http.HttpSession;

import control.JSONData;
import control.MainCommand;
import control.Print;
import control.factory.DataFactory;
import model.JSONOut;

public class LogoutUserCommand extends MainCommand {

	@Override
	public void execute() throws Exception {
		// Recupera a sessao
		HttpSession session = getRequest().getSession();
		// Limpa os dados da sessao
		session.invalidate();
		// Cria o objeto de saida da sessao
		JSONData data = DataFactory.create();
		// Adiciona o codigo de operacao confirmada
		data.put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA);
		// Mostra a saida do JSON
		Print.json(getResponse(), data);
	}

}
