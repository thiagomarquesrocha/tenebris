package control.recomendation.rate;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import model.RecommendParameters;
import model.Request;

public class RecommendRequest extends Request<RecommendParameters> {
	private static final String USER_ID = "userId";
	private static final String QTD = "qtd";
	
	private static final RecommendRequest instance = new RecommendRequest();
	private static final String RECOMMENDATION = "recommendation";
	
	private RecommendRequest(){}

	public static RecommendRequest getInstance() {
		return instance;
	}

	@Override
	public RecommendParameters get(HttpServletRequest request) {
		RecommendParameters recommend = RecommendFactory.create();
		String userId = request.getParameter(USER_ID);
		String qtdS = request.getParameter(QTD);
		String recommendationS = request.getParameter(RECOMMENDATION);
		//System.out.println("User by GET : "  + userId);
		int UserID = (userId == null || userId.isEmpty()) ? 0 : Integer.valueOf(userId);
		int qtd = (qtdS == null || qtdS.isEmpty()) ? 0 : Integer.valueOf(qtdS);
		int recommendation = (recommendationS == null || recommendationS.isEmpty()) ? 1 : Integer.valueOf(recommendationS);
		recommend.recommend(UserID, qtd);
		recommend.setType(recommendation);
		return recommend;
	}

	@Override
	public RecommendParameters post(StringBuffer sb) {
		String json = sb.toString();
		RecommendParameters recommend = RecommendFactory.create();
		JSONObject oRecommend;
		try {
			oRecommend = new JSONObject(json);
			int UserID = oRecommend.getInt(USER_ID);
			int qtd = oRecommend.getInt(QTD);
			int recommendation = oRecommend.getInt(RECOMMENDATION);
			recommend.recommend(UserID, qtd);
			recommend.setType(recommendation);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return recommend;
	}
}
