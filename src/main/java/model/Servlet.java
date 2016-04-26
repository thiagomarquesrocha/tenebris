package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet {
	private static Servlet instance = new Servlet();
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private Servlet(){}
	
	public static Servlet getInstance() {
		return instance;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	public Servlet setRequest(HttpServletRequest request) {
		this.request = request;
		return this;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public Servlet setResponse(HttpServletResponse response) {
		this.response = response;
		return this;
	}
}
