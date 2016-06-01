package control.recomendation.rate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import control.RecommendControl;
import control.factory.ConnectionFactory;
import model.Obra;

public class BackEndRecommendation extends RecommendControl{

 private static final String SERVER_NAME = ConnectionFactory.HOST_NAME;
 private static final String USER_NAME = ConnectionFactory.USER;
 private static final String PASSWORD = ConnectionFactory.PASSWORD;
 private static final String DATABASE = ConnectionFactory.BD;
 
 /*private static final String SERVER_NAME = "localhost";
 private static final String USER_NAME = "root";
 private static final String PASSWORD = "";
 private static final String DATABASE = "csv_db";*/


 private static final int NEIGHBOR_HOOD_SIZE = 50;
 
 SaidaJSON json;
 
 public BackEndRecommendation(){
	 super();
 }
 
 public BackEndRecommendation(HttpServletResponse response){
	 super(response);
 }

 public Recommender getRecommender() throws Exception {
	 
  MysqlDataSource dataSource = new MysqlDataSource();
  dataSource.setServerName(SERVER_NAME);
  dataSource.setUser(USER_NAME);
  dataSource.setPassword(PASSWORD);
  dataSource.setDatabaseName(DATABASE);

  DataModel model = new MySQLJDBCDataModel(dataSource,
    "avaliacao", "usuario", "obra", "avaliacao", null);

  UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

  UserNeighborhood neighborhood = new NearestNUserNeighborhood(
    NEIGHBOR_HOOD_SIZE, similarity, model);

  Recommender recommender = new GenericUserBasedRecommender(model,
    neighborhood, similarity);

  return recommender;
 }

 public List<RecommendedItem> getRecommendations(Recommender recommender, int custId, int noOfRecommendations) throws Exception {
  return recommender.recommend(custId, noOfRecommendations);
 }

 public void displayRecommendations(int custId, List<RecommendedItem> recommendations) throws SQLException {
	  json = new SaidaJSON();
	  System.out.println("Recommendções para usuario " + custId + " são:");
	
	  for (RecommendedItem recommendation : recommendations) {
	   int obraId = (int) recommendation.getItemID();
	   json.JSON(obraId, custId);
	  }
	  System.out.print("{\"data\":");
	  try {
		if(isRequest())
			json.print(getResponse());
	  } catch (IOException e) {
			e.printStackTrace();
	  }
	  System.out.print("}");
 }

 @Override
 public List<Obra> getList() {
	return json.getList();
 }

public BackEndRecommendation BackEndR(int UserID, int QtdR) throws Exception{
	 
  Recommender recommender = getRecommender();
  List<RecommendedItem> recommendations;

  recommendations = getRecommendations(recommender, UserID, QtdR);
  displayRecommendations(UserID, recommendations);
  
  return this;

}
}
