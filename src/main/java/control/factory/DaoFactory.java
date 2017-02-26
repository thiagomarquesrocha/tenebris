package control.factory;

import control.ConnectionSingleton;
import control.DaoBuilder;
import model.Dao;

public class DaoFactory {
	
	public static final int USER = 1;
	public static final int WORK = 2;
	public static final int AREA = 3;
	public static final int KEYWORDS = 4;
	
	public static Dao create(int factory){
		// Cria o DAO do usuario 
		DaoBuilder dao = DaoBuilder.getInstance();
		
		switch(factory){
			case USER :
				dao = dao.user();
				break;
			case WORK :
				dao = dao.work();
				break;
			case AREA:
				dao = dao.area();
				break;
			case KEYWORDS :
				dao = dao.keywords();
				break;
		}
		
		// Retorna a instancia do banco
		return dao.useConnection(ConnectionSingleton.getInstance().getConnection()).getDao();
	}
}
