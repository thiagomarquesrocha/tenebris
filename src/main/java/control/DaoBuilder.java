package control;

import java.sql.Connection;

import control.area.AreaDao;
import control.user.UserDao;
import control.work.WorkDao;
import model.Dao;

public class DaoBuilder {
	private static final DaoBuilder instance = new DaoBuilder();
	private Dao current; 
	
	public static DaoBuilder getInstance() {
		return instance;
	}

	public DaoBuilder user(){
		current = UserDao.getInstance();
		return this;
	}
	
	public DaoBuilder work(){
		current = WorkDao.getInstance();
		return this;
	}
	
	public DaoBuilder area() {
		current = AreaDao.getInstance();
		return this;
	}
	
	public DaoBuilder useConnection(Connection con){
		current.setCon(con);
		return this;
	}

	public Dao getDao() {
		return current;
	}
}
