package control.user;

import org.json.JSONObject;

import control.Print;
import model.Preference;

public class PreferenceCRUDCommand extends PreferencesCRUD {
	
	private JSONObject data;
	private Preference preference;

	@Override
	public void execute() throws Exception {
		super.execute();
		
		preference = PreferenceRequest.getInstance().create(getRequest());
		
		//System.out.println(preference.getType());
		
		// Cadastrar um novo interesse
		if(preference.getType().equals("add"))
			create();
		if(preference.getType().equals("remove"))
			delete();
		
		// Mostra a saida em JSON
		Print.json(getResponse(), data);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		String table = getTable(preference.getAction());
		
		data = getDao().delete(preference.getAction(), table, preference);
	}

	@Override
	public void create() {
		String table = getTable(preference.getAction());
		
		data = getDao().add(preference.getAction(), table, preference);
	}

	@Override
	public void recovery() {
		
	}

	private String getTable(int action) {
		String table = "";
		switch(action){
			case UserDao.ADD_USER : // Executa o comando para adicioanr um usuario
				break;
			case UserDao.ADD_INTEREST :
				table = "usuario_tags";
				break;
			case UserDao.ADD_INSTITUTION :
				table = "usuario_instituicoes";
				break;
			case UserDao.ADD_AUTH :
				table = "usuario_autores";
				break;
		}
		return table;
	}
}
