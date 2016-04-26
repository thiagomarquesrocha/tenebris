package control;

import java.sql.Connection;

import control.user.UserDao;
import control.work.WorkDao;

public class DaoManager {
	private static final DaoManager instance = new DaoManager();
	private Dao current; 
	
	public static DaoManager getInstance() {
		return instance;
	}

	public DaoManager user(){
		current = UserDao.getInstance();
		return this;
	}
	
	public DaoManager work(){
		current = WorkDao.getInstance();
		return this;
	}
	
	public DaoManager useConnection(Connection con){
		current.setCon(con);
		return this;
	}

	public Dao getDao() {
		return current;
	}
}
