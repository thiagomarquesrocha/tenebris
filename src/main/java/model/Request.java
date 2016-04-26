package model;

import javax.servlet.http.HttpServletRequest;

import control.JSONUtil;

/**
 * Pedido de request enviado para o servidor que pode ser do tipo POST (JSON) ou GET
 * @author Thiago
 *
 * @param <T>
 */
public abstract class Request <T>{
	
	protected HttpServletRequest request;
	protected StringBuffer sb;

	public final T create(HttpServletRequest request){
		
		this.sb = JSONUtil.readJSON(request);
		this.request = request;
		
		if(!sb.toString().isEmpty()){ // POST (JSON)
			System.out.println("JSON : " + sb.toString()); 
			return post(sb);
	    }else{ // GET
	    	System.out.println("GET "); 
			return get(request);
	    }
	}
	
	public abstract T get(HttpServletRequest request);
	public abstract T post(StringBuffer sb);
}
