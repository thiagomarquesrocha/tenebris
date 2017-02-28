package control.learning;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import model.Learning;
import model.Request;

public class LearningRequest extends Request<Learning>{
	
	private final String WORK = "workId";
	private final String USER = "userId";
	private final String RELEVANT = "relevant";
	private final String TITLE = "title";
	private final String KEY = "keyword";
	private Learning learning;

	private LearningRequest() {
		learning = Learning.newInstance();
	}
	
	public static LearningRequest newInstance(){
		return new LearningRequest();
	}

	@Override
	public Learning get(HttpServletRequest request) {
		String workId = request.getParameter(WORK);
		String userId = request.getParameter(USER);
		String relevant = request.getParameter(RELEVANT);
		String title = request.getParameter(TITLE);
		String key = request.getParameter(KEY);
		learning.setWorkId(Long.valueOf(workId));
		learning.setUserId(Long.valueOf(userId));
		learning.setRelevant(Integer.valueOf(relevant));
		learning.setTitle(title);
		learning.setPchave(Long.valueOf(key));
		return learning;
	}

	@Override
	public Learning post(StringBuffer sb) {
		try {
			JSONObject json = new JSONObject(sb.toString());
			learning.setWorkId(json.getLong(WORK));
			learning.setUserId(json.getLong(USER));
			learning.setRelevant(json.getInt(RELEVANT));
			learning.setTitle(json.getString(TITLE));
			learning.setPchave(json.getLong(KEY));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return learning;
	}

}
