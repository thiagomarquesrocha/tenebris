package control.user;

import model.CRUD;

public abstract class PreferencesCRUD extends UserCommand implements CRUD{

	@Override
	public void execute() throws Exception {
		// Conteudo do HTTP
		getResponse().setContentType("text/javascript");
	}
}
