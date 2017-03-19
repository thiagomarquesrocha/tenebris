package control;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import control.learning.Exemplo;
import control.recomendation.profile.Principal;
import control.recomendation.rate.BackEndRecommendation;
import model.Command;
import model.Obra;
import model.Recommend;
import model.RecommendRate;

public class RecommendHybrid implements Command{
	
	private RequestManager request;

	public RecommendHybrid(RequestManager request) {
		this.request = request;
	}


	@Override
	public void execute() throws Exception {
		RecommendRate recommend = request.getRecommend();
		HttpServletResponse response = request.getResponse();
		try {
			// Recomendação baseada no interesse 
			List<Obra> listByRate = new BackEndRecommendation().BackEndR(recommend.getUserID(), recommend.getQtdR()).getList();
			// Recomendacao baseada em conteudo
			List<Obra> listByData = new Principal().getRecommender(recommend.getUserID()).getList();
			// Lista de obras relevantes
			List<Obra> listByRelevancy = new Exemplo().Metodo(recommend.getUserID());
			
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
