package control.keywords;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import model.Keyword;
import model.Request;

public class KeywordRequest extends Request<Keyword>{

	private static final String WORK = "workId";
	private Keyword keyword;
	
	private KeywordRequest() {
		keyword = Keyword.newInstance();
	}
	
	public static KeywordRequest newInstance(){
		return new KeywordRequest();
	}

	@Override
	public Keyword get(HttpServletRequest request) {
		String workId = request.getParameter(WORK);
		keyword.setWork(Long.valueOf(workId));
		return keyword;
	}

	@Override
	public Keyword post(StringBuffer sb) {
		try {
			JSONObject json = new JSONObject(sb.toString());
			keyword.setWork(json.getLong(WORK));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return keyword;
	}

}
