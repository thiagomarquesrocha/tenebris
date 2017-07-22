package control;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import control.learning.bayes.Exemplo;
import control.learning.me.bazhenov.maxent.MaxEnt;
import control.recomendation.profile.Principal;
import control.recomendation.rate.BackEndRecommendation;
import model.Command;
import model.Obra;
import model.Recommend;
import model.RecommendParameters;

public class RecommendHybrid implements Command{
	
	private static final int RECOMMENDATION_1 = 1; // FC, FBC, Bayes
	private static final int RECOMMENDATION_2 = 2; // FC. FBC, Max Entropy
	private RequestManager request;

	public RecommendHybrid(RequestManager request) {
		this.request = request;
	}

	@Override
	public void execute() throws Exception {
		RecommendParameters recommend = request.getRecommend();
		HttpServletResponse response = request.getResponse();
		try {
			// Recomendação baseada no interesse 
			List<Obra> listByRate = new BackEndRecommendation().BackEndR(recommend.getUserID(), recommend.getQtdR()).getList();
			// Recomendacao baseada em conteudo
			List<Obra> listByData = new Principal().getRecommender(recommend.getUserID()).getList();
			
			List<Obra> listByRelevancy = null;
			
			// Lista de obras relevantes
			switch(recommend.getType()){
				case RECOMMENDATION_1 :
					listByRelevancy = new Exemplo().Metodo(recommend.getUserID());
					System.out.println("[NaiveBayes] Total de obras relevantes " + listByRelevancy.size());
					break;
				case RECOMMENDATION_2 :
					listByRelevancy = new MaxEnt().Metodo(recommend.getUserID());
					break;
			}
			
			
			Recommend recommends = new Recommend();
			/*// Converte para utf-8
			listByRate = (List<Obra>) JSONUtil.get(listByRate, List.class);
			// Converte para utf-8
			listByData = (List<Obra>) JSONUtil.get(listByData, List.class);*/
			recommends.add(listByRate).add(listByData).add(listByRelevancy);
			JSONUtil.print(response, recommends);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
