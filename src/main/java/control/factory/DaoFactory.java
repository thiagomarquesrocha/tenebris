package control.factory;

import control.ConnectionPool;
import control.Dao;
import control.DaoManager;

public class DaoFactory {
	
	public static final int USER = 1;
	public static final int WORK = 2;
	
	public static Dao create(int factory){
		// Cria o DAO do usuario 
		DaoManager dao = DaoManager.getInstance();
		
		switch(factory){
			case USER :
				dao = dao.user();
				break;
			case WORK :
				dao = dao.work();
				break;
		}
		
		// Retorna a instancia do banco
		return dao.useConnection(ConnectionPool.getInstance().getConnection()).getDao();
	}
}
