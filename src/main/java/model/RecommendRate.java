package model;

public class RecommendRate {
	int UserID, QtdR, ItemID, RatingID;
	float VarRating;
	
	public RecommendRate(){
		
	}
	
	/**
	 * Recomendar obras
	 * @param UserID
	 * @param QtdR
	 */
	public void recommend(int UserID, int QtdR){
		this.UserID = UserID;
		this.QtdR = QtdR;
	}
	
	/**
	 * Avaliar uma obra
	 * @param ItemID
	 * @param UserID
	 * @param VarRating
	 */
	public void rate(int ItemID, int UserID, float VarRating){
		this.ItemID = ItemID;
		this.UserID = UserID;
		this.VarRating = VarRating;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public int getQtdR() {
		return QtdR;
	}

	public void setQtdR(int qtdR) {
		QtdR = qtdR;
	}

	public int getItemID() {
		return ItemID;
	}

	public void setItemID(int itemID) {
		ItemID = itemID;
	}

	public int getRatingID() {
		return RatingID;
	}

	public void setRatingID(int ratingID) {
		RatingID = ratingID;
	}

	public float getVarRating() {
		return VarRating;
	}

	public void setVarRating(float varRating) {
		VarRating = varRating;
	}
}
