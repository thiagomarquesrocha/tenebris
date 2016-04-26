package control;

import javax.servlet.http.HttpServletResponse;

import model.JSONResponse;
import model.Obra;

public abstract class RecommendControl implements JSONResponse<Obra> {
	
	HttpServletResponse response;
	
	public RecommendControl() {
	}
	
	public RecommendControl(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@Override
	final public boolean isRequest() {
		return response != null;
	}
	
}
