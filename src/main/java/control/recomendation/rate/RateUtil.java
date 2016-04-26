package control.recomendation.rate;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import model.RecommendRate;
import model.Request;

public class RateUtil extends Request<RecommendRate> {
	private static final String ITEM_ID = "itemId";
	private static final String USER_ID = "userId";
	private static final String RATING = "rating";
	
	private static final RateUtil instance = new RateUtil();
	
	private RateUtil(){}

	public static RateUtil getInstance() {
		return instance;
	}

	@Override
	public RecommendRate get(HttpServletRequest request) {
		RecommendRate recommend = RecommendFactory.create();
		int ItemID = Integer.valueOf(request.getParameter(ITEM_ID));
		int UserID = Integer.valueOf(request.getParameter(USER_ID));
		float VarRating = Float.valueOf(request.getParameter(RATING));
		recommend.rate(ItemID, UserID, VarRating);
		return recommend;
	}

	@Override
	public RecommendRate post(StringBuffer sb) {
		String json = sb.toString();
		RecommendRate recommend = RecommendFactory.create();
		JSONObject oRecommend;
		try {
			oRecommend = new JSONObject(json);
			int ItemID = oRecommend.getInt(ITEM_ID);
			int UserID = oRecommend.getInt(USER_ID);
			float VarRating = (float) oRecommend.getDouble(RATING);
			recommend.rate(ItemID, UserID, VarRating);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return recommend;
	}
}
