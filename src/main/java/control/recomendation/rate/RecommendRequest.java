package control.recomendation.rate;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import model.RecommendRate;
import model.Request;

public class RecommendRequest extends Request<RecommendRate> {
	private static final String USER_ID = "userId";
	private static final String QTD = "qtd";
	
	private static final RecommendRequest instance = new RecommendRequest();
	
	private RecommendRequest(){}

	public static RecommendRequest getInstance() {
		return instance;
	}

	@Override
	public RecommendRate get(HttpServletRequest request) {
		RecommendRate recommend = RecommendFactory.create();
		String userId = request.getParameter(USER_ID);
		String qtdS = request.getParameter(QTD);
		//System.out.println("User by GET : "  + userId);
		int UserID = (userId == null || userId.isEmpty()) ? 0 : Integer.valueOf(userId);
		int qtd = (qtdS == null || qtdS.isEmpty()) ? 0 : Integer.valueOf(qtdS);
		recommend.recommend(UserID, qtd);
		return recommend;
	}

	@Override
	public RecommendRate post(StringBuffer sb) {
		String json = sb.toString();
		RecommendRate recommend = RecommendFactory.create();
		JSONObject oRecommend;
		try {
			oRecommend = new JSONObject(json);
			int UserID = oRecommend.getInt(USER_ID);
			int qtd = oRecommend.getInt(QTD);
			recommend.recommend(UserID, qtd);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return recommend;
	}
}
