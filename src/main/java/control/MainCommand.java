package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Command;
import model.Dao;
import model.Servlet;

public abstract class MainCommand implements Command {
	private Dao dao;

	public MainCommand setDao(Dao dao) {
		this.dao = dao;
		return this;
	}

	protected Dao getDao() {
		return dao;
	}
	
	public HttpServletRequest getRequest(){
		return Servlet.getInstance().getRequest();
	}
	
	public HttpServletResponse getResponse(){
		return Servlet.getInstance().getResponse();
	}
}
