package control.recomendation.rate;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import model.RecommendParameters;
import model.Request;

public class RateUtil extends Request<RecommendParameters> {
	private static final String ITEM_ID = "itemId";
	private static final String USER_ID = "userId";
	private static final String RATING = "rating";
	
	private static final RateUtil instance = new RateUtil();
	
	private RateUtil(){}

	public static RateUtil getInstance() {
		return instance;
	}

	@Override
	public RecommendParameters get(HttpServletRequest request) {
		RecommendParameters recommend = RecommendFactory.create();
		int ItemID = Integer.valueOf(request.getParameter(ITEM_ID));
		int UserID = Integer.valueOf(request.getParameter(USER_ID));
		float VarRating = Float.valueOf(request.getParameter(RATING));
		recommend.rate(ItemID, UserID, VarRating);
		return recommend;
	}

	@Override
	public RecommendParameters post(StringBuffer sb) {
		String json = sb.toString();
		RecommendParameters recommend = RecommendFactory.create();
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
